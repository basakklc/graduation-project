package com.example.mvvmdemoproject.domain.usecase

import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikGuncellemeRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.repository.EtkinlikGuncellemeRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


class EtkinlikGuncellemeUseCase (private val etkinlikGuncellemeRepository: EtkinlikGuncellemeRepository){

// Etkinlik guncelleme form binding
    fun bindingEtkinlik(id:Int): Single<EtkinlikGoruntulemeResponse> = etkinlikGuncellemeRepository.bindingEtkinlik(id)

// Etkinlik guncelleme
    fun updateEtkinlik(id:Int,etkinlik:EtkinlikOlusturmaRequest) = etkinlikGuncellemeRepository.updateEtkinlik(id, etkinlik)

//Update Etkinlik Kapsamı
    fun updateEtkinlikKapsami(etkinlikId:Int, etkinlikGuncellemeRequest: EtkinlikGuncellemeRequest) :Single<Unit> =
        etkinlikGuncellemeRepository.updateEtkinlikKapsami(etkinlikId,etkinlikGuncellemeRequest)
    fun getKapsamId(kapsamAdi:String): Single<Int> = etkinlikGuncellemeRepository.getKapsamId(kapsamAdi)
    fun getToplulukId(toplulukAdi:List<String>): Single<List<Int>> =etkinlikGuncellemeRepository.getToplulukId(toplulukAdi)

//Update Etkinlik  IletisimAdresi
    fun getIletisimId(etkinlikId: Int): Single<List<Int>> = etkinlikGuncellemeRepository.getIletisimId(etkinlikId)
    fun getIletisimAdresi(etkinlikId: Int): Single<List<String>> = etkinlikGuncellemeRepository.getIletisimAdresi(etkinlikId)
    fun modifyIletisimAdresi(iletisimAdresi: List<String>, iletisimId: List<Int>): Single<Unit> =
        etkinlikGuncellemeRepository.modifyIletisimAdresi(iletisimAdresi,iletisimId)
    fun deleteIletisimAdresi(adres: String): Single<Unit> = etkinlikGuncellemeRepository.deleteIletisimAdresi(adres)

//Update Konusmacı
    fun getKonusmaciId(etkinlikId: Int): Single<List<Int>> = etkinlikGuncellemeRepository.getKonusmaciId(etkinlikId)
    fun getKonusmaciAdi(etkinlikId: Int): Single<List<String>> = etkinlikGuncellemeRepository.getKonusmaciAdi(etkinlikId)
    fun getKonusmaciSoyadi(etkinlikId: Int): Single<List<String>> = etkinlikGuncellemeRepository.getKonusmaciSoyadi(etkinlikId)

    fun modifyKonusmaciAdSoyad(guncelKonusmaciAdi: List<String>, guncelKonusmaciSoyadi: List<String>, konusmaciId: List<Int>): Single<Unit> =
        etkinlikGuncellemeRepository.modifyKonusmaciAdSoyad(guncelKonusmaciAdi, guncelKonusmaciSoyadi, konusmaciId)
    fun deleteKonusmaciAdSoyad(adi: String, soyadi: String): Single<Unit> = etkinlikGuncellemeRepository.deleteKonusmaciAdSoyad(adi, soyadi)

//Update Adres
    fun getIlceSemtId(ilceId: Int, semtId: Int): Single<Int> = etkinlikGuncellemeRepository.getIlceSemtId(ilceId, semtId)
    fun getAdresId(etkinlikId: Int): Single<Int> = etkinlikGuncellemeRepository.getAdresId(etkinlikId)
    fun getSemt(semtAdi : String): Single<Int> = etkinlikGuncellemeRepository.getSemt(semtAdi)
    fun getIlce(ilceAdi: String): Single<Int> = etkinlikGuncellemeRepository.getIlce(ilceAdi)
    fun modifyAdres(ilceSemtId: Int, acikAdres: String, adresId: Int): Single<Unit> = etkinlikGuncellemeRepository.modifyAdres(ilceSemtId, acikAdres, adresId)
    fun ilceninSemti(ilceId: Number): Single<List<String>> =etkinlikGuncellemeRepository.ilceninSemti(ilceId)

    //create iletisimadresi
    fun createIletisimAdresi(iletisimAdresi: List<String>) :Single<Array<Int>> = etkinlikGuncellemeRepository.createIletisimAdresi(iletisimAdresi)
    fun createEtkinlikIletisimAdresleri(etkinlikId: Int?, iletisimId:Array<Int>): Single<Unit> =etkinlikGuncellemeRepository.createEtkinlikIletisimAdresleri(etkinlikId,iletisimId)

    //create konusmaci
    fun createKonusmaci(konusmaciAdi:List<String>, konusmaciSoyadi:List<String>): Single<Array<Int>> = etkinlikGuncellemeRepository.createKonusmaci(konusmaciAdi,konusmaciSoyadi)
    fun createEtkinlikKonusmacilari(etkinlikId:Int?,konusmaciId:Array<Int>): Single<Unit> = etkinlikGuncellemeRepository.createEtkinlikKonusmacilari(etkinlikId,konusmaciId)


}
