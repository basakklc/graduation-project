package com.example.mvvmdemoproject.domain.repository

import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import io.reactivex.rxjava3.core.Single

interface EtkinlikListelemeRepository {
    fun getEtkinlikler():Single<List<EtkinlikListelemeResponse>>
    fun getKullaniciEtkinlikleri(kullaniciId:Int):Single<List<EtkinlikListelemeResponse>>
    fun getId(ad:String,ucret:Number,tarih:String,aciklama:String): Single<Int>
    fun getTopluluklar(): Single<List<String>>
    fun getKapsamlar():Single<List<String>>
    fun filterKapsam(kapsam: String): Single<List<EtkinlikListelemeResponse>>
    fun filterTopluluk(topluluk: String): Single<List<EtkinlikListelemeResponse>>
    fun getEtkinlikSiralama(ucret:String?,tarih:String?): Single<List<EtkinlikListelemeResponse>>
}