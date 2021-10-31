package com.example.mvvmdemoproject.domain.model

import com.google.gson.annotations.SerializedName

data class EtkinlikListelemeResponse(
    @SerializedName("etkinlik_adi")
    val etkinlikAdi: String,
    @SerializedName("aciklama")
    val aciklama: String,
    @SerializedName("etkinlik_tarihi")
    val etkinlikTarihi: String,
    @SerializedName("ucret")
    val ucret: Number,
    @SerializedName("acik_adres")
    val acikAdres:String,
    @SerializedName("ilce_adi")
    val ilceAdi:String,
    @SerializedName("semt_adi")
    val semtAdi:String
)