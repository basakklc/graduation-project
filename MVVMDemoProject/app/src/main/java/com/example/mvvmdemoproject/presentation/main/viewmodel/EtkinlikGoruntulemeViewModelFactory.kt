package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGoruntulemeUseCase

class EtkinlikGoruntulemeViewModelFactory(private val etkinlikGoruntulemeUseCase: EtkinlikGoruntulemeUseCase,
                                          private val id:Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EtkinlikGoruntulemeViewModel::class.java)) {
            return EtkinlikGoruntulemeViewModel(etkinlikGoruntulemeUseCase,id) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}