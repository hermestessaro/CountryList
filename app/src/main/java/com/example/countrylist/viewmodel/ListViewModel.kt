package com.example.countrylist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.countrylist.di.DaggerApiComponent
import com.example.countrylist.model.CountriesService
import com.example.countrylist.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel: ViewModel() {

    @Inject
    lateinit var countriesService: CountriesService

    private val disposable = CompositeDisposable() //pra limpar a conexão pelo rxJava

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.postValue(true)
        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread()) //todo o processo vai ser feito em outra thread
                .observeOn(AndroidSchedulers.mainThread()) //o resultado do processo vai ser recebido na main thread
                .subscribeWith(object: DisposableSingleObserver<List<Country>>() { // esse trecho é pra definir o que vamos fazer com o que receber
                    override fun onSuccess(value: List<Country>?) {
                        countries.postValue(value)
                        countryLoadError.postValue(false)
                        loading.postValue(false)
                    }

                    override fun onError(e: Throwable?) {
                        countryLoadError.postValue(true)
                        loading.postValue(false)
                    }
                })
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}