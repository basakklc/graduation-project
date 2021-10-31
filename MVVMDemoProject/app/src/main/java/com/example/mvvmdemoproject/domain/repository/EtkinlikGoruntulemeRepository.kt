package com.example.mvvmdemoproject.domain.repository

import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import io.reactivex.rxjava3.core.Single

interface EtkinlikGoruntulemeRepository {
    fun showEtkinlik(id:Int): Single<EtkinlikGoruntulemeResponse>



}