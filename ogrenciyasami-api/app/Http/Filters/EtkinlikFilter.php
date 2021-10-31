<?php


namespace App\Http\Filters;


use App\Models\Topluluk;
use Illuminate\Http\Request;

class EtkinlikFilter extends QueryFilter
{
    protected $request;
    public function __construct(Request $request)
    {
        $this->request = $request;
        parent::__construct($request);
    }
    public function etkinlikTarihi($type) {
        return $this->builder->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
            ->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
            ->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
            ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')->orderBy('etkinlik_tarihi', ($type == 'asc') ? 'asc' : 'desc');
    }
    public function ucret($type) {
        return $this->builder->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
            ->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
            ->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
            ->join('semtler','semtler.id','=','ilceler_semtler.semt_id')->orderBy('ucret', ($type == 'asc') ? 'asc' : 'desc');
    }

}
