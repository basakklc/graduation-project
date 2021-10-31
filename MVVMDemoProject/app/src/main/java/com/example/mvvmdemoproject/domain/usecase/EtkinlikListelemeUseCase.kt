package com.example.mvvmdemoproject.domain.usecase

import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.repository.EtkinlikListelemeRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikListelemeUseCase(private val etkinlikListelemeRepository: EtkinlikListelemeRepository )  {
    operator fun invoke():Single<List<EtkinlikListelemeResponse>> = etkinlikListelemeRepository.getEtkinlikler()
    fun getKullaniciEtkinlikleri(kullaniciId: Int): Single<List<EtkinlikListelemeResponse>> = etkinlikListelemeRepository.getKullaniciEtkinlikleri(kullaniciId)

    fun getId(ad:String,ucret:Number,tarih:String,aciklama:String): Single<Int> = etkinlikListelemeRepository.getId(ad, ucret, tarih, aciklama)

    fun getTopluluklar(): Single<List<String>> = etkinlikListelemeRepository.getTopluluklar()
    fun getKapsamlar(): Single<List<String>> = etkinlikListelemeRepository.getKapsamlar()
    fun filterKapsam(kapsam: String): Single<List<EtkinlikListelemeResponse>> = etkinlikListelemeRepository.filterKapsam(kapsam)
    fun filterTopluluk(topluluk: String): Single<List<EtkinlikListelemeResponse>> = etkinlikListelemeRepository.filterTopluluk(topluluk)
    fun getEtkinlikSiralama(ucret:String?,tarih:String?): Single<List<EtkinlikListelemeResponse>> = etkinlikListelemeRepository.getEtkinlikSiralama(ucret, tarih)

}