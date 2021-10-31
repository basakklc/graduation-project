<?php

namespace App\Models;

use App\Http\Filters\Filterable;
use http\Env\Request;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Etkinlik extends Model
{
    use Filterable;
    protected $table='etkinlikler';
    protected $guarded=[];


    public function kullanicilari()
    {
        return $this->belongsToMany('App\Models\Kullanici', 'kullanici_etkinlikleri');
    }
    public function topluluklari()
    {
        return $this->belongsToMany('App\Models\Topluluk', 'etkinlik_kapsamlari');
    }
    public function kapsamlari()
    {
        return $this->belongsToMany('App\Models\Kapsam', 'etkinlik_kapsamlari');
    }

    public function adresi(){
        return $this->hasOne('App\Models\Adres','etkinlik_id','id');
    }

    public function konusmacilari()
    {
        return $this->belongsToMany('App\Models\Konusmaci', 'etkinlik_konusmacilari');
    }

    public function iletisimAdresleri()
    {
        $etkinlik = Etkinlik::select('etkinlikler.id','iletisim_id','iletisim_adresi')
            ->join('etkinlik_iletisimleri','etkinlik_iletisimleri.etkinlik_id',"=","etkinlikler.id")
            ->join('etkinlik_iletisim_kayitlari',"etkinlik_iletisim_kayitlari.id","=",'etkinlik_iletisimleri.iletisim_id');

        return  $etkinlik->get();//

        //return $this->belongsToMany('App\Models\EtkinlikIletisimKayit', 'etkinlik_iletisimleri','etkinlik_id','id');
    }


    public static function etkinlikListe()
    {
        $etkinlik = Etkinlik::select('etkinlik_adi','aciklama','etkinlik_tarihi','ucret','ilce_adi','semt_adi','acik_adres')
            ->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
            ->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
            ->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
            ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')
            ->get();
        return $etkinlik;
    }
    public static function iletisimAdresi($id){
        $adresleri = Etkinlik::join("etkinlik_iletisimleri","etkinlik_iletisimleri.etkinlik_id","=","etkinlikler.id")
            ->join("etkinlik_iletisim_kayitlari","etkinlik_iletisim_kayitlari.id","=", "etkinlik_iletisimleri.iletisim_id")
            ->where("etkinlik_id" ,"=" ,$id)->orderBy("etkinlik_iletisim_kayitlari.id","asc");
        return $adresleri ->pluck("iletisim_adresi") ;
    }
    public static function konusmaciAdi($id){
        $adlari = Etkinlik::join("etkinlik_konusmacilari","etkinlik_konusmacilari.etkinlik_id","=","etkinlikler.id")
            ->join("konusmacilar","konusmacilar.id","=", "etkinlik_konusmacilari.konusmaci_id")
            ->where("etkinlik_id" ,"=" ,$id)->orderBy("konusmacilar.id","asc");
        return $adlari ->pluck("adi") ;
    }
    public static function konusmaciSoyadi($id){
        $soyAdlari = Etkinlik::join("etkinlik_konusmacilari","etkinlik_konusmacilari.etkinlik_id","=","etkinlikler.id")
            ->join("konusmacilar","konusmacilar.id","=", "etkinlik_konusmacilari.konusmaci_id")
            ->where("etkinlik_id" ,"=" ,$id)->orderBy("konusmacilar.id","asc");
        return $soyAdlari ->pluck("soyadi") ;
    }




    public static function kapsamFiltrele($kapsamlar){
        $etkinlik = Etkinlik::select('etkinlik_adi','etkinlik_tarihi','ucret','ilce_adi','semt_adi','acik_adres','kapsam_adi')
            ->join("etkinlik_kapsamlari",'etkinlik_kapsamlari.etkinlik_id',"=","etkinlikler.id")
            ->join('kapsamlar','kapsamlar.id',"=","etkinlik_kapsamlari.kapsam_id")
            ->join('topluluklar','topluluklar.id',"=","etkinlik_kapsamlari.topluluk_id")
            ->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
            ->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
            ->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
            ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')
            ->whereIn('kapsamlar.kapsam_adi',$kapsamlar)
            ->distinct("kapsamlar.id");



        return $etkinlik;
    }
    public static function toplulukFiltrele($topluluklar){
        $etkinlik = Etkinlik::select('etkinlik_adi','etkinlik_tarihi','ucret','ilce_adi','semt_adi','acik_adres','topluluk_adi')
            ->join("etkinlik_kapsamlari",'etkinlik_kapsamlari.etkinlik_id',"=","etkinlikler.id")
            ->join('kapsamlar','kapsamlar.id',"=","etkinlik_kapsamlari.kapsam_id")
            ->join('topluluklar','topluluklar.id',"=","etkinlik_kapsamlari.topluluk_id")
            ->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
            ->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
            ->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
            ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')
            ->whereIn('topluluklar.topluluk_adi',$topluluklar);



        return $etkinlik;
    }


    /*$semtler = Ilce::join('ilceler_semtler','ilceler_semtler.ilce_id','=','ilceler.id')
    ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')
    ->where("ilce_id","=",$id);
        // return gettype($semtler);
        //foreach ($semtler as $semt)
        //return $semt[0];
    return $semtler->pluck('semt_adi');//->pluck('semt_adi');*/

}
