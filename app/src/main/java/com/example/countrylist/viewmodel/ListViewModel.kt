package com.example.countrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrylist.model.Country

class ListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData = listOf(Country("Alderaan"),
                Country("Betelgeuse"),
                Country("Mars"),
                Country("Coruscant"),
                Country("Tatooine"),
                Country("Dagobah"),
                Country("Mustafar"),
                Country("Corellia"),
                Country("Naboo")
        )

        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }

}