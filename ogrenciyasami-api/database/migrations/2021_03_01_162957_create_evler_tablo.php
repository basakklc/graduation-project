<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEvlerTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('evler', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('adres_id')->unsigned();
            $table->bigInteger('oda_sayisi_id')->unsigned();
            $table->string('baslik',60);
            $table->smallInteger('m2');
            $table->string('bulundugu_kat',5);
            $table->string('kat_sayisi',5);
            $table->boolean('esya_durumu');
            $table->text('aciklama');
            $table->double('ucret');
            $table->timestamps();

            $table->foreign('adres_id')->references('id')->on('adresler')->onDelete('cascade');
            $table->foreign('oda_sayisi_id')->references('id')->on('oda_sayilari')->onDelete('cascade');


        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('evler');
    }
}
