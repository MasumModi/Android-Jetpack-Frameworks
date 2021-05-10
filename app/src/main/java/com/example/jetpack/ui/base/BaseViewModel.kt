package com.example.jetpack.ui.base

import androidx.lifecycle.ViewModel
import com.example.jetpack.data.network.UserApi
import com.example.jetpack.data.repository.BaseRepository

abstract class BaseViewModel(
    private val baseRepository: BaseRepository
) : ViewModel() {

    suspend fun logout(api: UserApi) = baseRepository.logout(api)

}