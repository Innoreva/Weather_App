package com.ersubhadip.instantweather.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ersubhadip.instantweather.R

class ForecastAdapter(val context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_hour_forecast, parent, false)
        return ForecastViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ForecastViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val tvTemperature = itemView.findViewById<TextView>(R.id.tvTemperature)
        val tvHour = itemView.findViewById<TextView>(R.id.tvHour)
        val weatherIcon = itemView.findViewById<ImageView>(R.id.weatherIcon)

    }
}
