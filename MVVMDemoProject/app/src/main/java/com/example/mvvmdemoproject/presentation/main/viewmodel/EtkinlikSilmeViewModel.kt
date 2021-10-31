package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.domain.usecase.EtkinlikSilmeUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EtkinlikSilmeViewModel (private val etkinlikSilmeUseCase: EtkinlikSilmeUseCase,
                              private val id:Int) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val complete = MutableLiveData<Boolean>()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    init {
        deleteEtkinlik(id)
    }

    fun getEtkinlik(): MutableLiveData<Boolean>{//MutableLiveData<EtkinlikGoruntulemeResponse> {
        return complete
    }

    fun getError(): MutableLiveData<Boolean> {
        return repoLoadError
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }

    private fun deleteEtkinlik(id:Int) {
        compositeDisposable.add(
            etkinlikSilmeUseCase.deleteEtkinlik(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({complete.postValue(true)},{throwable ->complete.postValue(null)})
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }

}