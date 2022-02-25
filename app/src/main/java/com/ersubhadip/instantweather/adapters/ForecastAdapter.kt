package com.ersubhadip.instantweather.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ersubhadip.instantweather.R
import com.ersubhadip.instantweather.databinding.ItemHourForecastBinding
import com.ersubhadip.instantweather.pojos.ForecastAdapterModel

class ForecastAdapter(private val list: List<ForecastAdapterModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: ItemHourForecastBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_hour_forecast,parent,false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hour_forecast, parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        holder.bindData
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ForecastViewHolder(private val binding: ItemHourForecastBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(date:String,temp:String, icon:String) {
            binding.tvHour.text = date
            binding.tvTemperature.text = temp
            Glide.with(itemView).load(icon).into(binding.weatherIcon)
        }
    }
}
