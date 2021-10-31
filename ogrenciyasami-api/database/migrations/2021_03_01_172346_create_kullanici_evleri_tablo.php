<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateKullaniciEvleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('kullanici_evleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('kullanici_id')->unsigned();
            $table->bigInteger('ev_id')->unsigned();

            $table->foreign('kullanici_id')->references('id')->on('kullanicilar')->onDelete('cascade');
            $table->foreign('ev_id')->references('id')->on('evler')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('kullanici_evleri');
    }
}
