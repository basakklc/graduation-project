<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Adres;
use App\Models\Etkinlik;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use Illuminate\Http\Request;

class AdresController extends Controller
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
        $data =$request->all();
        return Adres::create($data);
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
    public function getAdresId(Request $request){

        $guncellenecekEtkinlikId = $request->etkinlik_id;
        $query = Adres::where("etkinlik_id","=",$guncellenecekEtkinlikId)->pluck("id")->first(); //126 etkinliğinin
        return $query;

    }

    public function modifyAdres(Request $request){

        $guncellenecekIlceSemtId = $request->ilce_semt_id ;
        $guncellenecekAcikAdres = $request->acik_adres;

        $adresId = $request->adres_id;


        $adres = Adres::where('id',"=",$adresId)->update([
                "acik_adres"=> $guncellenecekAcikAdres,
                "ilce_semt_id"=>$guncellenecekIlceSemtId,
                "etkinlik_id" =>226
            ]);


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
        $adres=Adres::find($id);
        if($adres == null)
            throw new ModelNotFoundException();

        $adres -> update($data);
        return $adres;

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
