package com.capstone.foodresq.ui.subscription

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.ActivitySubscriptionBinding
import com.capstone.foodresq.ui.login.LoginActivity
import com.capstone.foodresq.utils.Utils.observeOnce
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SubscriptionActivity : AppCompatActivity() {

    lateinit var binding:ActivitySubscriptionBinding
    private val subscriptionViewModel:SubscriptionViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySubscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setMenuIcon()

        checkSubscription()
        subscriptionViewModel.loading.observe(this){
            showLoading(it)
        }

        binding.buttonSubscribe.setOnClickListener {
            subscriptionViewModel.subscribe()
            subscriptionViewModel.subscribeStatus.observe(this){
                if(it!=null){
                    lifecycleScope.launch {
                        suspendCoroutine<Unit> { continuation ->
                            showAlertDialog("Status", it) {
                                continuation.resume(Unit)
                            }
                        }
                        finish()
                    }
                }
            }
        }
    }

    fun checkSubscription(){
        subscriptionViewModel.getSubscription()
        subscriptionViewModel.subscriptionStatus.observeOnce{
            if (it!=null&&it=="standard"){
                binding.buttonSubscribe.isEnabled=true
            }else{
                binding.buttonSubscribe.isEnabled=false
                binding.buttonSubscribe.text= getString(R.string.already_subscribed)
            }
        }
    }
    private fun showAlertDialog(title: String, message: String, callback: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { _, _ ->
                callback.invoke()
            }
            .setCancelable(false)
            .show()
    }

    fun setMenuIcon(){
        binding.toolbarSub.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarSub.setNavigationOnClickListener({
            finish()
        })
    }

    private fun showLoading(load : Boolean){
        binding.progressSub.isVisible = load == true
    }
}