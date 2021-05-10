package com.example.jetpack.ui.splash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.jetpack.R
import com.example.jetpack.data.UserPreferences
import com.example.jetpack.ui.auth.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userPreferences = UserPreferences(this)
        userPreferences.authToken.asLiveData().observe(this, Observer {
            Toast.makeText(this, it ?: "Token is null", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, AuthActivity::class.java))
        })

        /*finish()
        startActivity(Intent(this, AuthActivity::class.java))*/
    }
}