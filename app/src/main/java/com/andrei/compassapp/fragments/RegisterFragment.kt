package com.andrei.compassapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andrei.compassapp.R
import com.andrei.compassapp.databinding.FragmentRegisterBinding
import com.andrei.compassapp.utils.clean
import com.andrei.compassapp.utils.getTrimmedText
import com.andrei.compassapp.utils.reObserve
import com.andrei.compassapp.viewModels.AuthViewModel
import com.andrei.dataLayer.engine.repositories.AuthRepository

class RegisterFragment : Fragment() {
    private lateinit var binding:FragmentRegisterBinding

   private val authViewModel:AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        configureViews()
        binding.lifecycleOwner = viewLifecycleOwner
        attachRegistrationObserver()
        return binding.root
    }

    private fun attachRegistrationObserver() {
        authViewModel.registrationState.reObserve(viewLifecycleOwner, {
            if (it is AuthRepository.RegistrationResult.RegistrationSuccessful) {
                val action = RegisterFragmentDirections.actionGlobalConfirmationCodeFragment(it.username)
                findNavController().navigate(action)
            }
        })
    }

    private fun configureViews() {
        binding.screensRegisterFinishButton.setOnClickListener {
          if(areFieldsValid()){
              val username = binding.screensRegisterUsernameInput.getTrimmedText()
              val email = binding.screensRegisterEmailInput.getTrimmedText()
              val password = binding.screensRegisterPasswordInput.getTrimmedText()
              authViewModel.beginRegistration(username = username, email = email, password = password)
              cleanFields()
          }
        }
    }

    private fun cleanFields() {
        binding.apply {
            screensRegisterEmailInput.clean()
            screensRegisterPasswordInput.clean()
            screensRegisterReenterPasswordInput.clean()
            screensRegisterUsernameInput.clean()
        }
    }

    private fun areFieldsValid():Boolean{
        return true
    }

}