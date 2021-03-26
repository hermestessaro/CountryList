package com.example.countrylist.model

import io.reactivex.Single
import retrofit2.http.GET

interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>> //Single é um observavel que retorna uma variavel e dai fecha
}