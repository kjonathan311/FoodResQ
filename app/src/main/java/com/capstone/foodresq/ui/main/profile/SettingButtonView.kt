package com.capstone.foodresq.ui.main.profile

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.capstone.foodresq.R

class SettingButtonView:LinearLayout {

    private lateinit var ivIcon: ImageView
    private lateinit var tvTitle: TextView

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    private fun init(context: Context){
        val rootView= inflate(context, R.layout.settings_button,this)

        ivIcon=rootView.findViewById(R.id.iv_settings)
        tvTitle=rootView.findViewById(R.id.tv_settings)

    }

    fun setIcon(image: String) {
        val resourceId=resources.getIdentifier(image,"drawable",context.packageName)
        if (resourceId != 0) {
            ivIcon.setImageResource(resourceId)
        } else {
            ivIcon.setImageResource(R.drawable.rounded_corner)
        }
    }

    fun setTitle(title: String) {
        tvTitle.text = title
    }
}