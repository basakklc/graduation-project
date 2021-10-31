package com.example.mvvmdemoproject.data.repository

import com.example.mvvmdemoproject.data.network.ApiClient
import com.example.mvvmdemoproject.data.network.EtkinlikService
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.repository.EtkinlikGoruntulemeRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikGoruntulemeRepositoryImpl : EtkinlikGoruntulemeRepository{

    var etkinlikAPI = ApiClient.getClient().create(EtkinlikService::class.java)
    override fun showEtkinlik(id:Int): Single<EtkinlikGoruntulemeResponse> = etkinlikAPI.showEtkinlik(id)


}