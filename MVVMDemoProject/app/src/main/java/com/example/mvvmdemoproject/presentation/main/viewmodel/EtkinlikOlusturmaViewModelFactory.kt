package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.data.repository.EtkinlikOlusturmaRepositoryImpl
import com.example.mvvmdemoproject.domain.usecase.EtkinlikOlusturmaUseCase

class EtkinlikOlusturmaViewModelFactory(private val etkinlikOlusturmaUseCase: EtkinlikOlusturmaUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EtkinlikOlusturmaViewModel::class.java)) {
            return EtkinlikOlusturmaViewModel((etkinlikOlusturmaUseCase)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}