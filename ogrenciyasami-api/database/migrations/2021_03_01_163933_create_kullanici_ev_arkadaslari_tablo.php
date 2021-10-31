<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateKullaniciEvArkadaslariTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('kullanici_ev_arkadaslari', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('kullanici_id')->unsigned();
            $table->bigInteger('evark_id')->unsigned();

            $table->foreign('kullanici_id')->references('id')->on('kullanicilar')->onDelete('cascade');
            $table->foreign('evark_id')->references('id')->on('ev_arkadaslari')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('kullanici_ev_arkadaslari');
    }
}
