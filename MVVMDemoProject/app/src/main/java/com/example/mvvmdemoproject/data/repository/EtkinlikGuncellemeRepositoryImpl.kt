package com.example.mvvmdemoproject.data.repository

import com.example.mvvmdemoproject.data.network.ApiClient
import com.example.mvvmdemoproject.data.network.EtkinlikService
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikGuncellemeRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaResponse
import com.example.mvvmdemoproject.domain.repository.EtkinlikGuncellemeRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikGuncellemeRepositoryImpl : EtkinlikGuncellemeRepository{

    var etkinlikAPI = ApiClient.getClient().create(EtkinlikService::class.java)

    override fun bindingEtkinlik(id: Int): Single<EtkinlikGoruntulemeResponse>  = etkinlikAPI.showEtkinlik(id)
    override fun updateEtkinlik(id: Int, etkinlik: EtkinlikOlusturmaRequest): Single<EtkinlikOlusturmaResponse> =
        etkinlikAPI.updateEtkinlik(id,etkinlik)


    override fun getKapsamId(kapsamAdi: String): Single<Int> =etkinlikAPI.getKapsamId(kapsamAdi)
    override fun getToplulukId(toplulukAdi: List<String>): Single<List<Int>> = etkinlikAPI.getToplulukId(toplulukAdi)
    override fun updateEtkinlikKapsami(etkinlikId:Int,etkinlikGuncellemeRequest: EtkinlikGuncellemeRequest): Single<Unit> =
        etkinlikAPI.updateEtkinlikKapsami(etkinlikId,etkinlikGuncellemeRequest)

    override fun getIletisimId(etkinlikId: Int): Single<List<Int>> = etkinlikAPI.getIletisimId(etkinlikId)
    override fun getIletisimAdresi(etkinlikId: Int): Single<List<String>> =etkinlikAPI.getIletisimAdresi(etkinlikId)
    override fun modifyIletisimAdresi(iletisimAdresi: List<String>, iletisimId: List<Int>): Single<Unit> =
        etkinlikAPI.modifyIletisimAdresi(iletisimAdresi,iletisimId)

    override fun deleteIletisimAdresi(adres: String): Single<Unit> = etkinlikAPI.deleteIletisimAdresi(adres)

    override fun getKonusmaciId(etkinlikId: Int): Single<List<Int>> = etkinlikAPI.getKonusmaciId(etkinlikId)
    override fun getKonusmaciAdi(etkinlikId: Int): Single<List<String>> = etkinlikAPI.getKonusmaciAdi(etkinlikId)
    override fun getKonusmaciSoyadi(etkinlikId: Int): Single<List<String>> = etkinlikAPI.getKonusmaciSoyadi(etkinlikId)
    override fun modifyKonusmaciAdSoyad(guncelKonusmaciAdi: List<String>, guncelKonusmaciSoyadi: List<String>, konusmaciId: List<Int>): Single<Unit> =
        etkinlikAPI.modifyKonusmaciAdSoyad(guncelKonusmaciAdi, guncelKonusmaciSoyadi, konusmaciId)
    override fun deleteKonusmaciAdSoyad(adi: String, soyadi: String): Single<Unit> = etkinlikAPI.deleteKonusmaciAdiSoyadi(adi, soyadi)

    override fun getIlceSemtId(ilceId: Int, semtId: Int): Single<Int> = etkinlikAPI.getIlceSemtId(ilceId, semtId)
    override fun getAdresId(etkinlikId: Int): Single<Int> = etkinlikAPI.getAdresId(etkinlikId)
    override fun getSemt(semtAdi: String): Single<Int> = etkinlikAPI.getSemt(semtAdi)
    override fun getIlce(ilceAdi: String): Single<Int> = etkinlikAPI.getIlce(ilceAdi)

    override fun modifyAdres(ilceSemtId: Int, acikAdres: String, adresId: Int): Single<Unit> = etkinlikAPI.modifyAdres(ilceSemtId, acikAdres, adresId)
    override fun ilceninSemti(ilceId: Number): Single<List<String>> = etkinlikAPI.ilceninSemti(ilceId)

    override fun createIletisimAdresi(iletisimAdresi: List<String>) :Single<Array<Int>> = etkinlikAPI.createIletisimAdresi(iletisimAdresi)
    override fun createEtkinlikIletisimAdresleri(etkinlikId: Int?, iletisimId:Array<Int>): Single<Unit> =etkinlikAPI.createEtkinlikIletisimAdresleri(etkinlikId,iletisimId)

    override fun createKonusmaci(konusmaciAdi:List<String>, konusmaciSoyadi:List<String>): Single<Array<Int>> = etkinlikAPI.createKonusmaci(konusmaciAdi,konusmaciSoyadi)
    override fun createEtkinlikKonusmacilari(etkinlikId:Int?,konusmaciId:Array<Int>): Single<Unit> = etkinlikAPI.createEtkinlikKonusmacilari(etkinlikId,konusmaciId)
}