<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEvIletisimleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ev_iletisimleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('ev_id')->unsigned();
            $table->bigInteger('iletisim_id')->unsigned();

            $table->foreign('ev_id')->references('id')->on('evler')->onDelete('cascade');
            $table->foreign('iletisim_id')->references('id')->on('ev_iletisim_kayitlari')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('ev_iletisimleri');
    }
}
