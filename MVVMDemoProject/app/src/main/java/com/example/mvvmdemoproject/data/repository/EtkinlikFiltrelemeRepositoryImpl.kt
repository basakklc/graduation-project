package com.example.mvvmdemoproject.data.repository

import com.example.mvvmdemoproject.data.network.ApiClient
import com.example.mvvmdemoproject.data.network.EtkinlikService
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.repository.EtkinlikFiltrelemeRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikFiltrelemeRepositoryImpl: EtkinlikFiltrelemeRepository {
    var etkinlikAPI = ApiClient.getClient().create(EtkinlikService::class.java)

    override fun filterKapsam(kapsam: String): Single<List<EtkinlikListelemeResponse>> = etkinlikAPI.getKapsamaGore(kapsam)
    override fun filterTopluluk(topluluk: String): Single<List<EtkinlikListelemeResponse>> = etkinlikAPI.getToplulugaGore(topluluk)

}