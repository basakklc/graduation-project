package com.example.mvvmdemoproject.data.repository

import com.example.mvvmdemoproject.data.network.ApiClient
import com.example.mvvmdemoproject.data.network.EtkinlikService
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.repository.EtkinlikListelemeRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikListelemeRepositoryImpl : EtkinlikListelemeRepository{

    var etkinlikAPI = ApiClient.getClient().create(EtkinlikService::class.java)

    override fun getEtkinlikler(): Single<List<EtkinlikListelemeResponse>> = etkinlikAPI.getEtkinlikler()
    override fun getKullaniciEtkinlikleri(kullaniciId: Int): Single<List<EtkinlikListelemeResponse>> = etkinlikAPI.getKullaniciEtkinlikleri(kullaniciId)

    override fun getId(ad:String,ucret:Number,tarih:String,aciklama:String): Single<Int> = etkinlikAPI.getId(ad,ucret,tarih,aciklama)
    override fun getTopluluklar(): Single<List<String>> = etkinlikAPI.getTopluluklar()
    override fun getKapsamlar(): Single<List<String>> = etkinlikAPI.getKapsamlar()
    override fun filterKapsam(kapsam: String): Single<List<EtkinlikListelemeResponse>> = etkinlikAPI.getKapsamaGore(kapsam)
    override fun filterTopluluk(topluluk: String): Single<List<EtkinlikListelemeResponse>> = etkinlikAPI.getToplulugaGore(topluluk)
    override fun getEtkinlikSiralama(ucret: String?, tarih: String?): Single<List<EtkinlikListelemeResponse>> = etkinlikAPI.getEtkinlikSiralama(ucret, tarih)


}