package com.example.mvvmdemoproject.data.repository

import com.example.mvvmdemoproject.data.network.ApiClient
import com.example.mvvmdemoproject.data.network.EtkinlikService
import com.example.mvvmdemoproject.domain.model.KayitOlRequest
import com.example.mvvmdemoproject.domain.model.KayitOlResponse
import com.example.mvvmdemoproject.domain.model.SifreYenilemeResponse
import com.example.mvvmdemoproject.domain.repository.KullaniciIslemleriRepository
import io.reactivex.rxjava3.core.Single

class KullaniciIslemleriRepositoryImpl: KullaniciIslemleriRepository {

    var etkinlikAPI = ApiClient.getClient().create(EtkinlikService::class.java)

    override fun uyeGirisi(email: String, sifre: String): Single<Int> = etkinlikAPI.uyeGirisi(email, sifre)
    override fun sifreYenile(email: String, sifre: String, sifreTekrar: String): Single<SifreYenilemeResponse> =
        etkinlikAPI.sifreYenile(email, sifre, sifreTekrar)
    override fun kayitOl(kayitOlRequest: KayitOlRequest): Single<KayitOlResponse> = etkinlikAPI.kayıtOl(kayitOlRequest)
}