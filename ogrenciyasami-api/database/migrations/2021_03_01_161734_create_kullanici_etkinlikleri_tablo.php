<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateKullaniciEtkinlikleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('kullanici_etkinlikleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('kullanici_id')->unsigned();
            $table->bigInteger('etkinlik_id')->unsigned();

            $table->foreign('kullanici_id')->references('id')->on('kullanicilar')->onDelete('cascade');
            $table->foreign('etkinlik_id')->references('id')->on('etkinlikler')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('kullanici_etkinlikleri');
    }
}
