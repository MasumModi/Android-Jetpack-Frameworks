package com.example.jetpack.data.repository

import com.example.jetpack.data.UserPreferences
import com.example.jetpack.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi,
    private val preferences: UserPreferences
    ) : BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

    suspend fun saveAuthToken(
        token: String
    ){
        preferences.saveAuthToken(token)
    }
}