package com.example.countrylist.di

import com.example.countrylist.model.CountriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CountriesService)
}