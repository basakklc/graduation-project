<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Semt extends Model
{
    protected $table='semtler';
    protected $guarded=[];

    public function ilceler()
    {
        return $this-> belongsToMany('App\Models\Ilce','ilceler_semtler');
    }

}
