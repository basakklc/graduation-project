package com.example.mvvmdemoproject.data.repository

import com.example.mvvmdemoproject.data.network.ApiClient
import com.example.mvvmdemoproject.data.network.EtkinlikService
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaResponse
import com.example.mvvmdemoproject.domain.repository.EtkinlikOlusturmaRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class EtkinlikOlusturmaRepositoryImpl:EtkinlikOlusturmaRepository {

    var etkinlikAPI = ApiClient.getClient().create(EtkinlikService::class.java)

    override fun postEtkinlik(etkinlik: EtkinlikOlusturmaRequest): Single<EtkinlikOlusturmaResponse> =
        etkinlikAPI.createEtkinlik(etkinlik)

    override fun postKulaniciEtkinlik(etkinlikId: Int?, kullaniciId: Int): Single<Unit> = etkinlikAPI.createKullaniciEtkinlik(etkinlikId, kullaniciId)

    override fun postEtkinlikKapsamlari(etkinlikId: Int?, toplulukId: List<Int>, kapsamId: Int): Single<Unit> =
        etkinlikAPI.createEtkinlikKapsamlari(etkinlikId,toplulukId,kapsamId)

    override fun postEtkinlikAdresi(etkinlikId: Int?, ilceSemtId: Int, acikAdres: String):  Single<Unit> =
        etkinlikAPI.createEtkinlikAdresi(etkinlikId, ilceSemtId, acikAdres)

    override fun getIlceSemt(ilceId: Number, semtId: Number): Single<Int> =
        etkinlikAPI.getIlceSemt(ilceId, semtId)

    override fun postKonusmaci(konusmaciAdi: List<String>, konusmaciSoyadi: List<String>): Single<Array<Int>> =
        etkinlikAPI.createKonusmaci(konusmaciAdi, konusmaciSoyadi)

    override fun postEtkinlikKonusmacisi(etkinlikId: Int?, konusmaciId: Array<Int>): Single<Unit> =
        etkinlikAPI.createEtkinlikKonusmacilari(etkinlikId, konusmaciId)

    override fun postIletisimAdresleri(iletisimAdresleri: List<String>): Single<Array<Int>> =
        etkinlikAPI.createIletisimAdresi(iletisimAdresleri)

    override fun postEtkinlikIletisimAdresleri(etkinlikId: Int?, iletisimId: Array<Int>): Single<Unit> =
        etkinlikAPI.createEtkinlikIletisimAdresleri(etkinlikId, iletisimId)

    override fun ilceninSemti(ilceId: Number): Single<List<String>> = etkinlikAPI.ilceninSemti(ilceId)
    override fun getSemt(semtAdi: String): Single<Int> = etkinlikAPI.getSemt(semtAdi)
    override fun getIlce(ilceAdi: String): Single<Int> = etkinlikAPI.getIlce(ilceAdi)

    override fun getKapsamId(kapsamAdi: String): Single<Int> =etkinlikAPI.getKapsamId(kapsamAdi)
    override fun getToplulukId(toplulukAdi: List<String>): Single<List<Int>> = etkinlikAPI.getToplulukId(toplulukAdi)



}