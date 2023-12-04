package com.capstone.foodresq.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.capstone.foodresq.R
import com.capstone.foodresq.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    lateinit var binding:FragmentProfileBinding
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
        binding.btnSettings.setTitle(getString(R.string.settings))
        binding.btnSettings.setIcon(getString(R.string.settings_icon))
        binding.btnSettings.setOnClickListener {
            Toast.makeText(requireActivity(),"Future Content",Toast.LENGTH_SHORT).show()
        }
    }

}