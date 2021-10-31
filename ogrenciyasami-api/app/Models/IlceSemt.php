<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class IlceSemt extends Model
{
    protected $table = 'ilceler_semtler';
    protected $guarded = [];

    public function semti(){
        return $this->belongsTo('App\Models\Semt','semt_id','id')->get();
    }
    public function ilcesi(){
        return $this->belongsTo('App\Models\Ilce','ilce_id','id')->get();
    }
}
