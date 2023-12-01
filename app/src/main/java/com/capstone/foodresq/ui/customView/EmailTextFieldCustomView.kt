package com.capstone.foodresq.ui.customView

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.capstone.foodresq.R
import com.capstone.foodresq.utils.Utils

class EmailTextFieldCustomView : AppCompatEditText {
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
                if (Utils.isValidEmail(s.toString())){
                    this@EmailTextFieldCustomView.error = null
                } else {
                    this@EmailTextFieldCustomView.error = resources.getString(R.string.error_email)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }
}