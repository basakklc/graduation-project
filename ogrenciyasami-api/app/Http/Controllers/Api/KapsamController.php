<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Filters\KapsamFilter;
use App\Http\Filters\ToplulukFilter;
use App\Models\Etkinlik;
use App\Models\Kapsam;
use App\Models\Topluluk;
use Illuminate\Http\Request;

class KapsamController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return Kapsam::pluck('kapsam_adi');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Kapsam  $kapsam
     * @return \Illuminate\Http\Response
     */
    public function show(Kapsam $kapsam)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Kapsam  $kapsam
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Kapsam $kapsam)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Kapsam  $kapsam
     * @return \Illuminate\Http\Response
     */
    public function destroy(Kapsam $kapsam)
    {
        //
    }

    public function getKapsamId(Request $request){
        $kapsamAdi = $request->kapsam_adi;
        return Kapsam::where('kapsam_adi',"=",$kapsamAdi)->first()->id;
    }

    public function filter(Request $request,KapsamFilter $filter)
    {
     /*   $liste =collect();
        $ad=explode(",",$request->kapsamAdi);

        $etkinlikler =  Kapsam::detay($ad)->get();
        return $etkinlikler;

        foreach ($etkinlikler as $etkinlik){
            $etkinlik = $etkinlik->only(['etkinlik_adi','etkinlik_tarihi','ucret','ilce_adi','semt_adi','acik_adres']);
            $liste->push($etkinlik);

        }
        return $liste;
       /* $etkinlikler = collect();
        $etkinliklerinKapsamlari = Kapsam::filter($filter)->get();
        //$etkinliklerinKapsamlari = json_decode($etkinliklerinKapsamlari, true);

        foreach ($etkinliklerinKapsamlari as $kapsam)
            $etkinlikler->push($kapsam['etkinlikleri']);


        $etkinlikler=$etkinlikler->collapse()->all();


        return $etkinlikler;*/
    }
}
