package com.example.lesson51

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.lesson51.databinding.FragmentEditUserInfoBinding

class EditUserInfoFragment : Fragment() {
    private var _binding: FragmentEditUserInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EditUserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel = ViewModelProvider(this)[EditUserInfoViewModel::class.java]
        viewModel.initialize(requireContext())
        viewModel.loadUserInfo()

        binding.buttonSaveChanges.setOnClickListener {
            val newUsername = binding.editTextNewUsername.text.toString()
            val newAge = binding.editTextNewAge.text.toString().toInt()
            val newEmail = binding.editTextNewEmail.text.toString()
            val newPassword = binding.editTextNewPassword.text.toString()

            viewModel.saveChanges(newUsername, newAge, newEmail, newPassword)
            findNavController().navigateUp()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
