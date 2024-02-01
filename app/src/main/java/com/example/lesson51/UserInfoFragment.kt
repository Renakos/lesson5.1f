package com.example.lesson51

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lesson51.databinding.FragmentUserInfoBinding

class UserInfoFragment : Fragment() {
    private var _binding: FragmentUserInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[UserInfoViewModel::class.java]

        viewModel.userInfoLiveData.observe(viewLifecycleOwner) { userInfo ->
            updateUI(userInfo)
        }

        viewModel.loadUserInfo(requireContext())

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditData()
    }

    private fun setupEditData() = with(binding) {
        editData.setOnClickListener {
            findNavController().navigate(R.id.action_userInfoFragment_to_editUserInfoFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUI(userInfo: UserInfo) {
        binding.textViewUsername.text = userInfo.username
        binding.textViewAge.text = userInfo.age.toString()
        binding.textViewEmail.text = userInfo.email
        binding.textViewPassword.text = userInfo.password
    }
}
