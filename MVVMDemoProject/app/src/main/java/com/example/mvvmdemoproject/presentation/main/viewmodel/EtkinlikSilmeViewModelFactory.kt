package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.domain.usecase.EtkinlikSilmeUseCase

class EtkinlikSilmeViewModelFactory(private val etkinlikSilmeUseCase: EtkinlikSilmeUseCase,
                                    private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EtkinlikSilmeViewModel::class.java)) {
            return EtkinlikSilmeViewModel(etkinlikSilmeUseCase,id) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}