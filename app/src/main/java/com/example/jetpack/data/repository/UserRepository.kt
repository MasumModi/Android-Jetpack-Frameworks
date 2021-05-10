package com.example.jetpack.data.repository

import com.example.jetpack.data.network.UserApi

class UserRepository(
    private val api: UserApi,
) : BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }
}