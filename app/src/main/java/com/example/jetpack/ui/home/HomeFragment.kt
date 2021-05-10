package com.example.jetpack.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.jetpack.data.network.Resource
import com.example.jetpack.data.network.UserApi
import com.example.jetpack.data.reponses.auth.User
import com.example.jetpack.data.repository.UserRepository
import com.example.jetpack.databinding.FragmentHomeBinding
import com.example.jetpack.ui.base.BaseFragment
import com.example.jetpack.utils.visible
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visible(false)

        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visible(false)
                    updateUI(it.value.user)
                }
                is Resource.Loading -> {
                    binding.progressBar.visible(true)
                }
            }
        })

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun updateUI(user: User) {
        with(binding) {
            tvUserId.text = user.id.toString()
            tvUserEmail.text = user.email
            tvUserName.text = user.name
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getRepository(): UserRepository {
        val token = runBlocking { userPreferences.authToken.first() }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }

}