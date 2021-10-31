<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEvIsitmalariTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('ev_isitmalari', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->bigInteger('ev_id')->unsigned();
            $table->bigInteger('isitma_id')->unsigned();

           $table->foreign('ev_id')->references('id')->on('evler')->onDelete('cascade');
           $table->foreign('isitma_id')->references('id')->on('isitmalar')->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('ev_isitmalari');
    }
}
