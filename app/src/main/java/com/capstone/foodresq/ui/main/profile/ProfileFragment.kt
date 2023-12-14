package com.capstone.foodresq.ui.main.profile

import android.app.ProgressDialog.show
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.capstone.foodresq.R
import com.capstone.foodresq.data.remote.response.ProfileData
import com.capstone.foodresq.databinding.FragmentProfileBinding
import com.capstone.foodresq.ui.login.LoginActivity
import com.capstone.foodresq.ui.main.MainViewModel
import com.capstone.foodresq.ui.subscription.SubscriptionActivity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel.loading.observe(viewLifecycleOwner) { showLoading(it) }

        profileViewModel.profile().observe(viewLifecycleOwner) { profileData ->
            profileData?.let {
                updateUI(it)
            }
        }

        setSettingData()
    }

    private fun setSettingData() {
        binding.btnSubscription.setOnClickListener {
            startActivity(Intent(requireActivity(), SubscriptionActivity::class.java))
        }

        binding.btnSettings.setOnClickListener {
            showLogoutPopupMenu()
        }
    }

    private fun showLogoutPopupMenu() {
        PopupMenu(requireContext(), binding.btnSettings).apply {
            menuInflater.inflate(R.menu.menu_logout, menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_logout -> {
                        lifecycleScope.launch {
                            profileViewModel.logout()
                            startActivity(Intent(requireActivity(), LoginActivity::class.java))
                            requireActivity().finish()
                        }
                    }
                }
                true
            }
            show()
        }
    }

    private fun showLoading(load: Boolean) {
        binding.layoutLoading.progressBar.visibility = if (load) View.VISIBLE else View.GONE
        binding.tvProfileEmail.visibility = if (load) View.GONE else View.VISIBLE
        binding.tvProfileName.visibility = if (load) View.GONE else View.VISIBLE
        binding.tvProfileType.visibility = if (load) View.GONE else View.VISIBLE
    }

    private fun updateUI(profileData: ProfileData) {
        binding.tvProfileEmail.text = profileData.email
        binding.tvProfileName.text = profileData.name
        binding.tvProfileType.text = profileData.membership_type
        val backgroundTint1 = ContextCompat.getColorStateList(requireContext(), R.color.color_palette_5)
        val backgroundTint2 = ContextCompat.getColorStateList(requireContext(), R.color.color_palette_4)
        if (profileData.membership_type != "standard") {
            binding.tvProfileType.backgroundTintList = backgroundTint1
            binding.tvProfileType.backgroundTintMode = PorterDuff.Mode.SRC_IN
        } else {
            binding.tvProfileType.backgroundTintList = backgroundTint2
            binding.tvProfileType.backgroundTintMode = PorterDuff.Mode.SRC_IN
        }
    }
}


