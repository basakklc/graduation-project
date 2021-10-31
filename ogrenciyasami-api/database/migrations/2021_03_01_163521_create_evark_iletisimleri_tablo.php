<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEvarkIletisimleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('evark_iletisimleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('evark_id')->unsigned();
            $table->bigInteger('iletisim_id')->unsigned();

            $table->foreign('evark_id')->references('id')->on('ev_arkadaslari')->onDelete('cascade');
            $table->foreign('iletisim_id')->references('id')->on('evark_iletisim_kayitlari')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('evark_iletisimleri');
    }
}
