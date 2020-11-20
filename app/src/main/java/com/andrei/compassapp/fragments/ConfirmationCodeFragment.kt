package com.andrei.compassapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.andrei.compassapp.databinding.FragmentConfirmationCodeBinding
import com.andrei.compassapp.utils.getTrimmedText
import com.andrei.compassapp.viewModels.AuthViewModel

class ConfirmationCodeFragment : Fragment() {

    private val authViewModel:AuthViewModel by activityViewModels()
    private lateinit var binding:FragmentConfirmationCodeBinding
    private val args:ConfirmationCodeFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfirmationCodeBinding.inflate(inflater,container,false)
        configureViews()
        binding.viewModel = authViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


    private fun configureViews() {
        binding.screensConfirmationCodeFinishButton.setOnClickListener {
            val code = binding.screensConfirmationCodeInput.getTrimmedText()
            if(code.isNotEmpty()){
                authViewModel.confirmAccount(code = code,
                username = args.username )
            }
        }
    }

}