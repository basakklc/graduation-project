package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.domain.usecase.EtkinlikFiltrelemeUseCase
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGoruntulemeUseCase

class EtkinlikFiltrelemeViewModelFactory (private val etkinlikFiltrelemeUseCase: EtkinlikFiltrelemeUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EtkinlikFiltrelemeViewModel::class.java)) {
            return EtkinlikFiltrelemeViewModel(etkinlikFiltrelemeUseCase) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}