<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEtkinlikKonusmacilariTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('etkinlik_konusmacilari', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('konusmaci_id')->unsigned();
            $table->bigInteger('etkinlik_id')->unsigned();

            $table->foreign('konusmaci_id')->references('id')->on('konusmacilar')->onDelete('cascade');
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
        Schema::dropIfExists('etkinlik_konusmacilari');
    }
}
