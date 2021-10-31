<?php

namespace App\Models;

use App\Http\Filters\Filterable;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Http\Request;

class Topluluk extends Model
{
    use Filterable;
    protected $table='topluluklar';
    const UPDATED_AT = null;
    const CREATED_AT = null;
    protected $guarded=[];
    public static function idGetir($name){
        $topluluk = Topluluk::where('topluluk_adi','=',$name)->first();
        return $topluluk->id;
    }
    public function etkinlikleri()
    {
        return $this->belongsToMany('App\Models\Etkinlik', 'etkinlik_kapsamlari');
    }
}
