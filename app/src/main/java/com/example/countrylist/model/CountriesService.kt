package com.example.countrylist.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountriesService {

    private val BASE_URL = "https://raw.githubusercontent.com"
    private val api: CountriesApi

    init {
        api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //transforma o JSON recebido em Country
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //transforma os dados de tipo Country em um tipo observável
            .build()
            .create(CountriesApi::class.java)
    }

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}