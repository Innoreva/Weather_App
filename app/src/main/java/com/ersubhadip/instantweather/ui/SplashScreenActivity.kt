package com.ersubhadip.instantweather.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope

import com.ersubhadip.instantweather.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //TODO : If possible then use SplashScreen with theme/Splash API/Fragment (to keep app lightweight😁)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1000L)
            val intent= Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
            //fix - nav component will be used later
        }
    }
}