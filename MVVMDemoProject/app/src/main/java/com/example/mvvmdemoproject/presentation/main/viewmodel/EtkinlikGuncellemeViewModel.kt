package com.example.mvvmdemoproject.presentation.main.viewmodel

import android.provider.SyncStateContract.Helpers.update
import android.support.v4.app.INotificationSideChannel
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikGuncellemeRequest
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.schedulers.Schedulers

class EtkinlikGuncellemeViewModel (private val etkinlikGuncellemeUseCase: EtkinlikGuncellemeUseCase,
                                   private var id:Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val etkinlik = MutableLiveData<EtkinlikGoruntulemeResponse>()
    private val semt = MutableLiveData<List<String>>()
    private val konusmaciAdi = MutableLiveData<List<String>>()
    private val konusmaciSoyadi = MutableLiveData<List<String>>()
    private val iletisimAdresi = MutableLiveData<List<String>>()
    private val repoLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    var i_id =0
    var s_id =0
    var kapsam: Number = 0
    var topluluk: List<Int> = listOf()
    var iletisim: List<Int> = listOf()

    lateinit var request: EtkinlikGuncellemeRequest
    lateinit var guncelForm: EtkinlikGoruntulemeResponse


    init {
        binding(id)
    }

    private fun binding(id: Int) {
        compositeDisposable.add(
            etkinlikGuncellemeUseCase.bindingEtkinlik(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it -> etkinlik.postValue(it) },
                    { throwable -> Log.e("hata : ", throwable.message.toString()) })
        )
    }

    fun takeBindingData( form : EtkinlikGoruntulemeResponse){
        guncelForm = form
        //Log.e("model","girdi"+form.etkinlik.etkinlik_adi)
       /* var etkinlik = EtkinlikOlusturmaRequest("sdn", "sdn", "2021-04-21", 50)
        guncelForm = EtkinlikGoruntulemeResponse(etkinlik, "Tarih", listOf("Bisiklet","Caz","ÇASAD"), "sdn", "Merkez", "Barbaros",
            listOf("sdn"), listOf("sdn"), listOf("sdn"), listOf("sdn")
        )*/

    }

    fun  mainUpdate(eID:Int) {
        val x  = Single.merge(
            mainEtkinlikUpdate(eID).subscribeOn(Schedulers.io()),
            mainAdresUpdate().subscribeOn(Schedulers.io()),
            mainEtkinlikKapsamiUpdate().subscribeOn(Schedulers.io())
        )

            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()



    }

        fun mainEtkinlikUpdate(eID:Int):Single<Boolean> {
            compositeDisposable.add(
                etkinlikGuncellemeUseCase.updateEtkinlik(eID,guncelForm.etkinlik)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ Log.e("model",guncelForm.etkinlik.toString()) },{throwable -> })
            )
            return Single.just(true)
        }

        fun mainIletisimAdresiUpdate(iletisimListesi:List<String>) : Single<Boolean> {
            compositeDisposable.add(
                etkinlikGuncellemeUseCase.getIletisimId(id)
                    //.flatMap { etkinlikGuncellemeUseCase.modifyIletisimAdresi(guncelForm.iletisimAdresi,it) }
                    .flatMap { etkinlikGuncellemeUseCase.modifyIletisimAdresi(iletisimListesi,it) }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe({ Log.e("UPDATE","i")},{throwable -> })//Log.e("getID",kapsamId.toString())})

            )
            return Single.just(true)

        }
             fun iletisimAdresleri(etkinlik_id:Int){
                compositeDisposable.add(
                    etkinlikGuncellemeUseCase.getIletisimAdresi(etkinlik_id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread()) // konusmaciSoyadi.postValue(it)

                        .subscribe({ iletisimAdresi.postValue(it)},{ throwable -> })//Log.e("getID",kapsamId.toString())})

                )
            }
            fun createIletisimAdresi(adres:String){
        compositeDisposable.add(
            etkinlikGuncellemeUseCase.createIletisimAdresi(listOf(adres))                //.flatMap { etkinlikGuncellemeUseCase.modifyIletisimAdresi(guncelForm.iletisimAdresi,it) }
                .flatMap { etkinlikGuncellemeUseCase.createEtkinlikIletisimAdresleri(id,it)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe({ Log.e("UPDATE","i")},{throwable -> })//Log.e("getID",kapsamId.toString())})

        )

    }
            fun deleteIletisim(adres:String){

                  compositeDisposable.add(
            etkinlikGuncellemeUseCase.deleteIletisimAdresi(adres)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ },{throwable -> })//Log.e("getID",kapsamId.toString())})

        )


            }

        fun mainKonusmaciUpdate(adListesi:List<String>,soyadListesi:List<String>):Single<Boolean> {
            compositeDisposable.add(
                etkinlikGuncellemeUseCase.getKonusmaciId(id)
                    //.flatMap { etkinlikGuncellemeUseCase.modifyIletisimAdresi(guncelForm.iletisimAdresi,it) }
                    .flatMap { etkinlikGuncellemeUseCase.modifyKonusmaciAdSoyad(adListesi,
                       soyadListesi,it)}
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe({ Log.e("UPDATE","i")},{throwable -> })//Log.e("getID",kapsamId.toString())})

            )
            return Single.just(true)

        }

            fun createKonusmaci(ad:String,soyad:String){
        compositeDisposable.add(
            etkinlikGuncellemeUseCase.createKonusmaci(listOf(ad), listOf(soyad))            //.flatMap { etkinlikGuncellemeUseCase.modifyIletisimAdresi(guncelForm.iletisimAdresi,it) }
                .flatMap { etkinlikGuncellemeUseCase.createEtkinlikKonusmacilari(id,it)}
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe({ Log.e("UPDATE","i")},{throwable -> })//Log.e("getID",kapsamId.toString())})

        )

    }
            fun deleteKonusmaci(ad:String,soyad: String){
        compositeDisposable.add(
            etkinlikGuncellemeUseCase.deleteKonusmaciAdSoyad(ad,soyad)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ },{throwable -> })//Log.e("getID",kapsamId.toString())})

        )
    }
            private fun konusmaciAdlari(etkinlik_id:Int){
            compositeDisposable.add(
                etkinlikGuncellemeUseCase.getKonusmaciAdi(etkinlik_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()) //konusmaciAdi.postValue(it)
                    .subscribe({ konusmaciAdi.postValue(it)},{ throwable -> })//Log.e("getID",kapsamId.toString())})

            )
        }
            fun konusmaciSoyadlari(etkinlik_id:Int){
            compositeDisposable.add(
                etkinlikGuncellemeUseCase.getKonusmaciSoyadi(etkinlik_id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()) // konusmaciSoyadi.postValue(it)

                    .subscribe({ konusmaciSoyadi.postValue(it)},{ throwable -> })//Log.e("getID",kapsamId.toString())})

            )
        }


        fun mainAdresUpdate():Single<Boolean> {

        adres()
            val ilce =i_id
            val semt=s_id
        compositeDisposable.add(
            etkinlikGuncellemeUseCase.getIlceSemtId(ilce,semt)
                //.flatMap { etkinlikGuncellemeUseCase.modifyIletisimAdresi(guncelForm.iletisimAdresi,it) }
                .flatMap { x ->  etkinlikGuncellemeUseCase.getAdresId(id)
                    .flatMap { etkinlikGuncellemeUseCase.modifyAdres(x,guncelForm.acikAdres,it) } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe({ Log.e("UPDATE",semt.toString())},{throwable -> })//Log.e("getID",kapsamId.toString())})

        )
        return Single.just(true)
    }
            fun adres(){
                val x  = Single.merge(
                    etkinlikGuncellemeUseCase.getIlce(guncelForm.ilce).flatMap { x-> setIlceID(x)  }.subscribeOn(Schedulers.io()),
                    etkinlikGuncellemeUseCase.getSemt(guncelForm.semt).flatMap { y-> setSemtID(y) }.subscribeOn(Schedulers.io())
                )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()

            }
            fun setIlceID(id:Int):Single<Boolean>{
            i_id=id
            return Single.just(true)
        }
            fun setSemtID(id:Int):Single<Boolean>{
                s_id=id
                return Single.just(true)
            }

             fun ilceninSemti(ilce:String){  //paremtre geçmesin i_id aktarılsın. nul görme ihtimali yüksek
                compositeDisposable.add(
                    etkinlikGuncellemeUseCase.getIlce(ilce)
                        .flatMap { etkinlikGuncellemeUseCase.ilceninSemti(it) }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ x-> semt.postValue(x)  },{ throwable -> })//Log.e("getID",kapsamId.toString())})

                )
        }
           /* fun ilceninSemti(ilce_id:Number){  //paremtre geçmesin i_id aktarılsın. nul görme ihtimali yüksek
        compositeDisposable.add(
            etkinlikGuncellemeUseCase.ilceninSemti(ilce_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ semt.postValue(it)  },{ throwable -> })//Log.e("getID",kapsamId.toString())})

        )
    }*/




        fun mainEtkinlikKapsamiUpdate():Single<Boolean> {
            getKapsamID(guncelForm.etkinlikKapsami)
            getToplulukID(guncelForm.etkinlikToplulugu)
            guncellemeDataCreate(kapsam,topluluk)
            updateEtkinlikKapsami(request,id)
            return Single.just(true)
        }
            //kapsam ıd lerin alınıp requestin oluşturulması için atanması.
            private fun getKapsamID(etkinlikKapsami: String)  {
                var x = etkinlikGuncellemeUseCase.getKapsamId(etkinlikKapsami)
                    .flatMap { return@flatMap setKapsamId(it) }
                    .subscribe()//Log.e("getID",kapsamId.toString())})
            }
            private fun setKapsamId(id: Int) : Single<Boolean>{
                kapsam = id
                return Single.just(true)
            }
            private fun getToplulukID(etkinlikToplulugu: List<String>) {
                var x = etkinlikGuncellemeUseCase.getToplulukId(etkinlikToplulugu)
                    .flatMap { return@flatMap setToplulukId(it) }
                    .subscribe()//Log.e("getID",kapsamId.toString())})

            }
            private fun setToplulukId(id: List<Int>) :Single<Boolean> {
                topluluk = id
                return Single.just(true)
            }
            private fun guncellemeDataCreate(kapsam: Number, topluluk: List<Int>){
                request = EtkinlikGuncellemeRequest(kapsam,topluluk)
            }
            private fun updateEtkinlikKapsami(request: EtkinlikGuncellemeRequest, id: Int) {
                compositeDisposable.add(
                    etkinlikGuncellemeUseCase.updateEtkinlikKapsami(id,request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe({ Log.e("UPDATE",request.toString())},{throwable -> })//Log.e("getID",kapsamId.toString())})

                )
            }







// Live Data ların dönüşü
    fun getEtkinlik(): MutableLiveData<EtkinlikGoruntulemeResponse> {
        return etkinlik
    }
    fun getSemt(str:String?): MutableLiveData<List<String>>{
        Log.e("DENE",str!!)
        ilceninSemti(str)//3
        return semt
    }
    fun getAd(eid:Int):MutableLiveData<List<String>>{
        konusmaciAdlari(eid)
        return konusmaciAdi
    }
    fun getSoyad(eid:Int):MutableLiveData<List<String>>{
        konusmaciSoyadlari(eid)
        return konusmaciSoyadi
    }
    fun getIletisim(eid:Int):MutableLiveData<List<String>>{
        iletisimAdresleri(eid)
        return iletisimAdresi
    }
    fun getError(): MutableLiveData<Boolean> {
        return repoLoadError
    }
    fun getLoading(): MutableLiveData<Boolean> {
        return loading
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()

    }

}