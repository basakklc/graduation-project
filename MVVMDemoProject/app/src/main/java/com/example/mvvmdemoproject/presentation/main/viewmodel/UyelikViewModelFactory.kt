package com.example.mvvmdemoproject.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.domain.usecase.KullaniciIslemleriUseCase

class UyelikViewModelFactory(private val kullaniciIslemleriUseCase: KullaniciIslemleriUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UyelikViewModel::class.java)) {
            return UyelikViewModel((kullaniciIslemleriUseCase)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}