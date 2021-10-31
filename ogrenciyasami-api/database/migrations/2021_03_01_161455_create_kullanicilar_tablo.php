<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateKullanicilarTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('kullanicilar', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->string('adi',30);
            $table->string('soyadi',30);
            $table->string('email',50);
            $table->string('sifre',15);
            $table->string('sifre_tekrar',15);

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
        Schema::dropIfExists('kullanicilar');
    }
}
