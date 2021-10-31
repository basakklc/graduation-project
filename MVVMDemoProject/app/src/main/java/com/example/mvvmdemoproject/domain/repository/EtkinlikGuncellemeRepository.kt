package com.example.mvvmdemoproject.domain.repository

import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikGuncellemeRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Field

interface EtkinlikGuncellemeRepository {

    fun bindingEtkinlik(id:Int): Single<EtkinlikGoruntulemeResponse>

    fun updateEtkinlik(id:Int,etkinlik:EtkinlikOlusturmaRequest) : Single<EtkinlikOlusturmaResponse>

    fun getKapsamId(kapsamAdi:String): Single<Int>
    fun getToplulukId(toplulukAdi:List<String>): Single<List<Int>>
    fun updateEtkinlikKapsami(etkinlikId:Int,etkinlikGuncellemeRequest: EtkinlikGuncellemeRequest):Single<Unit>

    fun getIletisimId(etkinlikId: Int):Single<List<Int>>
    fun getIletisimAdresi(etkinlikId: Int): Single<List<String>>
    fun modifyIletisimAdresi(iletisimAdresi:List<String>,iletisimId:List<Int>):Single<Unit>
    fun deleteIletisimAdresi(adres: String): Single<Unit>

    fun getKonusmaciId(etkinlikId:Int) : Single<List<Int>>
    fun getKonusmaciAdi(etkinlikId: Int):Single<List<String>>
    fun getKonusmaciSoyadi(etkinlikId:Int):Single<List<String>>
    fun modifyKonusmaciAdSoyad(guncelKonusmaciAdi:List<String>, guncelKonusmaciSoyadi: List<String>, konusmaciId: List<Int>): Single<Unit>
    fun deleteKonusmaciAdSoyad(adi:String,soyadi:String):Single<Unit>

    fun getIlceSemtId(ilceId:Int, semtId:Int): Single<Int>
    fun getAdresId(etkinlikId:Int): Single<Int>
    fun getSemt(semtAdi:String): Single<Int>
    fun getIlce(ilceAdi:String): Single<Int>
    fun modifyAdres(ilceSemtId:Int, acikAdres: String, adresId: Int): Single<Unit>
    fun ilceninSemti(ilceId:Number):Single<List<String>>

    fun createIletisimAdresi(iletisimAdresi: List<String>) :Single<Array<Int>>
    fun createEtkinlikIletisimAdresleri(etkinlikId: Int?, iletisimId:Array<Int>): Single<Unit>


    fun createKonusmaci(konusmaciAdi:List<String>, konusmaciSoyadi:List<String>): Single<Array<Int>>
    fun createEtkinlikKonusmacilari(etkinlikId:Int?,konusmaciId:Array<Int>): Single<Unit>

}


