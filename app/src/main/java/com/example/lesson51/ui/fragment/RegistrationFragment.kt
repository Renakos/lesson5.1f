package com.example.lesson51.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.lesson51.R
import com.example.lesson51.data.viewmodel.RegistrationViewModel
import com.example.lesson51.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {
    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RegistrationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val view = binding.root

        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preferences_name),
            Context.MODE_PRIVATE
        )

        val isRegistered = sharedPreferences.getBoolean(getString(R.string.key_registered), false)


        if (!isRegistered) {
            binding.buttonRegister.setOnClickListener {
                val username = binding.editTextUsername.text.toString()
                val age = binding.editTextAge.text.toString().toInt()
                val email = binding.editTextEmail.text.toString()
                val password = binding.editTextPassword.text.toString()
                viewModel.initialize(sharedPreferences)
                viewModel.registerUser(username, age, email, password)


                findNavController().navigate(R.id.action_registrationFragment_to_userInfoFragment)
            }
        } else {

            findNavController().navigate(R.id.action_registrationFragment_to_userInfoFragment)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
