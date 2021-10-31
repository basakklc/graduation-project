<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\EtkinlikIletisimKayit;
use App\Models\Konusmaci;
use Illuminate\Http\Request;

class KonusmaciController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $i=0;
        $collection = collect();
        $isimler =$request->adi;
        $soyisimler =$request->soyadi;

        $konusmacilar  = array("konusmaci_adlari" => $isimler, "konusmaci_soyadlari" => $soyisimler);

        $ad =  $konusmacilar["konusmaci_adlari"];
        $sad =  $konusmacilar["konusmaci_soyadlari"];

        while($i<count($isimler)){

            Konusmaci::insert(['adi' =>$ad[$i], 'soyadi' => $sad[$i] ]);
            $collection->push(Konusmaci::pluck('id')->last());

            $i+=1;
        }

        return  $collection->all();

    }
    public function getKonusmaciId(Request $request){

        $guncellenecekEtkinlikId = $request->etkinlik_id;
        $query = Konusmaci::select("konusmacilar.id")
            ->join('etkinlik_konusmacilari','etkinlik_konusmacilari.konusmaci_id',"=","konusmacilar.id")
            ->where("etkinlik_id","=",$guncellenecekEtkinlikId); //126 etkinliğinin
        return $query->orderBy("konusmacilar.id","asc")->pluck("id");

    }

    public function modifyKonusmaciAdSoyad(Request $request){
        $guncellenecekAd = $request->konusmaci_ad ; //[beyza,basak]
        $guncellenecekSoyad = $request->konusmaci_soyad; //[nemli,kılıc]
        $guncellenecekId = $request-> konusmaci_id; //[128,129]


        $konusmacilar = Konusmaci::whereIn('id',$guncellenecekId)->get(); //[128 beyza, 129 bsk]

        $a = 0;
        while ($a < count($guncellenecekId)) {

            $konusmacilar[$a]->update([
                "adi" => $guncellenecekAd[$a],
                "soyadi" => $guncellenecekSoyad[$a]
            ]);

            $a += 1;

        }
    }




    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {

    }


    public function delete(Request $request)
    {
        Konusmaci::deleteKonusmaci($request);
    }


}
