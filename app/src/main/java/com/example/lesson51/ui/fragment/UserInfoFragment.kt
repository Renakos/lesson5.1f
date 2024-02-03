package com.example.lesson51.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lesson51.R
import com.example.lesson51.data.viewmodel.UserInfoViewModel
import com.example.lesson51.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<UserInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel.userInfoLiveData.observe(viewLifecycleOwner) {
            updateUI()
            Log.e("LiveDataMessage", "saw")
        }

        viewModel.loadUserInfo(
            requireActivity().getSharedPreferences(
                getString(R.string.preferences_name),
                Context.MODE_PRIVATE
            )
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditData()
    }

    private fun setupEditData() {
        binding.editData.setOnClickListener {
            findNavController().navigate(R.id.action_userInfoFragment_to_editUserInfoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI() {
        val sharedPreferences = requireActivity().getSharedPreferences(
            (R.string.preferences_name.toString()),
            Context.MODE_PRIVATE
        )
        binding.textViewEmail.text = sharedPreferences.getString("email", "")
        binding.textViewAge.text = sharedPreferences.getInt("age", 0).toString()
        binding.textViewPassword.text = sharedPreferences.getString("password", "")
        binding.textViewUsername.text = sharedPreferences.getString("username", "")
    }
}
