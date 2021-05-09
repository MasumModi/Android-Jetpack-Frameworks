package com.example.jetpack.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jetpack.data.network.AuthApi
import com.example.jetpack.data.repository.AuthRepository
import com.example.jetpack.databinding.FragmentLoginBinding
import com.example.jetpack.ui.base.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))

}