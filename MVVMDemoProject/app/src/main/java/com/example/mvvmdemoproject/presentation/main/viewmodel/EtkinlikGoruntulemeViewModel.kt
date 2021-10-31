package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.databinding.ActivityEtkinlikGoruntulemeBinding
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGoruntulemeUseCase
import com.example.mvvmdemoproject.presentation.main.view.EtkinlikGoruntulemeActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EtkinlikGoruntulemeViewModel (private val etkinlikGoruntulemeUseCase: EtkinlikGoruntulemeUseCase,
                                    private val id:Int) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val etkinlik= MutableLiveData<EtkinlikGoruntulemeResponse>()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()
    //private val etkinlikID =  EtkinlikOlusturmaRequest

    init {
        fetchEtkinlik(id)
    }

    fun getEtkinlik(): MutableLiveData<EtkinlikGoruntulemeResponse> {
        return etkinlik
    }

    fun getError(): MutableLiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }




    private fun fetchEtkinlik(id:Int) {
        compositeDisposable.add(
            etkinlikGoruntulemeUseCase.show(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({it->etkinlik.postValue(it)},{throwable ->etkinlik.postValue(null)})
        )
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }

}