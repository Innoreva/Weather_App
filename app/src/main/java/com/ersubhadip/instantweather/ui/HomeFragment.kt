package com.ersubhadip.instantweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ersubhadip.instantweather.R
import com.ersubhadip.instantweather.databinding.FragmentHomeBinding
import com.ersubhadip.instantweather.viewmodel.MainViewModel

class HomeFragment : Fragment() {
    lateinit var vm:MainViewModel
    lateinit var binding:FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false)
        val view = binding.root
        binding.mainViewModel = vm
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm= ViewModelProvider(this)[MainViewModel::class.java]

        //Live data observer
        vm.finalWeatherReport.observe(viewLifecycleOwner, Observer {
            //it -> CurrentModel

        })



    }

}