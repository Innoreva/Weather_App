package com.ersubhadip.instantweather.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ersubhadip.instantweather.R
import com.ersubhadip.instantweather.databinding.ItemHourForecastBinding
import com.ersubhadip.instantweather.pojos.ForecastAdapterModel

class ForecastAdapter(private val list: List<ForecastAdapterModel>):RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    lateinit var binding:ItemHourForecastBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_hour_forecast,parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position].date, list[position].temp, list[position].icon)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(private val binding: ItemHourForecastBinding):RecyclerView.ViewHolder(binding.root){

        fun bindData( date:String,temp:String, url:String){
            binding.tvHour.text = "Time: ${date.substring(11)}"
            binding.tvTemperature.text = temp
            Glide.with(binding.root).load("https:$url").into(binding.weatherIcon)
        }

    }
}