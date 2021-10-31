<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateAdreslerTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('adresler', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('ilce_semt_id')->unsigned();
            $table->string('acik_adres',120);

            $table->foreign('ilce_semt_id')->references('id')->on('ilceler_semtler')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('adresler');
    }
}
