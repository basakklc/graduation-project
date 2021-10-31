package com.example.mvvmdemoproject.domain.model

import com.google.gson.annotations.SerializedName

data class EtkinlikGoruntulemeResponse (
    @SerializedName("etkinlik")
    val etkinlik: EtkinlikOlusturmaRequest,
    @SerializedName("etkinlik_kapsami")
    var etkinlikKapsami: String,
    @SerializedName("etkinlik_toplulugu")
    var etkinlikToplulugu: List<String>,
    @SerializedName("acik_adres")
    var acikAdres: String,
    @SerializedName("ilce")
    var ilce: String,
    @SerializedName("semt")
    var semt: String,
    @SerializedName("iletisim_adresi")
    val iletisimAdresi: List<String>,
    @SerializedName("konusmaci_adi")
    var konusmaciAdi: List<String>,
    @SerializedName("adi")
    val ad: List<String>,
    @SerializedName("soyadi")
    val soyad: List<String>

)