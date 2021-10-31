<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\EtkinlikIletisimKayit;
use Illuminate\Http\Request;
use phpDocumentor\Reflection\Types\Collection;

class EtkinlikIletisimKayitController extends Controller
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
        /*$data =$request->all();
        $iletisimAdresi= EtkinlikIletisimKayit::create($data);
        return $iletisimAdresi->id;
*/
        // Create a new collection instance.
        $collection = collect();

        $datas =$request->iletisim_adresi;
        foreach ($datas as $data){
            EtkinlikIletisimKayit::insert(['iletisim_adresi' => $data]);
            $collection->push(EtkinlikIletisimKayit::pluck('id')->last());
        }
        return $collection->all();
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
    public function delete(Request $request)
    {
        EtkinlikIletisimKayit::deleteIletisim($request);
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
       // EtkinlikIletisimKayit::guncelleme($id,$request);
    }

    public function getIletisimId(Request $request){

        $guncellenecekEtkinlikId = $request->etkinlik_id;
        $query = EtkinlikIletisimKayit::select("etkinlik_iletisim_kayitlari.id")
            ->join('etkinlik_iletisimleri','etkinlik_iletisimleri.iletisim_id',"=","etkinlik_iletisim_kayitlari.id")
            ->where("etkinlik_id","=",$guncellenecekEtkinlikId); //126 etkinliğinin
        return $query->pluck("id");

       // return EtkinlikIletisimKayit::whereIn('iletisim_adresi',$iletisimAdresi)->pluck("id");
    }

    public function modifyIletisimAdresi(Request $request){
        $guncellenecekAdresler = $request->iletisim_adresi ; //[beyza,basak]
        $guncellenecekId = $request-> iletisim_id; //[128,129]

        $adresler = EtkinlikIletisimKayit::whereIn('id',$guncellenecekId)->get(); //[128 beyza, 129 bsk]

        $a = 0;
        while ($a < count($guncellenecekId)) {

            $adresler[$a]->update([
                "iletisim_adresi" => $guncellenecekAdresler[$a]
            ]);

            $a += 1;

        }
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
