<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Adres;
use App\Models\Etkinlik;
use App\Models\EtkinlikKapsam;
use App\Models\KullaniciEtkinlik;
use App\Models\Topluluk;
use App\Models\Kapsam;
use App\Services\EtkinlikService;
use Exception;
use App\Http\Filters\EtkinlikFilter;
use Illuminate\Http\Request;
use SebastianBergmann\Comparator\Comparator;
use function Sodium\compare;


class EtkinlikController extends Controller
{
    private $etkinlikService;

    public function __construct(EtkinlikService $etkinlikService)
    {
        $this->etkinlikService = $etkinlikService;
    }

    public function idModel( Request $request){
        $size = Etkinlik::count();
        $etkinlik = Etkinlik::all();

        for($i=0;$i<$size;$i++){
            $e = $etkinlik->get($i)->only("etkinlik_adi","ucret","etkinlik_tarihi","aciklama","id");

            if($e["etkinlik_adi"] == $request->etkinlik_adi &&
                $e["ucret"] == $request->ucret && $e["etkinlik_tarihi"] == $request->etkinlik_tarihi &&
                $e["aciklama"] == $request->aciklama)   return $e["id"];

        }

    }

    public function iletisimAdresi(Request $request){
        return Etkinlik::iletisimAdresi($request->etkinlik_id);

    }

    public function konusmaciAdi(Request $request){
        return Etkinlik::konusmaciAdi($request->etkinlik_id);

    }
    public function konusmaciSoyadi(Request $request){
        return Etkinlik::konusmacisoyAdi($request->etkinlik_id);
    }
/*
 *  ETKİNLİK DETAY
 */
    public function detay(Request $request){

        $collection = collect();
        $etkinlik = Etkinlik::find($request->id);


        $adres = $etkinlik->adresi()->get();
        $ilceSemti= $adres[0]->ilceSemti()->get(0);
        $iletisimAdresleri = $etkinlik->iletisimAdresleri();


        $i = 0;
        while($i<count($iletisimAdresleri)){
            if ($iletisimAdresleri->get($i)->id == $etkinlik->id) {
                $collection->push($iletisimAdresleri->get($i)->iletisim_adresi);
            }
            $i+=1;
        }

        $result= [
            'etkinlik' => $etkinlik,
            'etkinlik_kapsami' => $etkinlik->kapsamlari()->distinct()->pluck('kapsam_adi')->first(),
            'etkinlik_toplulugu' => $etkinlik->topluluklari()->distinct()->pluck('topluluk_adi'),
            'konusmaci_adi' =>$etkinlik->konusmacilari()->orderBy("konusmacilar.id","asc")->selectRaw("CONCAT(adi,' ',soyadi) AS 'ad_soyad'")->pluck('ad_soyad'),
            'acik_adres' =>  $adres->pluck('acik_adres')->get(0),
            'ilce' => $ilceSemti->ilcesi()->pluck('ilce_adi')->get(0),
            'semt' => $ilceSemti->semti()->pluck('semt_adi')->get(0),
            'iletisim_adresi' =>$collection//->pluck('iletisim_adresi')
            /*
             *
             */


        ];
        return $result;

    }

    public function etkinlikler(){
        $etkinlikAdresleri = Etkinlik::etkinlikListe();
        return $etkinlikAdresleri;
    }

        /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {



        return $this->etkinlikService->getEtkinlik();

    }


    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\JsonResponse
     */
    public function store(Request $request)
    {
        /**
         * $etkinlik = $request->all();
           $etkinlik= Etkinlik::create($etkinlik);
           return response()->json($etkinlik);
         */

        $data = $request->all();
       try {
            $result= [
                'status' => 200,
                'etkinlik' => $this->etkinlikService->storeEtkinlik($data)
            ];
        } catch (Exception $e) {
            $result=[
                'status'=>500,
                'error'=> $e->getMessage()
            ];
        }
        return $result;

    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Etkinlik  $etkinlik
     * @return \Illuminate\Http\Response
     */
    public function show($id){
        try {
            $result= [
                'status' => 200,
                'etkinlik' => $this->etkinlikService->showByIdEtkinlik($id)
            ];
        }catch (Exception $e){
            $result=[
                'status'=>500,
                'error'=> 'Model not found'
            ];
        }
        return $result;

    }




    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Etkinlik  $etkinlik
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $data = $request -> all(); // güncelleme yapmak istediğimiz alanlar
        try {
            $result= [
                'status' => 200,
                'etkinlik' => $this->etkinlikService->updateEtkinlik($data,$id)
            ];
        }catch (Exception $e){
            $result=[
                'status'=>500,
                'error'=> $e->getMessage()
            ];
        }

        return $result;

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Etkinlik  $etkinlik
     * @return \Illuminate\Http\Response
     */

    public function destroy($id)
    {
        //$etkinlik=Etkinlik::find($id);
        //$etkinlik-> delete($etkinlik);
        try {
            $result= [
                'status' => 200,
                'data' => $this->etkinlikService->deleteEtkinlik($id)
            ];
        }catch (Exception $e){
            $result=[
                'status'=>500,
                'error'=> $e->getMessage()
            ];
        }
        //return $result;
    }

    public function kapsamFiltrele(Request $request){
        $ad=explode(",",$request->kapsamAdi);
        return Etkinlik::kapsamFiltrele($ad)->get();
    }
    public function toplulukFiltrele(Request $request){
        $ad=explode(",",$request->toplulukAdi);
        return Etkinlik::toplulukFiltrele($ad)->get();
    }

    public function filter(Request $request,EtkinlikFilter $filter){
        $etkinlik =  Etkinlik::filter($filter)->get();


        return $etkinlik;
    }

}
