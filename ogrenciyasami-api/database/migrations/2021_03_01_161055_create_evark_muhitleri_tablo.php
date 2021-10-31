<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEvarkMuhitleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('evark_muhitleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('evark_id')->unsigned();
            $table->bigInteger('muhit_id')->unsigned();

            $table->foreign('evark_id')->references('id')->on('ev_arkadaslari')->onDelete('cascade');
            $table->foreign('muhit_id')->references('id')->on('muhitler')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('evark_muhitleri');
    }
}
