package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase

class EtkinlikGuncellemeViewModelFactory(private val etkinlikguncellemeUseCase: EtkinlikGuncellemeUseCase,
                                         private val id: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EtkinlikGuncellemeViewModel::class.java)) {
            return EtkinlikGuncellemeViewModel(etkinlikguncellemeUseCase,id) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}