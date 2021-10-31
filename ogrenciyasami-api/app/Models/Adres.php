<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Adres extends Model
{

    protected $table='adresler';
    protected $guarded=[];

    public const CREATED_AT = null;
    public const UPDATED_AT = null;
    public function ilceSemti()
    {
        return $this->belongsTo(IlceSemt::class, 'ilce_semt_id', 'id')->get();
    }

    public static function etkinlikListe()
    {
        $etkinlikler=Adres::join('etkinlikler','etkinlikler.id','=','adresler.etkinlik_id');
        return $etkinlikler->get();
    }


}
