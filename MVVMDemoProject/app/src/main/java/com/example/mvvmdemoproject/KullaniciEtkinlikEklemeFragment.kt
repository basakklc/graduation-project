package com.example.mvvmdemoproject

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mvvmdemoproject.data.repository.EtkinlikOlusturmaRepositoryImpl
import com.example.mvvmdemoproject.databinding.KullaniciEtkinlikFormuBinding
import com.example.mvvmdemoproject.domain.model.EtkinlikFormModel
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.usecase.EtkinlikOlusturmaUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikOlusturmaViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikOlusturmaViewModelFactory
import kotlinx.android.synthetic.main.kullanici_etkinlik_formu.view.*
import java.util.Observer

/**
 * A simple [Fragment] subclass.
 */
class KullaniciEtkinlikEklemeFragment : Fragment() {
    lateinit var rootView: View
    private lateinit var etkinlikOlusturmaViewModel: EtkinlikOlusturmaViewModel
    var repository = EtkinlikOlusturmaRepositoryImpl()
    var etkinlikOlusturmaUseCase = EtkinlikOlusturmaUseCase(repository)
    lateinit var binding: KullaniciEtkinlikFormuBinding
    lateinit var args:Bundle


    var ilceID = 0
    var semtID =0
    var kapsamID =0
    var toplulukID = listOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(it.containsKey(ARG_PARAM1)) kapsam = it.getString(ARG_PARAM1)!!
            if(it.containsKey(ARG_PARAM2)){
                val liste =it.getStringArray(ARG_PARAM2)!! as Array<String>
                topluluklar = liste.toMutableList()
            }
            if(it.containsKey(ARG_PARAM3)) semt = it.getString(ARG_PARAM3)!!
            if(it.containsKey(ARG_PARAM4)) ilce = it.getString(ARG_PARAM4)!!


            if(it.containsKey(ARG_PARAM5)){
                val liste =it.getStringArray(ARG_PARAM5)!! as Array<String>
                ad = liste.toMutableList()
            }
            if(it.containsKey(ARG_PARAM6)){
                val liste =it.getStringArray(ARG_PARAM6)!! as Array<String>
                soyad = liste.toMutableList()
            }
            if(it.containsKey(ARG_PARAM7)){
                kullaniciId = it.getInt(ARG_PARAM7)
            }

