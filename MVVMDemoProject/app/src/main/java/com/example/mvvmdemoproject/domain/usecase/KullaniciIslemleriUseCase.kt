package com.example.mvvmdemoproject.domain.usecase

import com.example.mvvmdemoproject.domain.model.KayitOlRequest
import com.example.mvvmdemoproject.domain.model.KayitOlResponse
import com.example.mvvmdemoproject.domain.model.SifreYenilemeResponse
import com.example.mvvmdemoproject.domain.repository.KullaniciIslemleriRepository
import io.reactivex.rxjava3.core.Single

class KullaniciIslemleriUseCase(private val kullaniciIslemleriRepository: KullaniciIslemleriRepository) {

    fun uyeGirisi(email:String,sifre:String): Single<Int> = kullaniciIslemleriRepository.uyeGirisi(email, sifre)
    fun sifreYenile(email:String,sifre:String,sifreTekrar:String): Single<SifreYenilemeResponse> =
        kullaniciIslemleriRepository.sifreYenile(email, sifre,sifreTekrar)
    fun kayitOl(kayitOlRequest: KayitOlRequest): Single<KayitOlResponse> =
        kullaniciIslemleriRepository.kayitOl(kayitOlRequest)
}