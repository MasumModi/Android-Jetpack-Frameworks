package com.example.jetpack.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.jetpack.data.network.AuthApi
import com.example.jetpack.data.network.Resource
import com.example.jetpack.data.repository.AuthRepository
import com.example.jetpack.databinding.FragmentLoginBinding
import com.example.jetpack.ui.base.BaseFragment
import com.example.jetpack.ui.home.HomeActivity
import com.example.jetpack.utils.enable
import com.example.jetpack.utils.handleApiError
import com.example.jetpack.utils.startNewActivity
import com.example.jetpack.utils.visible
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible(false)
        binding.btnLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressBar.visible(it is Resource.Loading)
            binding.btnLogin.enable(true)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        it.value.user.access_token?.let { it1 -> viewModel.saveAuthToken(it1) }
                    }
                    requireActivity().startNewActivity(HomeActivity::class.java)
                }
                is Resource.Failure -> handleApiError(it)
            }
        })

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            binding.btnLogin.enable(false)
            viewModel.login(email, password)
        }

        binding.etPassword.addTextChangedListener {
            val email = binding.etEmail.text.toString().trim()
            binding.btnLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}