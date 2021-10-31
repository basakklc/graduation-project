<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Konusmaci;
use App\Models\Kullanici;
use Illuminate\Http\Request;

class KullaniciController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $data = Kullanici::query()->with('etkinlikleri')->get();
        return response($data,200);
    }

    public function kullaniciEtkinlikleri(Request $request)  // kullanıcının etkinlikleri ->etkinlik çekilir.
    {
        $id = $request->kullanici_id;
        //$data = Kullanici::find($id)->etkinlikleri()->get();
        $data = Kullanici::find($id)->etkinlikler();
        //return $data->first()->id;
        return response($data,200); // bu bi liste idleri için colllectin yapısı oluşacak!!
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)     //kayıt ol -- comutr
    {
        $data = $request->all();
        $email=$data["email"];
        $sifre = $data["sifre"];
        $sifreTekrar = $data["sifre_tekrar"];
        $query = Kullanici::pluck("email");

        if(! str_contains($email,"@comu.edu.tr")){
            $result= [
                'error' => "Üniversite mail adresinizi girmelisiniz. ",
                'id' => 0
            ];
            return  $result;
        }

        for($i=0;$i<count($query);$i++){

            if($query[$i]==$email) {
                $result= [
                    'error' => "Email adresiniz kayıtlı ",
                    'id' => 0
                ];
                return $result;
            }


        }

        if($sifre == $sifreTekrar){
            $kullanici = Kullanici::create($data);
            $result= [
                'error' => "Kayıt tamamlandı ",
                'id' => $kullanici->id
            ];
            return $result;

        }
        else{
            $result= [
                'error' => "Şifreler farklı",
                'id' => 0
            ];
            return $result;
        }

    }

    public function uyeGirisi(Request $request){ //kullanici yoksa id 0 döner.
        $email=$request->email;
        $sifre=$request->sifre;
        $kullanici = Kullanici::where("email","=",$email)->where("sifre","=",$sifre)->get();
        if(count($kullanici)==0){
            return 0;
        }

        return $kullanici->first()->id;

    } // id döner

    public function sifremiUnuttum(Request $request){
        $email=$request->email;
        $sifre = $request->sifre;
        $sifreTekrar = $request->sifre_tekrar;

        $query = Kullanici::where("email","=",$email)->get();


        if(count($query)==0) {
            $result= [
                'email' => 500,
                'sifre' =>500
            ];
            return $result;

        }

        if($sifre == $sifreTekrar){
            $query=$query->first()->id;
            $kullanici = Kullanici::find($query)->update([
               "sifre" => $sifre,
                "sifre_tekrar" => $sifreTekrar
            ]);
            $result= [
                'email' => 200,
                'sifre' =>200
            ];
            return $result;

        }
        else{
            $result= [
                'email' => 200,
                'sifre' =>500
            ];
            return $result;
        }



    }


    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Kullanici  $kullanici
     * @return \Illuminate\Http\Response
     */
    public function show(KullaniciController $kullanici)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \App\Models\Kullanici  $kullanici
     * @return \Illuminate\Http\Response
     */
    public function update($id,Request $request)
    {
        $data = $request->all();

        if(array_key_exists("email",$data)){
            $email=$data["email"];
            if(! str_contains($email,"@comu.edu.tr")) return;
            $query = Kullanici::pluck("email");
            for($i=0;$i<count($query);$i++){
                if($query[$i]==$email) return ;

            }


        }

        if(array_key_exists("sifre",$data) and array_key_exists("sifre_tekrar",$data) ){
            $sifre = $data["sifre"];
            $sifreTekrar = $data["sifre_tekrar"];

            if($sifre != $sifreTekrar) return;
        }
        Kullanici::find($id)->update($data);


    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Kullanici  $kullanici
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $konusmaci =Kullanici::find($id);
        $konusmaci -> delete();
    }
}
