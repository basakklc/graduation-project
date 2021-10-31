<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateIlcelerSemtlerTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ilceler_semtler', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('ilce_id')->unsigned();
            $table->bigInteger('semt_id')->unsigned();

            $table->foreign('ilce_id')->references('id')->on('ilceler')->onDelete('cascade');
            $table->foreign('semt_id')->references('id')->on('semtler')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('ilceler_semtler');
    }
}
