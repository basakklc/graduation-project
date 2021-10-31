package com.example.mvvmdemoproject.domain.usecase

import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.repository.EtkinlikFiltrelemeRepository
import com.example.mvvmdemoproject.domain.repository.EtkinlikGoruntulemeRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikFiltrelemeUseCase (private val etkinlikFiltrelemeRepository: EtkinlikFiltrelemeRepository) {

    fun filterKapsam(kapsam:String): Single<List<EtkinlikListelemeResponse>> = etkinlikFiltrelemeRepository.filterKapsam(kapsam)
    fun filterTopluluk(topluluk:String): Single<List<EtkinlikListelemeResponse>> = etkinlikFiltrelemeRepository.filterTopluluk(topluluk)
}