<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEvArkadaslariTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ev_arkadaslari', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('adres_id')->unsigned();
            $table->bigInteger('oda_sayisi_id')->unsigned();
            $table->text('aciklama');
            $table->string('baslik',60);
            $table->smallInteger('evde_yasayan_kisi_sayisi');
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
        Schema::dropIfExists('ev_arkadaslari');
    }
}
