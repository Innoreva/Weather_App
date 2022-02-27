package com.ersubhadip.instantweather.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.ersubhadip.instantweather.R
import com.nitish.typewriterview.TypeWriterView
import com.nitish.typewriterview.TypeWriterView.OnAnimationChangeListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        //initialized Animation Utility Class
        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)

        //Icon Animation
        findViewById<LottieAnimationView>(R.id.weatherAnimation).startAnimation(animation)

        //Heading Text Animation
        findViewById<TextView>(R.id.headTv).startAnimation(animation)


        //TypeWriter Text
        val typing = findViewById<TypeWriterView>(R.id.typeTv)
        typing.animateText("Get Live Weather Report \nAnytime, Anywhere")
        typing.setCharacterDelay(100L)

        //some junk yet important - Intent after Animations completes
        typing.setOnAnimationChangeListener(OnAnimationChangeListener {
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}