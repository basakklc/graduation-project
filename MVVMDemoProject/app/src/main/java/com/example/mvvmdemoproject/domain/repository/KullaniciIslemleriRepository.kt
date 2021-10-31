package com.example.mvvmdemoproject.domain.repository

import com.example.mvvmdemoproject.domain.model.KayitOlRequest
import com.example.mvvmdemoproject.domain.model.KayitOlResponse
import com.example.mvvmdemoproject.domain.model.SifreYenilemeResponse
import io.reactivex.rxjava3.core.Single

interface KullaniciIslemleriRepository {
    fun uyeGirisi(email:String,sifre:String): Single<Int>
    fun sifreYenile(email:String,sifre:String,sifreTekrar:String): Single<SifreYenilemeResponse>
    fun kayitOl(kayitOlRequest: KayitOlRequest): Single<KayitOlResponse>
}