<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEsyalarTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('esyalar', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('esya_tur_id')->unsigned();
            $table->string('esya_basligi',100);
            $table->text('aciklama');
            //$table->dateTime('ilan_tarihi');
            $table->double('ucret');


            $table->timestamps();

            $table->foreign('esya_tur_id')->references('id')->on('esya_turleri')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('esyalar');
    }
}
