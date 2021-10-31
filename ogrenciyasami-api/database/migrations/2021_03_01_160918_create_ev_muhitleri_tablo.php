<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEvMuhitleriTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ev_muhitleri', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('ev_id')->unsigned();
            $table->bigInteger('muhit_id')->unsigned();

            $table->foreign('ev_id')->references('id')->on('evler')->onDelete('cascade');
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
        Schema::dropIfExists('ev_muhitleri');
    }
}
