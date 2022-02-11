package com.ersubhadip.instantweather.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ersubhadip.instantweather.R
import com.nitish.typewriterview.TypeWriterView
import com.nitish.typewriterview.TypeWriterView.OnAnimationChangeListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    //todo:ViewBinding not getting applied in SplashActivity - Do Later
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        //delay for splash
//        lifecycleScope.launch(Dispatchers.Main) {
//            delay(1000L)
//            val intent= Intent(this@SplashScreenActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//            //fix - nav component will be used later
//        }

        //TypeWriter Text
         val typing = findViewById<TypeWriterView>(R.id.typeTv)
        typing.animateText("Get Live Weather Report \nAnytime, Anywhere")
        typing.setCharacterDelay(100L)

        //some junk yet important - Intent after Animations completes
        typing.setOnAnimationChangeListener(OnAnimationChangeListener {
            val intent= Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })

    }
}