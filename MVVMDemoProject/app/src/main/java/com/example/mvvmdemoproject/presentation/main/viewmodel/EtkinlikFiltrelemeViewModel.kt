package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikFiltrelemeUseCase
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGoruntulemeUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EtkinlikFiltrelemeViewModel (private val etkinlikFiltrelemeUseCase: EtkinlikFiltrelemeUseCase) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val etkinlikler= MutableLiveData<List<EtkinlikListelemeResponse>>()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    init {
        fetchEtkinlikler()
    }

    fun getEtkinlik(): MutableLiveData<List<EtkinlikListelemeResponse>> {
        return etkinlikler
    }

    fun getError(): MutableLiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }

    private fun fetchEtkinlikler() {
        compositeDisposable.add(
            etkinlikFiltrelemeUseCase.filterKapsam("Teknoloji,Biyoloji")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({it->etkinlikler.postValue(it)},{throwable ->etkinlikler.postValue(null)})
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }


}