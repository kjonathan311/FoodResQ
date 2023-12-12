package com.capstone.foodresq.ui.login

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.capstone.foodresq.R
import com.capstone.foodresq.data.datastore.UserModel
import com.capstone.foodresq.databinding.ActivityLoginBinding
import com.capstone.foodresq.ui.main.MainActivity
import com.capstone.foodresq.ui.register.RegisterActivity
import com.capstone.foodresq.utils.Utils
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel:LoginViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        setButtonLoginEnable()
        onTextInputChanged()
        setRegisterHandler()

        loginViewModel.loading.observe(this){
            showLoading(it)
        }
        binding.btnLogin.setOnClickListener {
            LoginUser()
        }
    }

    private fun LoginUser(){
        val checkSessionLogin = MutableLiveData<Boolean>()
        val email=binding.textInputEditEmail.text.toString()
        val password=binding.textInputEditPassword.text.toString()
        loginViewModel.login(email,password)
        loginViewModel.successResult.observe(this){
            if (it!=null){
                checkSessionLogin.value=true
                loginViewModel.saveSession(UserModel(it.token.toString()))
            }
        }
        loginViewModel.errorMessage.observe(this){
            if (it!=null){
                showAlertDialog(this,"error",it)
            }
        }
        checkSessionLogin.observe(this){
            lifecycleScope.launch {
                loginViewModel.getSession().observe(this@LoginActivity){
                    if(it!=null){
                        Log.d("check",it.token)
                        Log.d("check",it.isLogin.toString())
                        startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    private fun setButtonLoginEnable(){
        val email = binding.textInputEditEmail.text.toString()
        val password = binding.textInputEditPassword.text.toString()

        binding.btnLogin.isEnabled = email.isNotEmpty() && password.isNotEmpty() && Utils.isValidEmail(email) && Utils.isValidPassword(password)
    }

    private fun onTextInputChanged(){
        binding.textInputEditEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setButtonLoginEnable()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.textInputEditPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setButtonLoginEnable()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }


    private fun setRegisterHandler(){
        binding.txtNavigateToRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showLoading(load : Boolean){
        binding.layoutLoading.progressBar.isVisible = load == true
    }

    private fun setUpView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    fun showAlertDialog(context: Context, errorTitle:String, errorMessage: String) {
        AlertDialog.Builder(context)
            .setTitle(errorTitle)
            .setMessage(errorMessage)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


}