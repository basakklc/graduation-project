package com.example.mvvmdemoproject.domain.usecase

import com.example.mvvmdemoproject.domain.repository.EtkinlikSilmeRepository
import io.reactivex.rxjava3.core.Single

class EtkinlikSilmeUseCase (private val etkinlikSilmeRepository: EtkinlikSilmeRepository){

    fun deleteEtkinlik(id:Int): Single<Unit> =
        etkinlikSilmeRepository.deleteEtkinlik(id)
}