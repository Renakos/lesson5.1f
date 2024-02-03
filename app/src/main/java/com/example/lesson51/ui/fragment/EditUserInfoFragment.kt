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
import com.example.lesson51.data.model.UserInfo
import com.example.lesson51.data.viewmodel.EditUserInfoViewModel
import com.example.lesson51.databinding.FragmentEditUserInfoBinding

class EditUserInfoFragment : Fragment() {
    private var _binding: FragmentEditUserInfoBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<EditUserInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditUserInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.buttonSaveChanges.setOnClickListener {
            val newUsername = binding.editTextNewUsername.text.toString()
            val newAge = binding.editTextNewAge.text.toString().toInt()
            val newEmail = binding.editTextNewEmail.text.toString()
            val newPassword = binding.editTextNewPassword.text.toString()
            viewModel.saveChanges(
                newUsername,
                newAge,
                newEmail,
                newPassword,
                requireActivity().getSharedPreferences(
                    (R.string.preferences_name.toString()),
                    Context.MODE_PRIVATE
                )
            )
            viewModel.initialize(UserInfo(newUsername, newAge, newEmail, newPassword))
            Log.e("DataEntered", "Data: $newUsername, $newAge, $newEmail, $newPassword")
            findNavController().navigateUp()
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
