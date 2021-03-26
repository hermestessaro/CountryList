package com.example.countrylist.di

import com.example.countrylist.model.CountriesService
import com.example.countrylist.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    //função inject recebe como parametro a classe onde vai injetar os dados
    fun inject(service: CountriesService)

    fun inject(viewModel: ListViewModel)
}