<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Adres;
use App\Models\EtkinlikKapsam;
use App\Models\Kapsam;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;
use function PHPUnit\Framework\isEmpty;

class EtkinlikKapsamController extends Controller
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
        $topluluklar=$request->topluluk_id;
        $etkinlikKapsamlari = array("topluluklari"=>$topluluklar);
        $i=0;

        while($i<count($topluluklar)){
            EtkinlikKapsam::insert(['etkinlik_id' =>$request->etkinlik_id,
                                    'topluluk_id'=> $etkinlikKapsamlari['topluluklari'][$i],
                                    'kapsam_id' =>$request->kapsam_id]);

            $i+=1;
        }
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\EtkinlikKapsam  $etkinlikKapsam
     * @return \Illuminate\Http\Response
     */
    public function show(EtkinlikKapsam $etkinlikKapsam)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\EtkinlikKapsam  $etkinlikKapsam
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $etkinlik_id)
    {
        //kapsam_adi = request-> kapsam_adi //arkeolji --> [bitek,çasad]
        // idler=Kapsam::whereIn('kapsam_adi',"=",kapsam_adi)->pluck("id")
        // request=[
        //      kapsam_id = idler
                //topluluk_id=
        //          ]

        EtkinlikKapsam::guncelleme($etkinlik_id,$request);




     //   $etkinlikKapsamlari=EtkinlikKapsam::find($etkinlik_id);
       // if($etkinlikKapsamlari == null)
           // throw new ModelNotFoundException();

      //  $etkinlikKapsamlari -> update($data);
       // return $etkinlikKapsamlari;

    }



    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\EtkinlikKapsam  $etkinlikKapsam
     * @return \Illuminate\Http\Response
     */
    public function destroy(EtkinlikKapsam $etkinlikKapsam)
    {
        //
    }
}
