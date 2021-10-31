package com.example.mvvmdemoproject.data.network

import com.example.mvvmdemoproject.domain.model.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface EtkinlikService {

    //etkinlik listeleme
    @GET("etkinlikler/etkinlikler")
    fun getEtkinlikler(): Single<List<EtkinlikListelemeResponse>>

    @GET("kullanicilar/etkinlikID")
    fun getKullaniciEtkinlikleri(@Query("kullanici_id") kullaniciId: Int) : Single<List<EtkinlikListelemeResponse>>

    @GET("etkinlikler/model")
    fun getId(@Query("etkinlik_adi") etkinlik_adi:String,
              @Query("ucret") ucret:Number,
              @Query("etkinlik_tarihi") etkinlik_tarihi:String,
              @Query("aciklama") aciklama:String): Single<Int>
    @GET("topluluklar")
    fun getTopluluklar(): Single<List<String>>
    @GET("kapsamlar")
    fun getKapsamlar(): Single<List<String>>


    //etkinlik oluşturma
    @POST("etkinlikler")
    fun createEtkinlik(@Body etkinlik: EtkinlikOlusturmaRequest): Single<EtkinlikOlusturmaResponse>

    @FormUrlEncoded
    @POST("kullaniciEtkinlikleri")
    fun createKullaniciEtkinlik(@Field("etkinlik_id") etkinlikId:Int?,
                                @Field("kullanici_id") kullaniciId:Int): Single<Unit>

    @FormUrlEncoded
    @POST("etkinlikKapsamlari")
    fun createEtkinlikKapsamlari(@Field("etkinlik_id") etkinlikId:Int?,
                                 @Field("topluluk_id[]") toplulukId:List<Int>,
                                 @Field("kapsam_id") kapsamId:Int): Single<Unit>

    @FormUrlEncoded
    @POST("adresler")
    fun createEtkinlikAdresi(@Field("etkinlik_id") etkinlikId:Int?,
                    @Field("ilce_semt_id") ilceSemtId:Int,
                    @Field("acik_adres") acikAdres:String): Single<Unit>

    @GET("ilceSemt/id")
    fun getIlceSemt(@Query("ilce_id") ilceId:Number,
                    @Query("semt_id") semtId:Number): Single<Int>

    @GET("ilceler/semti")
    fun ilceninSemti(@Query("ilce_id") ilceId:Number): Single<List<String>>

    @FormUrlEncoded
    @POST("konusmacilar")
    fun createKonusmaci(@Field("adi[]") konusmaciAdi:List<String>,
                        @Field("soyadi[]") konusmaciSoyadi:List<String>): Single<Array<Int>>
    @FormUrlEncoded
    @POST("etkinlikKonusmacilari")
    fun createEtkinlikKonusmacilari(@Field("etkinlik_id") etkinlikId: Int?,
                                    @Field("konusmaci_id[]") konusmaciId:Array<Int>): Single<Unit>

    @FormUrlEncoded
    @POST("iletisimKayitlari")
    fun createIletisimAdresi(@Field ("iletisim_adresi[]")  iletisimAdresi: List<String>) :Single<Array<Int>>

    @FormUrlEncoded
    @POST("iletisimler")
    fun createEtkinlikIletisimAdresleri(@Field("etkinlik_id") etkinlikId: Int?,
                                       @Field("iletisim_adresi[]") iletisimId:Array<Int>): Single<Unit>

    //etkinlik görüntüleme

    @GET("etkinlikler/detay")
    fun showEtkinlik(@Query("id") id:Int): Single<EtkinlikGoruntulemeResponse>

    //etkinlik sil
    @DELETE("etkinlikler/{id}")
    fun deleteEtkinlik(@Path("id") id:Int ) : Single<Unit>


// etkinlik guncelle -
    @PATCH("etkinlikler/{id}")
    fun updateEtkinlik(@Path("id") id:Int, @Body etkinlik: EtkinlikOlusturmaRequest): Single<EtkinlikOlusturmaResponse>

//etkinlik güncelle - KAPSAM
    @PATCH("etkinlikKapsamlari/{id}")
    fun updateEtkinlikKapsami(@Path("id") etkinlikId:Int, @Body etkinlik: EtkinlikGuncellemeRequest): Single<Unit>

    @GET("kapsamlar/id")
    fun getKapsamId(@Query("kapsam_adi") kapsamAdi:String): Single<Int>

    @GET("topluluklar/id")
    fun getToplulukId(@Query("topluluk_adi[]") toplulukAdi:List<String>): Single<List<Int>>

//etkinlik güncelle - ILETISIM
    @GET("iletisimKayitlari/id")
    fun getIletisimId(@Query("etkinlik_id") etkinlikId:Int ): Single<List<Int>>
    @GET("etkinlikler/adresi")
    fun getIletisimAdresi(@Query("etkinlik_id") etkinlikId:Int ): Single<List<String>>

    @GET("iletisimler/delete")
    fun deleteIletisimAdresi(@Query("iletisimAdresi") adi:String): Single<Unit>

    @GET("iletisimKayitlari/modifyIletisimAdresi")
    fun modifyIletisimAdresi(@Query("iletisim_adresi[]") guncelIletisimAdresi:List<String>,
                             @Query("iletisim_id[]") iletisimId: List<Int>): Single<Unit>
//etkinlik güncelle - KONUSMACI
    @GET("konusmacilar/id")
    fun getKonusmaciId(@Query("etkinlik_id") etkinlikId:Int ): Single<List<Int>>
    @GET("etkinlikler/konusmaciAdi")
    fun getKonusmaciSoyadi(@Query("etkinlik_id") etkinlikId:Int ): Single<List<String>>
    @GET("etkinlikler/konusmaciSoyadi")
    fun getKonusmaciAdi(@Query("etkinlik_id") etkinlikId:Int ): Single<List<String>>

    @GET("konusmacilar/delete")
    fun deleteKonusmaciAdiSoyadi(@Query("konusmaciAdi") adi:String,
                                 @Query("konusmaciSoyadi") soyadi:String): Single<Unit>

    @GET("konusmacilar/modifyKonusmaciAdSoyad")
    fun modifyKonusmaciAdSoyad(@Query("konusmaci_ad[]") guncelKonusmaciAdi:List<String>,
                               @Query("konusmaci_soyad[]") guncelKonusmaciSoyadi: List<String>,
                               @Query("konusmaci_id[]") konusmaciId: List<Int>): Single<Unit>

//etkinlik güncelle - ADRES
    @GET("ilceSemt/id")
    fun getIlceSemtId(@Query("ilce_id") ilceId:Int ,
                    @Query("semt_id") semtId:Int): Single<Int>

    @GET("adresler/id")
    fun getAdresId(@Query("etkinlik_id") etkinlikId:Int): Single<Int>

    @GET("ilceler/ilce")
    fun getIlce(@Query("ilce_adi") ilceAdi:String): Single<Int>

    @GET("semtler")
    fun getSemt(@Query("semt_adi") semtAdi:String): Single<Int>

    @GET("adresler/modifyAdres")
    fun modifyAdres(@Query("ilce_semt_id") ilceSemtId:Int,
                    @Query("acik_adres") acikAdres: String,
                    @Query("adres_id") adresId: Int): Single<Unit>





    //filtreleme-sıralama
    @GET("etkinlikler/siralama")
    fun getEtkinlikSiralama(@Query("ucret") ucret:String?,
                      @Query("etkinlikTarihi") tarih:String?) : Single<List<EtkinlikListelemeResponse>>

    @GET("kapsamlar/filtreleme")
    fun getKapsamaGore(@Query("kapsamAdi") kapsamlar:String) : Single<List<EtkinlikListelemeResponse>>

    @GET("topluluklar/filtreleme")
    fun getToplulugaGore(@Query("toplulukAdi") topluluklar:String) : Single<List<EtkinlikListelemeResponse>>


    //uyelik
    @GET("kullanicilar/uyeGirisi")
    fun uyeGirisi(@Query("email") email:String,
                            @Query("sifre") sifre:String) : Single<Int>

    @GET("kullanicilar/sifremiUnuttum")
    fun sifreYenile(@Query("email") email:String,
                    @Query("sifre") sifre:String,
                    @Query("sifre_tekrar") sifreTekrar:String) : Single<SifreYenilemeResponse>

    @POST("kullanicilar")
    fun kayıtOl(@Body kayitOlRequest: KayitOlRequest) : Single<KayitOlResponse>


}