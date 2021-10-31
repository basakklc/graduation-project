<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class EtkinlikKapsam extends Model
{
    protected $table='etkinlik_kapsamlari';
    protected $guarded=[];
    public const UPDATED_AT =null ;
    public const CREATED_AT = null;



    public static function deleteRow($etkinlik,$topluluklar)
    {
        $i = 0;
        $etkinlik_sayisi = $etkinlik->count(); //4 1. grup
        $topluluk_sayisi = count($topluluklar);
        $loop = $etkinlik_sayisi - $topluluk_sayisi; // 2

        while ($i < $loop) {
            $etkinlik[$i]->delete();
            $i += 1;
        }

    }
    public static function updateRow($etkinlik,$topluluklar,$data,$listIndisControl){
        $a = 0;
        //$topluluk_sayisi = count($topluluklar);

        while ($a < $listIndisControl) {
            $etkinlik[$a]->update([
                "topluluk_id" => $topluluklar[$a],
                "kapsam_id" => $data->kapsam_id
            ]);
            $a += 1;

        }
        return $a;
    }
    public static function insertRow($indis,$etkinlik,$topluluklar,$etkinlik_id,$data){
        $topluluk_sayisi = count($topluluklar);
        while ($indis < $topluluk_sayisi) {
            $etkinlik->insert([
                "etkinlik_id" => $etkinlik_id,
                "topluluk_id" => $topluluklar[$indis],
                "kapsam_id" => $data->kapsam_id
            ]);
            $indis += 1;
        }

    }


    public static function guncelleme($etkinlik_id,$data)
    {

        $etkinlik = EtkinlikKapsam::where("etkinlik_id", $etkinlik_id)->get(); // 174 olanlar
        $topluluklar = $data->topluluk_id; // toplululk listesi = 2.grup

        $etkinlik_sayisi = $etkinlik->count(); //4 1. grup
        $topluluk_sayisi = count($topluluklar); //2 2. grup



        if ($etkinlik_sayisi > $topluluk_sayisi) {
            EtkinlikKapsam::deleteRow($etkinlik,$topluluklar);
            $etkinlik = EtkinlikKapsam::where("etkinlik_id", "=", $etkinlik_id)->get();
            $indis = EtkinlikKapsam::updateRow($etkinlik,$topluluklar,$data,$topluluk_sayisi);

        }
        elseif ($etkinlik_sayisi < $topluluk_sayisi) {
            $indis = EtkinlikKapsam::updateRow($etkinlik,$topluluklar,$data,$etkinlik_sayisi);
            $etkinlik = EtkinlikKapsam::where("etkinlik_id", "=", $etkinlik_id);//->get();
            EtkinlikKapsam::insertRow($indis,$etkinlik,$topluluklar,$etkinlik_id,$data);

        }
        else{
            $etkinlik = EtkinlikKapsam::where("etkinlik_id", "=", $etkinlik_id)->get();
            $indis = EtkinlikKapsam::updateRow($etkinlik,$topluluklar,$data,$etkinlik_sayisi);
        }



    }



}
