<?php

namespace App\Models;

use App\Http\Filters\Filterable;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Kapsam extends Model
{
    use Filterable;
    protected $table='kapsamlar';
    protected $guarded=[];

    public static function idGetir($name)
    {
        $kapsam = Kapsam::where('kapsam_adi', '=', $name)->first();
        return $kapsam ->id;
    }
    public static function detay($ad){
        $etkinlik = Etkinlik::select('etkinlik_adi','etkinlik_tarihi','ucret','ilce_adi','semt_adi','acik_adres','kapsam_adi')
            ->join('etkinlik_kapsamlari','etkinlik_kapsamlari.etkinlik_id',"=","etkinlikler.id")
            ->join('kapsamlar','kapsamlar.id',"=","etkinlik_kapsamlari.kapsam_id")
            ->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
            ->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
            ->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
            ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')

            ->distinct();

        return $etkinlik;
    }

    public function etkinlikleri()
    {
        return $this->belongsToMany('App\Models\Etkinlik', 'etkinlik_kapsamlari');
    }
}
