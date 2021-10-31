package com.example.mvvmdemoproject.domain.usecase

import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.repository.EtkinlikGoruntulemeRepository
import com.example.mvvmdemoproject.domain.repository.EtkinlikOlusturmaRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikGoruntulemeUseCase (private val etkinlikGoruntulemeRepository: EtkinlikGoruntulemeRepository){
    fun show(id:Int): Single<EtkinlikGoruntulemeResponse> = etkinlikGoruntulemeRepository.showEtkinlik(id)

}