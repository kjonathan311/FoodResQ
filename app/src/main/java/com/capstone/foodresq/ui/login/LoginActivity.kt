package com.capstone.foodresq.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.view.isVisible
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityLoginBinding
import com.capstone.foodresq.ui.main.MainActivity
import com.capstone.foodresq.ui.register.RegisterActivity
import com.capstone.foodresq.utils.Utils

class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        setButtonLoginEnable()
        onTextInputChanged()
        buttonLoginHandler()
        setRegisterHandler()
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

    private fun buttonLoginHandler(){
        if (binding.btnLogin.isEnabled){
            binding.btnLogin.setOnClickListener {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                Handler(Looper.getMainLooper()).postDelayed({
                    showLoading(true)
                    startActivity(intent)
                    showLoading(false)
                }, 3000)
            }
        }
    }

    private fun setRegisterHandler(){
        binding.txtNavigateToRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            Handler(Looper.getMainLooper()).postDelayed({
                showLoading(true)
                binding.txtNavigateToRegister.setTextColor(resources.getColor(R.color.color_text_4))
                startActivity(intent)
                showLoading(false)
                binding.txtNavigateToRegister.setTextColor(resources.getColor(R.color.color_palette_5))
            }, 2000)
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

}