package com.example.jetpack.data.network

import com.example.jetpack.data.reponses.auth.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse

}