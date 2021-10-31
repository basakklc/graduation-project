package com.example.mvvmdemoproject.domain.repository

import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaResponse
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface EtkinlikOlusturmaRepository {

    fun postEtkinlik(etkinlik:EtkinlikOlusturmaRequest): Single<EtkinlikOlusturmaResponse>
    fun postKulaniciEtkinlik(etkinlikId: Int?,kullaniciId:Int): Single<Unit>

    fun postEtkinlikKapsamlari(etkinlikId:Int?,toplulukId:List<Int>,kapsamId:Int): Single<Unit>

    fun postEtkinlikAdresi(etkinlikId:Int?, ilceSemtId:Int, acikAdres:String): Single<Unit>
    fun getIlceSemt(ilceId:Number,semtId:Number):Single<Int>

    fun postKonusmaci(konusmaciAdi:List<String>,konusmaciSoyadi:List<String>):Single<Array<Int>>
    fun postEtkinlikKonusmacisi(etkinlikId: Int?,konusmaciId:Array<Int>):Single<Unit>

    fun postIletisimAdresleri(iletisimAdresleri:List<String>):Single<Array<Int>>
    fun postEtkinlikIletisimAdresleri(etkinlikId: Int?,iletisimId:Array<Int>):Single<Unit>

    fun ilceninSemti(ilceId:Number):Single<List<String>>

    fun getSemt(semtAdi:String): Single<Int>
    fun getIlce(ilceAdi:String): Single<Int>

    fun getKapsamId(kapsamAdi:String): Single<Int>
    fun getToplulukId(toplulukAdi:List<String>): Single<List<Int>>


}