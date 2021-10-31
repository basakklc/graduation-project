<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateKullaniciDersleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('kullanici_dersleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('kullanici_id')->unsigned();
            $table->bigInteger('ders_id')->unsigned();

            $table->foreign('kullanici_id')->references('id')->on('kullanicilar')->onDelete('cascade');
            $table->foreign('ders_id')->references('id')->on('dersler')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('kullanici_dersleri');
    }
}
