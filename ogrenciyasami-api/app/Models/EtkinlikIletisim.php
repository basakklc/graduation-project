<?php

namespace App\Models;


use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Support\Facades\DB;
use function PHPUnit\Framework\isEmpty;

class EtkinlikIletisim extends Model
{

    protected $table='etkinlik_iletisimleri';
    protected $guarded=[];

    protected  $etkinlikSayisi;
    protected  $iletisimAdresiSayisi;

    public static function updateRow($etkinlik,$iletisimIDList,$listIndisControl){
        $a = 0;
        //$topluluk_sayisi = count($topluluklar);

        while ($a < $listIndisControl ) {
            $etkinlik[$a]->update([
                "iletisim_id" => $iletisimIDList[$a],
            ]);
            $a += 1;

        }
        return $a;
    }

    public static function guncelleme($etkinlik_id,$data){
        $etkinlik =  EtkinlikIletisim::where("etkinlik_id",$etkinlik_id)->get();
        $iletisimIDList = $data->iletisim_id;

        $etkinlikSayisi = $etkinlik->count();
        $indis = EtkinlikIletisim::updateRow($etkinlik,$iletisimIDList,$etkinlikSayisi);


    }

    public const CREATED_AT = null;
    public const UPDATED_AT = null;
}
