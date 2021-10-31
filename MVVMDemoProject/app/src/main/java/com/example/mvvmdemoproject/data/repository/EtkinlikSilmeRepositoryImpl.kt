package com.example.mvvmdemoproject.data.repository

import com.example.mvvmdemoproject.data.network.ApiClient
import com.example.mvvmdemoproject.data.network.EtkinlikService
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.repository.EtkinlikSilmeRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikSilmeRepositoryImpl: EtkinlikSilmeRepository {

    var etkinlikAPI = ApiClient.getClient().create(EtkinlikService::class.java)

    override fun deleteEtkinlik(id:Int): Single<Unit> = etkinlikAPI.deleteEtkinlik(id)
}