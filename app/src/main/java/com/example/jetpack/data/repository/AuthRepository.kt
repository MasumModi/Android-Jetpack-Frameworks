package com.example.jetpack.data.repository

import com.example.jetpack.data.network.AuthApi

class AuthRepository(private val api: AuthApi) : BaseRepository() {

    suspend fun login(email: String, password: String) = safeApiCall {
        api.login(email, password)
    }

}