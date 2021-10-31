<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;


/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('konusmacilar/delete','App\Http\Controllers\Api\KonusmaciController@delete');
Route::get('iletisimler/delete','App\Http\Controllers\Api\EtkinlikIletisimKayitController@delete');
Route::get('etkinlikler/etkinlikler','App\Http\Controllers\Api\EtkinlikController@etkinlikler');

Route::get('etkinlikler/model','App\Http\Controllers\Api\EtkinlikController@idModel');
Route::get('etkinlikler/detay','App\Http\Controllers\Api\EtkinlikController@detay');

Route::get('topluluklar/filtreleme','App\Http\Controllers\Api\EtkinlikController@toplulukFiltrele');
Route::get('kapsamlar/filtreleme','App\Http\Controllers\Api\EtkinlikController@kapsamFiltrele');

/*Route::get('topluluklar/filtreleme','App\Http\Controllers\Api\ToplulukController@filter');
Route::get('kapsamlar/filtreleme','App\Http\Controllers\Api\KapsamController@filter');*/


Route::get('etkinlikler/siralama','App\Http\Controllers\Api\EtkinlikController@filter');
Route::get('ilceSemt/id','App\Http\Controllers\Api\IlceSemtController@ilceSemtId');
Route::get('ilceler/semtler/{id}','App\Http\Controllers\Api\IlceController@semtler');
Route::get('detay_goster','App\Http\Controllers\Api\KapsamController@etkinlikDetaylari');
Route::get('kapsamlar/id','App\Http\Controllers\Api\KapsamController@getKapsamId');
Route::get('topluluklar/id','App\Http\Controllers\Api\ToplulukController@getId');
Route::get('iletisimKayitlari/id','App\Http\Controllers\Api\EtkinlikIletisimKayitController@getIletisimId');
Route::get('iletisimKayitlari/modifyIletisimAdresi','App\Http\Controllers\Api\EtkinlikIletisimKayitController@modifyIletisimAdresi');
Route::get('konusmacilar/id','App\Http\Controllers\Api\KonusmaciController@getKonusmaciId');
Route::get('konusmacilar/modifyKonusmaciAdSoyad','App\Http\Controllers\Api\KonusmaciController@modifyKonusmaciAdSoyad');
Route::get('adresler/id','App\Http\Controllers\Api\AdresController@getAdresId');
Route::get('adresler/modifyAdres','App\Http\Controllers\Api\AdresController@modifyAdres');
Route::get('ilceler/semti','App\Http\Controllers\Api\IlceController@semti');
Route::get('etkinlikler/konusmaciAdi','App\Http\Controllers\Api\EtkinlikController@konusmaciAdi');
Route::get('etkinlikler/konusmaciSoyadi','App\Http\Controllers\Api\EtkinlikController@konusmaciSoyadi');
Route::get('etkinlikler/adresi','App\Http\Controllers\Api\EtkinlikController@iletisimAdresi');
Route::get('ilceler/ilce','App\Http\Controllers\Api\IlceController@ilce');
Route::get('semtler','App\Http\Controllers\Api\SemtController@semt');
Route::get('kullanicilar/etkinlikID','App\Http\Controllers\Api\KullaniciController@kullaniciEtkinlikleri');
Route::get('kullanicilar/uyeGirisi','App\Http\Controllers\Api\KullaniciController@uyeGirisi');
Route::get('kullanicilar/sifremiUnuttum','App\Http\Controllers\Api\KullaniciController@sifremiUnuttum');


Route::apiResources(['etkinlikler' => 'App\Http\Controllers\Api\EtkinlikController',
    'kullaniciEtkinlikleri' => 'App\Http\Controllers\Api\KullaniciEtkinlikController',
    'kullanicilar'=> 'App\Http\Controllers\Api\KullaniciController',
    'topluluklar'=> 'App\Http\Controllers\Api\ToplulukController',
    'etkinlikKapsamlari'=> 'App\Http\Controllers\Api\EtkinlikKapsamController',
    'adresler'=> 'App\Http\Controllers\Api\AdresController',
    'konusmacilar'=> 'App\Http\Controllers\Api\KonusmaciController',
    'etkinlikKonusmacilari'=> 'App\Http\Controllers\Api\EtkinlikKonusmaciController',
    'iletisimKayitlari'=> 'App\Http\Controllers\Api\EtkinlikIletisimKayitController',
    'iletisimler'=> 'App\Http\Controllers\Api\EtkinlikIletisimController',
    'ilceler'=>'App\Http\Controllers\Api\IlceController',
    'kapsamlar'=> 'App\Http\Controllers\Api\KapsamController',

]);

