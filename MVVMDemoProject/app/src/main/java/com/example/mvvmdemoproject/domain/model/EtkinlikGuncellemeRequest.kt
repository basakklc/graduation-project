package com.example.mvvmdemoproject.domain.model

import com.google.gson.annotations.SerializedName

data class EtkinlikGuncellemeRequest (
    @SerializedName("kapsam_id")
    val kapsamId: Number,
    @SerializedName("topluluk_id")
    val toplulukId: List<Int>

)