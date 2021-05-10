package com.example.jetpack.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.jetpack.data.network.AuthApi
import com.example.jetpack.data.network.Resource
import com.example.jetpack.data.repository.AuthRepository
import com.example.jetpack.databinding.FragmentLoginBinding
import com.example.jetpack.ui.base.BaseFragment
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        userPreferences.saveAuthToken(it.value.user.access_token)
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login failure", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            //@todo add validation
            viewModel.login(email, password)
        }

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))

}