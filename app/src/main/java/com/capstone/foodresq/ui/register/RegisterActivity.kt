package com.capstone.foodresq.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivityRegisterBinding
import com.capstone.foodresq.ui.login.LoginActivity
import com.capstone.foodresq.utils.Utils

class RegisterActivity : AppCompatActivity() {

    private var _binding : ActivityRegisterBinding? = null
    private val binding get() = _binding!!
    private var itemSelected : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dropDown = binding.dropdownUser

        val typeUser = resources.getStringArray(R.array.type_user)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, typeUser)
        dropDown.setAdapter(arrayAdapter)
        dropDown.onItemClickListener = AdapterView.OnItemClickListener{adapterView, view, position, l ->
            itemSelected = adapterView.getItemAtPosition(position).toString()
        }

        setButtonRegisterEnabled()
        onTextFieldChanged()
        setLoginHandler()
        buttonRegisterHandler()
    }

    private fun onTextFieldChanged(){
        binding.textInputEditEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.btnRegister.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setButtonRegisterEnabled()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.textInputEditPassword.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.btnRegister.isEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                setButtonRegisterEnabled()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun setButtonRegisterEnabled() {
        val email = binding.textInputEditEmail.text.toString()
        val password = binding.textInputEditPassword.text.toString()

        binding.btnRegister.isEnabled = email.isNotEmpty() && password.isNotEmpty() && Utils.isValidEmail(email) && Utils.isValidPassword(password)
    }

    private fun buttonRegisterHandler(){
        binding.btnRegister.setOnClickListener {
            if (itemSelected == null){
                binding.dropDownTypeUser.error = "Please define your user type"
            } else{
                val message = "Your new account already set. Let's get in!"
                val title = "Success"
                makeAlert(message, title)

                binding.textInputEditEmail.text = null
                binding.textInputEditPassword.text = null
                itemSelected = null
            }
        }
    }

    private fun setLoginHandler() {
        binding.txtNavigateToLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            Handler(Looper.getMainLooper()).postDelayed({
                showLoading(true)
                binding.txtNavigateToLogin.setTextColor(resources.getColor(R.color.color_text_4))
                startActivity(intent)
                showLoading(false)
                binding.txtNavigateToLogin.setTextColor(resources.getColor(R.color.color_palette_5))
            }, 3000)

        }
    }

    private fun makeAlert(message : String, title : String){
        AlertDialog.Builder(this)
            .setMessage(message)
            .setTitle(title)
            .setPositiveButton("OK"){dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun showLoading(load : Boolean){
        binding.layoutLoading.progressBar.isVisible = load == true
    }
}