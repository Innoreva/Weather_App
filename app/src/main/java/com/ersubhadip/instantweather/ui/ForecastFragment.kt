package com.ersubhadip.instantweather.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ersubhadip.instantweather.R
import com.ersubhadip.instantweather.adapters.ForecastAdapter
import com.ersubhadip.instantweather.api.RetrofitInstance
import com.ersubhadip.instantweather.databinding.FragmentForecastBinding
import com.ersubhadip.instantweather.pojos.ForecastAdapterModel
import com.ersubhadip.instantweather.viewmodel.ApiRepository
import com.ersubhadip.instantweather.viewmodel.ForecastViewModel
import com.ersubhadip.instantweather.viewmodel.ForecastViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ForecastFragment : Fragment() {
    private lateinit var binding: FragmentForecastBinding
    lateinit var viewModel: ForecastViewModel
    private lateinit var adapter: ForecastAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_forecast, container, false)
        val view = binding.root
        val repo = ApiRepository(RetrofitInstance)
        val factory = ForecastViewModelFactory(repo, context?.applicationContext as Application)
        viewModel = ViewModelProvider(this, factory)[ForecastViewModel::class.java]
        binding.vm = viewModel
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.lifecycleOwner = this
        viewModel.liveCity.observe(viewLifecycleOwner, Observer { it ->
            if (!it.isNullOrBlank()) {
                //Gives a Livedata of list of ForecastAdapterModel
                viewModel.getForecast()
//                binding.loadingBar = View.VISIBLE

                binding.loadingBar.visibility = View.VISIBLE
                //setting forecastList
                viewModel.forecastDetails.observe(viewLifecycleOwner, Observer {
                    val list: List<ForecastAdapterModel> = it

                    //setting data to adapter
                    binding.weatherForecastRv.layoutManager = LinearLayoutManager(context) //doubt
                    binding.weatherForecastRv.adapter = ForecastAdapter(list)
//                    lifecycleScope.launch(Dispatchers.Main) {
//                        delay(1000L)
                        binding.loadingBar.visibility = View.GONE
//                    }

                })
            }
        })

    }

}