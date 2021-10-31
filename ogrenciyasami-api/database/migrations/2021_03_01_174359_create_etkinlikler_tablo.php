<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEtkinliklerTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('etkinlikler', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('adres_id')->unsigned();
            $table->string('etkinlik_adi',60);
            $table->text('aciklama');
            $table->string('etkinlik_tarihi');
            $table->double('ucret');

            $table->timestamps();

            $table->foreign('adres_id')->references('id')->on('adresler')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('etkinlikler');
    }
}
