package com.app.woocerassignment.ui.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.app.woocerassignment.R
import com.app.woocerassignment.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInButton.setOnClickListener {
            viewModel.signIn(
                name = binding.nameEditText.text.toString(),
                email = binding.emailEditText.text.toString(),
                website = binding.websiteEditText.text.toString(),
                username = binding.usernameEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )
        }

        registerObservers()
    }

    private fun registerObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.message.collect {
                Toast.makeText(requireContext(), it, LENGTH_LONG).show()
            }
            viewModel.signInCompleted.collect {

            }
        }
    }
}