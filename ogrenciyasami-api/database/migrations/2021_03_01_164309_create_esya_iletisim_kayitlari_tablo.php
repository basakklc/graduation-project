<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateEsyaIletisimKayitlariTablo extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('esya_iletisim_kayitlari', function (Blueprint $table) {
            $table->bigIncrements('id');
            $table->string('iletisim_adresleri',50);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('esya_iletisim_kayitlari');
    }
}
