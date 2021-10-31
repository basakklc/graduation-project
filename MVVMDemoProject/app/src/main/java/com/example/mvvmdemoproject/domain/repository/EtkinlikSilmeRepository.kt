package com.example.mvvmdemoproject.domain.repository

import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import io.reactivex.rxjava3.core.Single

interface EtkinlikSilmeRepository {
    fun deleteEtkinlik(id:Int): Single<Unit>

}