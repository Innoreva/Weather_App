package com.ersubhadip.instantweather.ui

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ersubhadip.instantweather.R
import com.ersubhadip.instantweather.api.RetrofitInstance
import com.ersubhadip.instantweather.databinding.FragmentSearchBinding
import com.ersubhadip.instantweather.viewmodel.ApiRepository
import com.ersubhadip.instantweather.viewmodel.SearchViewModel
import com.ersubhadip.instantweather.viewmodel.SearchViewModelFactory


class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        val view = binding.root
        val repo = ApiRepository(RetrofitInstance)
        val factory = SearchViewModelFactory(repo, context?.applicationContext as Application)
        viewModel = ViewModelProvider(this, factory)[SearchViewModel::class.java]
        binding.searchViewModel = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        binding.searchBtn.setOnClickListener {

            if (!TextUtils.isEmpty(binding.searchBar.text)) {
                //Loading starts
                binding.indeterminateProgressBar.visibility = View.VISIBLE
                //todo:Fake loading
                //Making Invisible
                binding.searchConstraints.setBackgroundColor(Color.parseColor("#0069c0"))

                //Visible the weather Report
                viewModel.getWeather()
                viewModel.imageUrl.observe(viewLifecycleOwner, Observer {
                    context?.let { it1 ->
                        Glide.with(it1.applicationContext).load(it).into(binding.weatherImage)
                    }
                })
                binding.outputCard.visibility = View.VISIBLE
                binding.indeterminateProgressBar.visibility = View.GONE
            } else {
                binding.searchBar.error = "Please enter a place"
            }

        }

        binding.searchBar.setOnClickListener {

            //Removing the card on another search
            binding.searchConstraints.setBackgroundDrawable(context?.let { it1 ->
                ContextCompat.getDrawable(
                    it1.applicationContext, R.drawable.ic_bg
                )
            })
            binding.outputCard.visibility = View.GONE

        }


    }


}