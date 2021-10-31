<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDersIletisimleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ders_iletisimleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('ders_id')->unsigned();
            $table->bigInteger('iletisim_id')->unsigned();

            $table->foreign('ders_id')->references('id')->on('dersler')->onDelete('cascade');
            $table->foreign('iletisim_id')->references('id')->on('ders_iletisim_kayitlari')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('ders_iletisimleri');
    }
}
