<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEtkinlikIletisimleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('etkinlik_iletisimleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('etkinlik_id')->unsigned();
            $table->bigInteger('iletisim_id')->unsigned();

            $table->foreign('etkinlik_id')->references('id')->on('etkinlikler')->onDelete('cascade');
            $table->foreign('iletisim_id')->references('id')->on('etkinlik_iletisim_kayitlari')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('etkinlik_iletisimleri');
    }
}
