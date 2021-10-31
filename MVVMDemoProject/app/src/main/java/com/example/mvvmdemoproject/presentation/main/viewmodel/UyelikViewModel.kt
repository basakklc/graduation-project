package com.example.mvvmdemoproject.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.domain.model.KayitOlRequest
import com.example.mvvmdemoproject.domain.model.KayitOlResponse
import com.example.mvvmdemoproject.domain.model.SifreYenilemeResponse
import com.example.mvvmdemoproject.domain.usecase.KullaniciIslemleriUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class UyelikViewModel (private val kullaniciIslemleriUseCase: KullaniciIslemleriUseCase): ViewModel(){


    private val compositeDisposable = CompositeDisposable()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    private val id = MutableLiveData<Int>()
    private val sifreYenilemeResponse = MutableLiveData<SifreYenilemeResponse>()
    private val kayitOlResponse = MutableLiveData<KayitOlResponse>()

    fun getError(): MutableLiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }

    fun giris(email:String,sifre:String){

        compositeDisposable.add(
            kullaniciIslemleriUseCase.uyeGirisi(email,sifre)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({id.postValue(it)},{})
        )

    }
    fun sifreYenileme(email:String,sifre:String,sifreTekrar:String){

        compositeDisposable.add(
            kullaniciIslemleriUseCase.sifreYenile(email,sifre,sifreTekrar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ sifreYenilemeResponse.postValue(it)},{})
        )

    }
    fun kayitOl(kayitOlRequest: KayitOlRequest){

        compositeDisposable.add(
            kullaniciIslemleriUseCase.kayitOl(kayitOlRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({kayitOlResponse.postValue(it)},{})
        )

    }
    fun getID(): MutableLiveData<Int>{
        return id
    }

    fun getSifreResponse(): MutableLiveData<SifreYenilemeResponse>{
        Log.e("uyelik",sifreYenilemeResponse.value.toString())

        return sifreYenilemeResponse

    }

    fun getKayitResponse(): MutableLiveData<KayitOlResponse>{
        return kayitOlResponse

    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }

}

/*

class EtkinlikGoruntulemeViewModel (private val etkinlikGoruntulemeUseCase: EtkinlikGoruntulemeUseCase,
                                    private val id:Int) : ViewModel(){

    private val etkinlik= MutableLiveData<EtkinlikGoruntulemeResponse>()

    //private val etkinlikID =  EtkinlikOlusturmaRequest

    init {
        fetchEtkinlik(id)
    }

    fun getEtkinlik(): MutableLiveData<EtkinlikGoruntulemeResponse> {
        return etkinlik
    }





    private fun fetchEtkinlik(id:Int) {
        compositeDisposable.add(
            etkinlikGoruntulemeUseCase.show(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({it->etkinlik.postValue(it)},{throwable ->etkinlik.postValue(null)})
        )
    }




}
 */