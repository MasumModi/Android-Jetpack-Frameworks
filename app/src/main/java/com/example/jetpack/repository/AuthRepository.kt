package com.example.jetpack.repository

import com.example.jetpack.network.AuthApi

class AuthRepository(private val api: AuthApi) : BaseRepository() {

    suspend fun login(email: String, password: String) = safeApiCall {
        api.login(email, password)
    }

}