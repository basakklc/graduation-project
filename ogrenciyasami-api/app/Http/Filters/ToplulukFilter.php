<?php


namespace App\Http\Filters;


use Illuminate\Http\Request;
use Symfony\Component\Console\Input\Input;
use function Symfony\Component\String\b;

class ToplulukFilter extends QueryFilter
{
    protected $request;
    public function __construct(Request $request)
    {
        $this->request = $request;
        parent::__construct($request);
    }

    public function toplulukAdi($name){

        $etkinlikler = $this->builder->join('etkinlik_kapsamlari','etkinlik_kapsamlari.topluluk_id',"=","topluluklar.id")
            ->join('etkinlikler','etkinlik_kapsamlari.etkinlik_id',"=","etkinlikler.id")
            ->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
            ->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
            ->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
            ->join('semtler','semtler.id','=','ilceler_semtler.semt_id');
        $ad=explode(",",$name);

        return $etkinlikler->whereIn('topluluklar.topluluk_adi',$ad);
        /*try {
            $etkinlikler->whereIn('topluluk_adi',$ad);
        }
        catch (Exception $exception){
            $etkinlikler->whereIn('topluluk_adi',$ad);
        }*/

    }



}
/**
 *         -> join('etkinlik_kapsamlari','etkinlik_kapsamlari.topluluk_id',"=","topluluklar.id")
->join('etkinlikler','etkinlik_kapsamlari.etkinlik_id',"=","etkinlikler.id")
->join('adresler', 'adresler.etkinlik_id', '=', 'etkinlikler.id')
->join('ilceler_semtler','ilceler_semtler.id','=','adresler.ilce_semt_id')
->join('ilceler','ilceler.id','=','ilceler_semtler.ilce_id')
->join('semtler','semtler.id','=','ilceler_semtler.semt_id')->with['etkinlikleri'];;
 */
