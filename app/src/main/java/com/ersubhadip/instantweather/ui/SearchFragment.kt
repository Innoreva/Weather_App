package com.ersubhadip.instantweather.ui

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ersubhadip.instantweather.R
import com.ersubhadip.instantweather.api.RetrofitInstance
import com.ersubhadip.instantweather.databinding.FragmentSearchBinding
import com.ersubhadip.instantweather.viewmodel.ApiRepository
import com.ersubhadip.instantweather.viewmodel.SearchViewModel
import com.ersubhadip.instantweather.viewmodel.SearchViewModelFactory
import kotlinx.coroutines.*


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
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


            // keyboard will get disappeared after click
            val inputMethodManager = getSystemService(
                requireContext(),
                InputMethodManager::class.java
            ) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

            //loading starts
            binding.indeterminateProgressBar.visibility = View.VISIBLE

            binding.indeterminateProgressBar.visibility = View.VISIBLE

            CoroutineScope(Dispatchers.IO).launch {
                if (!TextUtils.isEmpty(binding.searchBar.text)) {
                    //Loading starts
                    binding.searchConstraints.setBackgroundColor(Color.parseColor("#0069c0"))
                    binding.indeterminateProgressBar.visibility = View.VISIBLE

                    lifecycleScope.launch((Dispatchers.IO)) {

                        viewModel.getWeather()
                        withContext(Dispatchers.Main) {
                            viewModel.imageUrl.observe(viewLifecycleOwner, Observer {
                                context?.let { it1 ->
                                    Glide.with(it1.applicationContext).load(it)
                                        .into(binding.weatherImage)
                                }
                            })
                            binding.outputCard.visibility = View.VISIBLE



                            delay(1000L)
                            binding.indeterminateProgressBar.visibility = View.GONE

                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        binding.searchBar.error = "Please enter a place"
                        binding.indeterminateProgressBar.visibility = View.GONE
                    }
                }
            }
        }
        binding.searchBar.setOnClickListener {
            binding.outputCard.visibility = View.GONE
        }
    }
}
