package com.example.submission1aplikasigithubuser.ui.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.submission1aplikasigithubuser.databinding.ActivitySplashScreenBinding
import com.example.submission1aplikasigithubuser.ui.main.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var splashScreenActivity: ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenActivity = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashScreenActivity.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, delay)
    }

    companion object {
        private const val delay = 2000L
    }


}