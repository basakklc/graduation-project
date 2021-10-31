<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEtkinlikKapsamlariTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('etkinlik_kapsamlari', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('topluluk_id')->unsigned();
            $table->bigInteger('kapsam_id')->unsigned();
            $table->bigInteger('etkinlik_id')->unsigned();


            $table->foreign('topluluk_id')->references('id')->on('topluluklar')->onDelete('cascade');
            $table->foreign('kapsam_id')->references('id')->on('kapsamlar')->onDelete('cascade');
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
        Schema::dropIfExists('etkinlik_kapsamlari');
    }
}
