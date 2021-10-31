package com.example.mvvmdemoproject.domain.model

data class EtkinlikFormModel(
    var adres:String,
    var etkinlik:String,
    var tarih:String,
    var aciklama:String,
    var ucret:Number,
    var ilceId:Number,
    var semtId:Number,
    var kapsamList:List<Int>,
    var toplulukList:List<Int>,
    var konusmaciAd:List<String>,
    var konusmaciSoyad:List<String>,
    var iletisimAdresi:Array<String> )