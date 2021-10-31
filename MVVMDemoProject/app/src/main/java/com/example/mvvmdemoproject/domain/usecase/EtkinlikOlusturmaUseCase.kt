package com.example.mvvmdemoproject.domain.usecase

import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaResponse
import com.example.mvvmdemoproject.domain.repository.EtkinlikOlusturmaRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class EtkinlikOlusturmaUseCase(private val etkinlikOlusturmaRepository: EtkinlikOlusturmaRepository)  {

    fun postEtkinlik(etkinlik:EtkinlikOlusturmaRequest):Single<EtkinlikOlusturmaResponse> =
        etkinlikOlusturmaRepository.postEtkinlik(etkinlik)

    fun postKullaniciEtkinlik(etkinlikId:Int?,kullaniciId:Int):Single<Unit> =
        etkinlikOlusturmaRepository.postKulaniciEtkinlik(etkinlikId, kullaniciId)

    fun postEtkinlikKapsamlari(etkinlikId: Int?, toplulukId:List<Int>, kapsamId: Int): Single<Unit> =
        etkinlikOlusturmaRepository.postEtkinlikKapsamlari(etkinlikId,toplulukId,kapsamId)

    fun postEtkinlikAdresi(etkinlikId: Int?, ilceSemtId: Int, acikAdres: String):  Single<Unit> =
        etkinlikOlusturmaRepository.postEtkinlikAdresi(etkinlikId, ilceSemtId, acikAdres)

    fun getIlceSemt(ilceId: Number, semtId: Number): Single<Int> =
        etkinlikOlusturmaRepository.getIlceSemt(ilceId, semtId)

    fun postKonusmaci(konusmaciAdi: List<String>, konusmaciSoyadi: List<String>): Single<Array<Int>> =
        etkinlikOlusturmaRepository.postKonusmaci(konusmaciAdi, konusmaciSoyadi)

    fun postEtkinlikKonusmacisi(etkinlikId: Int?, konusmaciId: Array<Int>): Single<Unit> =
        etkinlikOlusturmaRepository.postEtkinlikKonusmacisi(etkinlikId, konusmaciId)

    fun postIletisimAdresleri(iletisimAdresleri: List<String>): Single<Array<Int>> =
        etkinlikOlusturmaRepository.postIletisimAdresleri(iletisimAdresleri)

    fun postEtkinlikIletisimAdresleri(etkinlikId: Int?, iletisimId: Array<Int>): Single<Unit> =
        etkinlikOlusturmaRepository.postEtkinlikIletisimAdresleri(etkinlikId, iletisimId)

    fun ilceninSemti(ilceId: Number): Single<List<String>> =etkinlikOlusturmaRepository.ilceninSemti(ilceId)
    fun getSemt(semtAdi : String): Single<Int> = etkinlikOlusturmaRepository.getSemt(semtAdi)
    fun getIlce(ilceAdi: String): Single<Int> = etkinlikOlusturmaRepository.getIlce(ilceAdi)

    fun getKapsamId(kapsamAdi:String): Single<Int> = etkinlikOlusturmaRepository.getKapsamId(kapsamAdi)
    fun getToplulukId(toplulukAdi:List<String>): Single<List<Int>> =etkinlikOlusturmaRepository.getToplulukId(toplulukAdi)
}