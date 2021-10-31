<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Ilce extends Model
{
    protected $table='ilceler';
    protected $guarded=[];

    public function semtler()
    {

        $ilcelerSemtler = $this-> belongsToMany('App\Models\Semt','ilceler_semtler');
        return $ilcelerSemtler;

    }

    public static function semtleri($id){ // 2

        $semtler = Ilce::join('ilceler_semtler','ilceler_semtler.ilce_id','=','ilceler.id')
            ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')
            ->where("ilce_id","=",$id);
       // return gettype($semtler);
        //foreach ($semtler as $semt)
            //return $semt[0];
        return $semtler->pluck('semt_adi');//->pluck('semt_adi');

    }


}
