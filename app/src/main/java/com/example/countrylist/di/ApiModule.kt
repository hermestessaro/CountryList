package com.example.countrylist.di

import com.example.countrylist.model.CountriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) //transforma o JSON recebido em Country
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //transforma os dados de tipo Country em um tipo observável
            .build()
            .create(CountriesApi::class.java)
    }
}