<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Kullanici extends Model
{
    protected $table='kullanicilar';
    protected $guarded=[];

    public function etkinlikleri()
    {
        return $this->belongsToMany('App\Models\Etkinlik', 'kullanici_etkinlikleri');
    }


    public function etkinlikler(){
        $kullanici= Kullanici::select('etkinlik_adi','aciklama','etkinlik_tarihi','ucret','ilce_adi','semt_adi','acik_adres')
                ->join("kullanici_etkinlikleri","kullanici_etkinlikleri.kullanici_id","kullanicilar.id")
                ->join("etkinlikler","etkinlikler.id","kullanici_etkinlikleri.etkinlik_id")
                ->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
                ->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
                ->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
                ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')
                ->get();

        return $kullanici;
    }
}
