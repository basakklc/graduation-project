<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateDerslerTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('dersler', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('bolum_id')->unsigned();
            $table->string('ders_basligi',60);
            $table->text('aciklama');
            //$table->dateTime('ilan_tarihi');
            $table->double('ucret');

            $table->foreign('bolum_id')->references('id')->on('bolumler')->onDelete('cascade');

            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('dersler');
    }
}
