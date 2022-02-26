package com.ersubhadip.instantweather.ui

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ersubhadip.instantweather.R
import com.ersubhadip.instantweather.api.RetrofitInstance
import com.ersubhadip.instantweather.databinding.FragmentHomeBinding
import com.ersubhadip.instantweather.viewmodel.ApiRepository
import com.ersubhadip.instantweather.viewmodel.MainViewModel
import com.ersubhadip.instantweather.viewmodel.MainViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    lateinit var vm: MainViewModel
    lateinit var binding: FragmentHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view = binding.root
        val repo = ApiRepository(RetrofitInstance)
        val factory = MainViewModelFactory(repo, context?.applicationContext as Application)


        vm = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding.mainViewModel = vm
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Live data observer
        vm.url.observe(viewLifecycleOwner, Observer {
            context?.let { it1 ->
                Glide.with(it1.applicationContext).load(it).into(binding.weatherImage)
            }
        })


        binding.lifecycleOwner = this

        vm.liveCity.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank()) {
                // fake loading
                binding.progressBar.visibility = View.VISIBLE

                CoroutineScope(Dispatchers.IO).launch {
                    vm.getCurrentWeatherVM()
                    withContext(Dispatchers.Main) {
                        binding.progressBar.visibility = View.GONE

                    }
                }


            }
        })

        binding.refreshBtn.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch{
                vm.getCurrentWeatherVM()
                withContext(Dispatchers.Main){

            CoroutineScope(Dispatchers.IO).launch {
                vm.updateWeather()
                withContext(Dispatchers.Main) {

                    binding.progressBar.visibility = View.GONE
                }
            }

        }
    }

}