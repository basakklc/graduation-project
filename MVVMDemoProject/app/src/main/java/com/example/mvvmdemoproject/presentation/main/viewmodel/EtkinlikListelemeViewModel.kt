package com.example.mvvmdemoproject.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.ArrayList


class EtkinlikListelemeViewModel(private val etkinlikListelemeUseCase: EtkinlikListelemeUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val etkinlikler=MutableLiveData<List<EtkinlikListelemeResponse>>()
    private val kullaniciEtkinlikleri=MutableLiveData<List<EtkinlikListelemeResponse>>()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    private val id = MutableLiveData<Int>()
    private val kapsamlar = MutableLiveData<List<String>>()
    private val topluluklar = MutableLiveData<List<String>>()
    private val siralıEtkinlikler = MutableLiveData<List<EtkinlikListelemeResponse>>()
    val mapped : MutableList<MutableLiveData<List<EtkinlikListelemeResponse>>> = mutableListOf()
    private var kapsamaGoreResponse =MutableLiveData<List<EtkinlikListelemeResponse>>()
    private var toplulugaGoreResponse = MutableLiveData<List<EtkinlikListelemeResponse>>()


    init {
        fetchEtkinlikler()
    }


    private fun fetchEtkinlikler() {
        compositeDisposable.add(
            etkinlikListelemeUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({etkinlikList->etkinlikler.postValue(etkinlikList)},{throwable ->etkinlikler.postValue(arrayListOf())})
        )
    }

    fun fetchKullaniciEtkinlikleri() {
        compositeDisposable.add(
            etkinlikListelemeUseCase.getKullaniciEtkinlikleri(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({etkinlikList->kullaniciEtkinlikleri.postValue(etkinlikList)},{}) //etkinlikList->kullaniciEtkinlikleri.postValue(etkinlikList)
        )
    }

    fun etkinlikId(ad:String,ucret:Number,tarih:String,aciklama:String){
        compositeDisposable.add(
            etkinlikListelemeUseCase.getId(ad, ucret, tarih, aciklama)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({id.postValue(it)},{})
        )
    }
    private fun fetchTopluluklar() {
        compositeDisposable.add(
            etkinlikListelemeUseCase.getTopluluklar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({topluluklar.postValue(it)},{})
        )
    }
    private fun fetchKapsamlar() {
        compositeDisposable.add(
            etkinlikListelemeUseCase.getKapsamlar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({kapsamlar.postValue(it)},{})
        )
    }

    private fun filterTopluluk(topluluk:String) {
        compositeDisposable.add(
            etkinlikListelemeUseCase.filterTopluluk(topluluk)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({toplulugaGoreResponse.postValue(it)},{})
        )

    }
    private fun filterKapsam(kapsam: String){
        compositeDisposable.add(
            etkinlikListelemeUseCase.filterKapsam(kapsam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({kapsamaGoreResponse.postValue(it)},{})
        )
    }

    private fun getEtkinlikSiralama(ucret: String?,tarih: String?){
        compositeDisposable.add(
            etkinlikListelemeUseCase.getEtkinlikSiralama(ucret, tarih)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({siralıEtkinlikler.postValue(it)},{})
        )
    }



 //live data alınması
    fun getSiraliResponse(ucret:String?,tarih: String?): MutableLiveData<List<EtkinlikListelemeResponse>> {
        getEtkinlikSiralama(ucret, tarih)
        return siralıEtkinlikler
    }



    fun getKapsamResponse(kapsam: String): MutableLiveData<List<EtkinlikListelemeResponse>> {
        filterKapsam(kapsam)
        //mapped.add(1,kapsamaGoreResponse)
        return kapsamaGoreResponse
    }
    fun getToplulukResponse(topluluk:String) : MutableLiveData<List<EtkinlikListelemeResponse>>{
        filterTopluluk(topluluk)
        //mapped.add(0,toplulugaGoreResponse)
        return toplulugaGoreResponse
    }





/*
    fun mappedResponse():List<MutableLiveData<List<EtkinlikListelemeResponse>>>{

        val mapped : MutableList<MutableLiveData<List<EtkinlikListelemeResponse>>> = mutableListOf(EtkinlikListelemeResponse(),EtkinlikListelemeResponse())

        mapped.add(0,kapsamaGoreResponse)
        mapped.add(1,toplulugaGoreResponse)

        return mapped
    }
*/





    fun kullanicininEtkinlikleri(): MutableLiveData<List<EtkinlikListelemeResponse>> {
        return kullaniciEtkinlikleri
    }

    fun getEtkinlikler(): MutableLiveData<List<EtkinlikListelemeResponse>> {
        return etkinlikler
    }
    fun getTopluluklar(): MutableLiveData<List<String>> {
        fetchTopluluklar()
        return topluluklar
    }
    fun getKapsamlar(): MutableLiveData<List<String>> {
        fetchKapsamlar()
        return kapsamlar
    }
    fun getID(): MutableLiveData<Int> {
        return id
    }
    fun getError(): MutableLiveData<Boolean> {
        return repoLoadError
    }
    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }

}