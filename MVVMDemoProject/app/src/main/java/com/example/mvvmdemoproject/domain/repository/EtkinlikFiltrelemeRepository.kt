package com.example.mvvmdemoproject.domain.repository

import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import io.reactivex.rxjava3.core.Single

interface EtkinlikFiltrelemeRepository {
    fun filterKapsam(kapsam:String): Single<List<EtkinlikListelemeResponse>>
    fun filterTopluluk(topluluk:String): Single<List<EtkinlikListelemeResponse>>
}