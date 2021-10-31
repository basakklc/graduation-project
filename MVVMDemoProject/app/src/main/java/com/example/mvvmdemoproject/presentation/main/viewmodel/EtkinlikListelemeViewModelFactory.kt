package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase

class EtkinlikListelemeViewModelFactory(private val etkinlikListelemeUseCase: EtkinlikListelemeUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EtkinlikListelemeViewModel::class.java)) {
            return EtkinlikListelemeViewModel((etkinlikListelemeUseCase)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}