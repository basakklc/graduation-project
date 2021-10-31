<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\EtkinlikIletisim;
use App\Models\EtkinlikIletisimKayit;
use App\Models\EtkinlikKapsam;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class EtkinlikIletisimController extends Controller
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
        $etkinlik_id=$request->get('etkinlik_id');
        $iletisimAdresleri=$request->get('iletisim_adresi');
        foreach ($iletisimAdresleri as $adres){
            EtkinlikIletisim::insert([
                'etkinlik_id' => $etkinlik_id,
                'iletisim_id' => $adres]);
            //$iletisimAdresleri->add(EtkinlikIletisim::pluck('id')->last());
        }


        //$data=$request->all();
        //EtkinlikIletisim:: create($data);
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
        $data=$request->all(); // 2.grup
        EtkinlikIletisim::guncelleme($id,$request);

        /*


        $data=$request->all();
        $etkinlikIletisimleri=EtkinlikIletisim::find($id);
        if($etkinlikIletisimleri == null)
            throw new ModelNotFoundException();

        $etkinlikIletisimleri -> update($data);
        return $etkinlikIletisimleri;*/

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
