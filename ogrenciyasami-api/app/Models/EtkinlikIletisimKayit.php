<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class EtkinlikIletisimKayit extends Model
{
    protected $table='etkinlik_iletisim_kayitlari';
    protected $guarded=[];

    public const CREATED_AT = null;
    public const UPDATED_AT = null;
    public static function deleteIletisim($request){

        $id = EtkinlikIletisimKayit::where("iletisim_adresi","=",$request->iletisimAdresi)->pluck("id")->first();
        EtkinlikIletisimKayit::find($id)->delete();
        //return $id->first();




    }


}

