package com.ersubhadip.instantweather.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:url")
fun ImageView.setImageViewResource( url:String?) {
    Glide.with(this.context).load(url).into(this)
}