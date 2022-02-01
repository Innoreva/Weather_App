package com.ersubhadip.instantweather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //todo : If possible then use SplashScreen with theme/Splash API/Fragment (to keep app lightweight😁)
        lifecycleScope.launch {
            delay(1000L)
            val intent= Intent(this@SplashScreenActivity,MainActivity::class.java)
            startActivity(intent)
            finish()//remove this Activity from Task stack
        }
    }
}