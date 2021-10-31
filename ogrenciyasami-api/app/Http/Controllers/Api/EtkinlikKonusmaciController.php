<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\EtkinlikKapsam;
use App\Models\EtkinlikKonusmaci;
use App\Models\Konusmaci;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class EtkinlikKonusmaciController extends Controller
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
        $konusmacilar=$request->get('konusmaci_id');
        foreach ($konusmacilar as $konusmaci){
            EtkinlikKonusmaci::insert([
                'etkinlik_id' => $etkinlik_id,
                'konusmaci_id' => $konusmaci]);
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
        $data=$request->all();
        $etkinlikKonusmaci=EtkinlikKonusmaci::find($id);
        if($etkinlikKonusmaci == null)
            throw new ModelNotFoundException();

        $etkinlikKonusmaci -> update($data);
        return $etkinlikKonusmaci;

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
}