            Log.e("KULLANICI" , kullaniciId.toString())
            Log.e("AD SOYAD","AD : " + ad.toString()+"SOYAD : "+ soyad.toString())


        }





    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("create","bir")
        rootView = inflater.inflate(R.layout.fragment_kullanici_etkinlik_ekleme, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(),R.layout.kullanici_etkinlik_formu)

        var etkinlikViewModelFactory = EtkinlikOlusturmaViewModelFactory(etkinlikOlusturmaUseCase)
        etkinlikOlusturmaViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikOlusturmaViewModel::class.java)


        fragmentLabel()

        setupUI()
        observableViewModel()
        observableData()



        return rootView

    }

    private fun fragmentLabel() {
        args = Bundle()
        args.putString("currentFragment",findNavController().currentDestination?.label.toString())

    }

    private fun setupUI() {

        /*if(etkinlikAdi != "") binding.tvAd.setText(etkinlikAdi)
        if(aciklama!= "") binding.tvAciklama.setText(aciklama)
        if(acikAdres!= "") binding.tvAd.setText(acikAdres)
        if(ucret!= 0) binding.tvUcret.setText(ucret)
        if(tarih!= "") binding.tvTarih.setText(tarih)*/
        binding.tvKapsamAdi.text=kapsam
        binding.tvilceAdi.text= ilce
        binding.tvSemtAdi.text= semt
        binding.tvToplulukAdi.text=topluluklar.toString()
        val mapped= mutableListOf<String>()
        for (i in 0..ad.size-1){
            mapped.add(i, ad.get(i)+" "+ soyad.get(i))
        }
        binding.tvKonusmacilar.text = mapped.toString()



        //binding.tvKonusmacilar.text=a

//paremtre aktarımı


    }
    private fun observableViewModel() {
        etkinlikOlusturmaViewModel.getEtkinlikler().observe(requireActivity(),androidx.lifecycle.Observer{ //it -> renderList(it)
            //if (it!=null) Toast.makeText(applicationContext,"heyyyy",Toast.LENGTH_LONG).show()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.tvKapsamAdi.setOnClickListener {

            Navigation.findNavController(rootView).navigate(R.id.action_kullaniciEtkinlikEkleme_to_kapsamListFragment,args)
        }
        binding.tvToplulukAdi.setOnClickListener {
            Navigation.findNavController(rootView).navigate(R.id.action_kullaniciEtkinlikEkleme_to_toplulukListFragment,args)

        }
        binding.tvilceAdi.setOnClickListener {
            Navigation.findNavController(rootView).navigate(R.id.action_kullaniciEtkinlikEkleme_to_ilceListFragment,args) }
        binding.tvSemtAdi.setOnClickListener {
            etkinlikOlusturmaViewModel.getSemt(getIlce()).observe(requireActivity(), androidx.lifecycle.Observer { x ->
                observableSemt(x)
            })

        }

        binding.tvKonusmacilar.setOnClickListener {
            Navigation.findNavController(rootView).navigate(R.id.action_kullaniciEtkinlikEkleme_to_konusmaciEklemeFragment,args)
        }

        /********************************/

        binding.tvAcikAdres.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                setAcikAdres(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        binding.tvAd.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                setEtkinlikAdi(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        binding.tvTarih.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                setTarih(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        binding.tvUcret.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                setUcret(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        binding.tvAciklama.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                setAciklama(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        /**********************************/




        binding.btnEkle.setOnClickListener {
            //if ... alanları doldurun.
            val adres = binding.tviletisimAdresi.text.toString()
            val iletisimAdres = adres.split(",").toTypedArray()

            val iID= getIlce()
            val sID =getSemt()
            val tID =getTopluluk()
            val kID = getKapsam()
            val adr = getAcikAdres()
            val aciklama = getAciklama()
            val tarih=getTarih()
            val ucret = getUcret()
            val etkinlikAdi = getAd()



            val etkinlikOlusturmaRequest = EtkinlikOlusturmaRequest(etkinlikAdi, aciklama, tarih, ucret)

            val etkinlikFormModel = EtkinlikFormModel(adr,etkinlikOlusturmaRequest.etkinlik_adi, etkinlikOlusturmaRequest.etkinlik_tarihi, etkinlikOlusturmaRequest.aciklama,
                etkinlikOlusturmaRequest.ucret, iID, sID, listOf(kID),tID, ad, soyad,iletisimAdres)

            Log.e("kBTN", kullaniciId.toString())
                etkinlikOlusturmaViewModel.kulaniciID= kullaniciId


                etkinlikOlusturmaViewModel.etkinlikFormModel = etkinlikFormModel
                etkinlikOlusturmaViewModel.createEtkinlik(etkinlikOlusturmaRequest)


        }
    }

    private fun observableData() {

        var y = binding.tvToplulukAdi.text.toString()
        y= y.replace("[","")
        y=y.replace("]","")

        val etkinlikTopluluklari = y.split(",")

        etkinlikOlusturmaViewModel.adres(binding.tvilceAdi.text.toString(),binding.tvSemtAdi.text.toString())
        etkinlikOlusturmaViewModel.fetchSemtID().observe(requireActivity(), androidx.lifecycle.Observer { x ->
            setSemt(x)
        })
        etkinlikOlusturmaViewModel.fetchIlceID().observe(requireActivity(), androidx.lifecycle.Observer { x ->
            setIlce(x)
        })
        etkinlikOlusturmaViewModel.fetchToplulukID(etkinlikTopluluklari).observe(requireActivity(), androidx.lifecycle.Observer { x ->
           setTopluluk(x)
        })
        etkinlikOlusturmaViewModel.fetchKapsamID(binding.tvKapsamAdi.text.toString()).observe(requireActivity(), androidx.lifecycle.Observer { x ->
           setKapsam(x)
        })
    }

    private fun setKapsam(x: Int) {
        kapsamID =x
    }
    private fun setTopluluk(x: List<Int>) {
        toplulukID =x
    }
    private fun setSemt(x: Int) {
        semtID= x
    }
    private fun setIlce(x: Int) {
        ilceID =x
    }
    private fun setAcikAdres(data:String?){
        if(! data.isNullOrEmpty()){
            acikAdres = data }
    }
    private fun setEtkinlikAdi(data:String?){
        if(! data.isNullOrEmpty()){
            etkinlikAdi = data}
    }
    private fun setTarih(data:String?){
        if(! data.isNullOrEmpty()){
            tarih = data}
    }
    private fun setAciklama(data:String?){
        if(! data.isNullOrEmpty()){
            aciklama = data}
    }
    private fun setUcret(data:String?){
        if(! data.isNullOrEmpty()){
            ucret = data.toInt()}
    }

    private fun getKapsam(): Int {
       return kapsamID
    }
    private fun getTopluluk(): List<Int> {
        return toplulukID
    }
    private fun getSemt(): Int {
        return semtID
    }
    private fun getIlce(): Int {
        return ilceID
    }
    private fun getAcikAdres():String{
        return acikAdres
    }
    private fun getTarih():String{
        return tarih

    }
    private fun getUcret():Number{
        return ucret
    }
    private fun getAciklama():String{
        return aciklama
    }
    private fun getAd():String{
        return etkinlikAdi
    }



    private fun observableSemt(x:List<String>){
        args.putStringArray("semtListesi",x.toTypedArray())
        Navigation.findNavController(rootView).navigate(R.id.action_kullaniciEtkinlikEkleme_to_semtListFragment,args)

    }

    private fun renderList(it: List<EtkinlikListelemeResponse>) {

    }

    companion object {
        var kullaniciId=0
        var etkinlikAdi=""
        var aciklama=""
        var tarih = ""
        var ucret = 0
        var acikAdres =""

        var kapsam = ""
        var semt=""
        var ilce=""
        var topluluklar = listOf<String>()
        var ad = listOf<String>()
        var soyad = listOf<String>()

        var ARG_PARAM1 = "kapsam"
        var ARG_PARAM2 = "topluluk"
        var ARG_PARAM3 = "semt"
        var ARG_PARAM4 ="ilce"
        var ARG_PARAM5 = "konusmaciAdlari"
        var ARG_PARAM6 = "konusmaciSoyadlari"
        var ARG_PARAM7 = "kullaniciId"

        @JvmField
        var adSoyadMapped: MutableList<List<String>> = mutableListOf()
        var map: MutableList<List<String>> = mutableListOf()
       // var semt:List<String> = listOf()
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EtkinlikGuncellemeFragment.
         */
        // TODO: Rename and change types and number of parameters

        fun newInstance(param1: String, param2: String) =
            KullaniciEtkinlikEklemeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}