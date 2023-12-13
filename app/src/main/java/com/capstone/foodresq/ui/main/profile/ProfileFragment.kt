package com.capstone.foodresq.ui.main.profile

import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.FragmentProfileBinding
import com.capstone.foodresq.ui.login.LoginActivity
import com.capstone.foodresq.ui.main.MainViewModel
import com.capstone.foodresq.ui.subscription.SubscriptionActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    lateinit var binding:FragmentProfileBinding
    private val profileViewModel:ProfileViewModel by viewModel()
    private val mainViewModel:MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSettingData()
    }

    private fun setSettingData(){
        binding.btnSubscription.setTitle(getString(R.string.subscription))
        binding.btnSubscription.setIcon(getString(R.string.subscription_icon))
        binding.btnSubscription.setOnClickListener {
            startActivity(Intent(requireActivity(),SubscriptionActivity::class.java))
        }
        binding.btnSettings.setTitle(getString(R.string.settings))
        binding.btnSettings.setIcon(getString(R.string.settings_icon))
        binding.btnSettings.setOnClickListener {
            PopupMenu(requireContext(),it).run{
                menuInflater.inflate(R.menu.menu_logout,menu)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.action_logout-> {
                                profileViewModel.logout()
//                                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                            }
                        }
                        true
                    }
                show()
                }
            }
        }
}


