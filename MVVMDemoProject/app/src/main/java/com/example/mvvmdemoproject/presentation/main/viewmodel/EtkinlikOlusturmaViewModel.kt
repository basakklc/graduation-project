package com.example.mvvmdemoproject.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.data.network.ApiClient
import com.example.mvvmdemoproject.data.network.EtkinlikService
import com.example.mvvmdemoproject.domain.model.EtkinlikFormModel
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikOlusturmaUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class EtkinlikOlusturmaViewModel(private val etkinlikOlusturmaUseCase: EtkinlikOlusturmaUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val etkinlikler = MutableLiveData<List<EtkinlikListelemeResponse>>()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    private val semt = MutableLiveData<List<String>>()

    private val i_id = MutableLiveData<Int>()
    private val s_id = MutableLiveData<Int>()
    private val kapsam = MutableLiveData<Int>()
    private val topluluk = MutableLiveData<List<Int>>()

    lateinit var etkinlikFormModel:EtkinlikFormModel
    lateinit var kulaniciID:Number


    /*var etkinlikFormModel = EtkinlikFormModel("yakacık", "yenii", "2021-04-23", "asdfghj", 65,
        1, 1, listOf(1, 2, 3), listOf(1, 2), listOf("basak", "beyza"), listOf("klc", "cbn"), arrayOf("bskgmail", "byzgmail"))
    var etkinlikOlusturmaRequest = EtkinlikOlusturmaRequest("yenii", "asdfghj", "2021-04-23", 65)*/


    /*init {
        createEtkinlik(etkinlikOlusturmaRequest)
    }*/
    fun createEtkinlik(etkinlikOlusturmaRequest: EtkinlikOlusturmaRequest): Single<Boolean> {
        compositeDisposable.add(
            etkinlikOlusturmaUseCase.postEtkinlik(etkinlikOlusturmaRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({create(it.etkinlik)},{t: Throwable -> t.message   })
        )

        return Single.just(true)
    }

    private fun  create(id:Int?) : Single<Boolean> {
        val x  = Single.merge(
            etkinlikOlusturmaUseCase.getIlceSemt(etkinlikFormModel.ilceId,etkinlikFormModel.semtId).flatMap {
                etkinlikOlusturmaUseCase.postEtkinlikAdresi(id,it,etkinlikFormModel.adres) }.subscribeOn(Schedulers.io()),
            etkinlikOlusturmaUseCase.postEtkinlikKapsamlari(id, etkinlikFormModel.toplulukList,etkinlikFormModel.kapsamList.get(0)).subscribeOn(Schedulers.io()),
            etkinlikOlusturmaUseCase.postKonusmaci(etkinlikFormModel.konusmaciAd, etkinlikFormModel.konusmaciSoyad).flatMap {
                etkinlikOlusturmaUseCase.postEtkinlikKonusmacisi(id,it)}.subscribeOn(Schedulers.io()),
            etkinlikOlusturmaUseCase.postIletisimAdresleri(etkinlikFormModel.iletisimAdresi.toList()).flatMap {
                etkinlikOlusturmaUseCase.postEtkinlikIletisimAdresleri(id,it)}.subscribeOn(Schedulers.io())
        )

            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        Log.e("Kview model",kulaniciID.toString())

        val y= etkinlikOlusturmaUseCase.postKullaniciEtkinlik(id,kulaniciID.toInt())
                             .subscribeOn(Schedulers.io())
                             .observeOn(AndroidSchedulers.mainThread())
                             .subscribe()
        return Single.just(true)

    }
    fun getKapsamID(etkinlikKapsami: String)   {
        compositeDisposable.add(
            etkinlikOlusturmaUseCase.getKapsamId(etkinlikKapsami)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({kapsam.postValue(it)},{t: Throwable -> t.message  })
        )
    }

    fun getToplulukID(etkinlikToplulugu: List<String>) {
        var x = etkinlikOlusturmaUseCase.getToplulukId(etkinlikToplulugu)
            .subscribe({topluluk.postValue(it)},{})//Log.e("getID",kapsamId.toString())})

    }
    fun getIlceID(ilce:String) {
        var x = etkinlikOlusturmaUseCase.getIlce(ilce)
            .subscribe({i_id.postValue(it)},{})//Log.e("getID",kapsamId.toString())})

    }
    fun getSemtID(semt:String) {
        var x = etkinlikOlusturmaUseCase.getSemt(semt)
            .subscribe({s_id.postValue(it)},{})//Log.e("getID",kapsamId.toString())})

    }


    fun adres(ilce:String,semt:String){
        getIlceID(ilce)
        getSemtID(semt)
    }



    fun ilceninSemti(ilce_id:Number){
        compositeDisposable.add(
            etkinlikOlusturmaUseCase.ilceninSemti(ilce_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ semt.postValue(it)  },{ throwable -> })//Log.e("getID",kapsamId.toString())})

        )
    }

    fun getEtkinlikler(): MutableLiveData<List<EtkinlikListelemeResponse>> {
        return etkinlikler
    }
    fun fetchToplulukID(topluluklar:List<String>): MutableLiveData<List<Int>> {
        getToplulukID(topluluklar)
        return topluluk
    }
    fun fetchSemtID(): MutableLiveData<Int> {
        return s_id
    }
    fun fetchIlceID(): MutableLiveData<Int> {
        return i_id
    }
    fun fetchKapsamID(kapsamAd:String): MutableLiveData<Int> {
        getKapsamID(kapsamAd)
        return kapsam
    }

    fun getError(): MutableLiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }
    fun getSemt(ilce:Int): MutableLiveData<List<String>>{
        ilceninSemti(ilce)
        return semt
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }



}

/*

    private fun setKapsamId(id: Int) : Single<Boolean>{
        kapsam = id

        Log.e("kapsamID-viewmodel",kapsam.toString())
        return Single.just(true)
    }
    private fun setToplulukId(id: List<Int>) :Single<Boolean> {
        topluluk = id
        return Single.just(true)
    }
    fun setIlceID(id:Int):Single<Boolean>{
        i_id=id
        return Single.just(true)
    }
    fun setSemtID(id:Int):Single<Boolean>{
        s_id=id
        return Single.just(true)
    }

 */