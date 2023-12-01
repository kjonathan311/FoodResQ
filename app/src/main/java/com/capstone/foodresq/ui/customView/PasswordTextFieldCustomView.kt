package com.capstone.foodresq.ui.customView

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.capstone.foodresq.R
import com.capstone.foodresq.utils.Utils

class PasswordTextFieldCustomView : AppCompatEditText {
    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet){
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr){
        init()
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (Utils.isValidPassword(s.toString())){
                    this@PasswordTextFieldCustomView.error = null
                } else {
                    this@PasswordTextFieldCustomView.error = resources.getString(R.string.error_password)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
}