<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Konusmaci extends Model
{
    protected $table='konusmacilar';
    protected $guarded=[];

    public const CREATED_AT = null;
    public const UPDATED_AT = null;

    public static function deleteKonusmaci($request){
        $id = Konusmaci::where("adi","=",$request->konusmaciAdi)
            ->where("soyadi","=",$request->konusmaciSoyadi)->pluck("id")->first();
        Konusmaci::find($id)->delete();
        //return $id->first();




    }
}
