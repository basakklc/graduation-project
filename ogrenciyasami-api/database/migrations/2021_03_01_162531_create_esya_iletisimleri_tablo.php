<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEsyaIletisimleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('esya_iletisimleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('esya_id')->unsigned();
            $table->bigInteger('iletisim_id')->unsigned();

            $table->foreign('esya_id')->references('id')->on('esyalar')->onDelete('cascade');
            $table->foreign('iletisim_id')->references('id')->on('esya_iletisim_kayitlari')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('esya_iletisimleri');
    }
}
