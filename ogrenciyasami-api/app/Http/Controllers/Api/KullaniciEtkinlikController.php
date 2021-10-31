<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Etkinlik;
use App\Models\Kullanici;
use App\Models\KullaniciEtkinlik;
use Illuminate\Http\Request;

class KullaniciEtkinlikController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {

    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {

        KullaniciEtkinlik::create($request->all());

        /*return response([
            'data'=> $kullaniciEtkinlik,
            'message' => 'Oluşturuldu'
        ],201);*/


    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\KullaniciEtkinlik  $kullaniciEtkinlik
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $kullanici = Kullanici::find($id);

        if ($kullanici)
            return response($kullanici->etkinlikleri,200);
        else
            return response(['message'=>'404 not found'],404);


    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\KullaniciEtkinlik  $kullaniciEtkinlik
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, KullaniciEtkinlik $kullaniciEtkinlik)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\KullaniciEtkinlik  $kullaniciEtkinlik
     * @return \Illuminate\Http\Response
     */
    public function destroy(KullaniciEtkinlik $kullaniciEtkinlik)
    {
        //
    }
}
