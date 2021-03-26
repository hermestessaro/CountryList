package com.example.countrylist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrylist.R
import com.example.countrylist.databinding.ActivityMainBinding
import com.example.countrylist.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    lateinit var binding: ActivityMainBinding
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.countriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.countries.observe(this, { countries ->
            binding.countriesRecyclerView.visibility = View.VISIBLE
            countriesAdapter.updateCountries(countries)
        })

        viewModel.countryLoadError.observe(this, { isError ->
            binding.listError.visibility = if(isError) View.VISIBLE else View.GONE
        })

        viewModel.loading.observe(this, { isLoading ->
            binding.loadingView.visibility = if(isLoading) View.VISIBLE else View.GONE
            if(isLoading){
                binding.listError.visibility = View.GONE
                binding.countriesRecyclerView.visibility = View.GONE
            }
        })
    }
}