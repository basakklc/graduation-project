<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateKullaniciEsyalariTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('kullanici_esyalari', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('kullanici_id')->unsigned();
            $table->bigInteger('esya_id')->unsigned();

            $table->foreign('kullanici_id')->references('id')->on('kullanicilar')->onDelete('cascade');
            $table->foreign('esya_id')->references('id')->on('esyalar')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('kullanici_esyalari');
    }
}
