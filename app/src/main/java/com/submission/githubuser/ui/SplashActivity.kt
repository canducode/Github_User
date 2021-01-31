package com.submission.githubuser.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.submission.githubuser.R

class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_TIME_OUT: Long = 3000
    }

    @Suppress("DEPRECATION")
    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handler.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)

    }
}