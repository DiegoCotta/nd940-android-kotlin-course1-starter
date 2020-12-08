package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding


class LoginFragment : Fragment() {

    lateinit var binding: LoginFragmentBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.login_fragment,
            container,
            false
        )
        setHasOptionsMenu(true);


        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)

        setupObservables()
        setupClickButtons()


        return binding.root
    }

    private fun setupObservables() {
        viewModel.eventLogin.observe(this, Observer { response ->
            if (response) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                viewModel.loginDone()
            }
        })
        viewModel.eventLoginError.observe(this, Observer { errorString ->
            if (errorString != null) {
                Toast.makeText(requireContext(), errorString, Toast.LENGTH_SHORT).show()
                viewModel.errorDone()
            }
        })
        viewModel.eventNewUser.observe(this, Observer { response ->
            if (response) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
                viewModel.createUserDone()
            }
        })
    }

    private fun setupClickButtons() {
        binding.createButton.setOnClickListener {
            if (binding.userEmail.text.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.enter_email),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (binding.userPassword.text.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.enter_password),
                    Toast.LENGTH_SHORT
                ).show()
            }

            viewModel.createUser(
                binding.userEmail.text.toString(),
                binding.userPassword.text.toString()
            )
        }

        binding.loginButton.setOnClickListener {
            if (binding.userEmail.text.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.enter_email),
                    Toast.LENGTH_SHORT
                ).show()
            } else if (binding.userPassword.text.isBlank()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.enter_password),
                    Toast.LENGTH_SHORT
                ).show()
            }

            viewModel.login(
                binding.userEmail.text.toString(),
                binding.userPassword.text.toString()
            )
        }
    }
}

