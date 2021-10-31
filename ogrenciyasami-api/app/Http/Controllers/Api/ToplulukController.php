<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Filters\EtkinlikFilter;
use App\Http\Filters\ToplulukFilter;
use App\Models\Etkinlik;
use App\Models\Topluluk;
use Illuminate\Http\Request;

class ToplulukController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
       return Topluluk::pluck('topluluk_adi');
    }

    /**
     * Store a newly created resource in storag     * @param
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $topluluk= new Topluluk();
        /*$topluluk->topluluk_adi = $request->topluluk_adi;
        $topluluk->save();*/
        $data = $request->all();
        $topluluk =Topluluk::create($data);
        //return ($topluluk);
        return response()->json($topluluk);
    }

    /**
     * Display the specified resource.
     *
    pp\Models\Topluluk  $topluluk
     * @return \Illuminate\Http\Response
     */
    public function show(Topluluk $topluluk)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Topluluk  $topluluk
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, Topluluk $topluluk)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Topluluk  $topluluk
     * @return \Illuminate\Http\Response
     */
    public function destroy(Topluluk $topluluk)
    {
        //
    }

    public function getId(Request $request){
        $topluluklar = $request->topluluk_adi;

        return Topluluk::whereIn('topluluk_adi',$topluluklar)->pluck("id");
    }

    public function filter(Request $request,ToplulukFilter $filter)
    {
        $etkinlikler = collect();
        $toplulukEtkinlikleri = Topluluk::filter($filter)->get();

        $toplulukEtkinlikleri = json_decode($toplulukEtkinlikleri, true);
        return $toplulukEtkinlikleri;
        foreach ($toplulukEtkinlikleri as $topluluk)
                $etkinlikler->push($topluluk);

        return $etkinlikler->collapse()->all();





    }

}
