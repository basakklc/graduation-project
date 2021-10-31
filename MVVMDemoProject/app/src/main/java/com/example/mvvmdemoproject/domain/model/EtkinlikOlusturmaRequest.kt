package com.example.mvvmdemoproject.domain.model

import android.os.Parcelable

data class EtkinlikOlusturmaRequest (
    var etkinlik_adi: String,
    var aciklama: String,
    var etkinlik_tarihi: String,
    var ucret: Number

)