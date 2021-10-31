package com.example.mvvmdemoproject.presentation.main.view

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGuncellemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.EtkinlikGuncellemeFormuBinding
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModelFactory
import kotlinx.android.synthetic.main.etkinlik_guncelleme_formu.view.*
import java.lang.StringBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EtkinlikGuncellemeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EtkinlikGuncellemeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var etkinlikGuncellemeViewModel: EtkinlikGuncellemeViewModel
    var repository = EtkinlikGuncellemeRepositoryImpl()
    var etkinlikGuncellemeUseCase = EtkinlikGuncellemeUseCase(repository)

    lateinit var binding: EtkinlikGuncellemeFormuBinding

    lateinit var etkinlikModel : EtkinlikGoruntulemeResponse

    lateinit var rootView : View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if(it.containsKey(ARG_PARAM1)){
                val liste =it.getStringArray(ARG_PARAM1)!! as Array<String>
                guncelAd = liste.toMutableList()
            }
            if(it.containsKey(ARG_PARAM2)){
                val liste2 = it.getStringArray(ARG_PARAM2)!! as Array<String>
                guncelSoyad = liste2.toMutableList()
            }
            if(it.containsKey(ARG_PARAM3)) guncelKapsam = it.getString(ARG_PARAM3)!!

            if(it.containsKey(ARG_PARAM4)){
                val liste =it.getStringArray(ARG_PARAM4)!! as Array<String>
                guncelTopluluk = liste.toMutableList()

            }
            if(it.containsKey(ARG_PARAM5)) guncelSemt = it.getString(ARG_PARAM5)!!
            if(it.containsKey(ARG_PARAM6)) guncelIlce = it.getString(ARG_PARAM6)!!

            if(it.containsKey(ARG_PARAM7)) eID=it.getInt(ARG_PARAM7)



        }

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.etkinlik_guncelleme_formu, container, false)

        binding =DataBindingUtil.setContentView(requireActivity(),R.layout.etkinlik_guncelleme_formu)
        binding.setLifecycleOwner (viewLifecycleOwner)
        var etkinlikViewModelFactory = EtkinlikGuncellemeViewModelFactory(etkinlikGuncellemeUseCase, eID)
        etkinlikGuncellemeViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikGuncellemeViewModel::class.java)
        observableEtkinlikViewModel()

        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.tvKapsamAdi.setOnClickListener {


            val kapsamListDestination=rootView.findNavController().graph.findNode(R.id.kapsamListFragment)
            kapsamListDestination?.addArgument("kapsamAdi", NavArgument.Builder().setType(NavType.StringType).setDefaultValue(binding.bindingGuncellemeForm?.etkinlikKapsami).build())
            kapsamListDestination?.addArgument("currentFragment", NavArgument.Builder().setType(NavType.StringType).setDefaultValue(findNavController().currentDestination?.label).build())

            Navigation.findNavController(rootView).navigate(R.id.action_etkinlikGuncellemeFragment_to_kapsamListFragment)

        }
        binding.tvToplulukAdi.setOnClickListener {

            val toplulukListDestination=rootView.findNavController().graph.findNode(R.id.toplulukListFragment)
            toplulukListDestination?.addArgument("toplulukAdi", NavArgument.Builder().setType(NavType.StringArrayType).setDefaultValue(binding.bindingGuncellemeForm?.etkinlikToplulugu?.toTypedArray()).build())
            toplulukListDestination?.addArgument("currentFragment", NavArgument.Builder().setType(NavType.StringType).setDefaultValue(findNavController().currentDestination?.label).build())

            Navigation.findNavController(rootView).navigate(R.id.action_etkinlikGuncellemeFragment_to_toplulukListFragment)


            //  Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.action_etkinlikGuncellemeFragment_to_toplulukListFragment)
        }
        binding.tvilceAdi.setOnClickListener {
            val ilceListDestination=rootView.findNavController().graph.findNode(R.id.ilceListFragment)
            ilceListDestination?.addArgument("ilceAdi", NavArgument.Builder().setType(NavType.StringType).setDefaultValue(binding.bindingGuncellemeForm?.ilce).build())
            ilceListDestination?.addArgument("currentFragment", NavArgument.Builder().setType(NavType.StringType).setDefaultValue(findNavController().currentDestination?.label).build())
            Navigation.findNavController(rootView).navigate(R.id.action_etkinlikGuncellemeFragment_to_ilceListFragment) }

        binding.tvSemtAdi.setOnClickListener {
            etkinlikGuncellemeViewModel.getSemt(binding.bindingGuncellemeForm?.ilce).observe(requireActivity(), Observer { x ->
                observableSemt(x)
            })
        }
        binding.tvKonusmacilar.setOnClickListener {
            var a = mutableListOf<List<String>>()
            etkinlikGuncellemeViewModel.getAd(eID).observe(requireActivity(), Observer { it->
                val x = setAd(it)
                a.add(x)

            })

            etkinlikGuncellemeViewModel.getSoyad(eID).observe(requireActivity(), Observer { it->
               val y = setSoyad(it)
                a.add(y)
            })

            if(a.size==2){
                var bundle=Bundle()
                bundle.putStringArray("konusmaciAdlari",a.get(0).toTypedArray())
                bundle.putStringArray("konusmaciSoyadlari",a.get(1).toTypedArray())
                //
                bundle.putInt("etkinlikID", eID)
                Log.e("konusmaciFRAG", eID.toString())
                Navigation.findNavController(rootView).navigate(R.id.action_etkinlikGuncellemeFragment_to_konusmaciUpdateFragment,bundle)
            }


        }

        binding.tviletisimAdresi.setOnClickListener {

            var x = listOf<String>()
            etkinlikGuncellemeViewModel.getIletisim(eID).observe(requireActivity(), Observer { it->

               x = setIletisim(it)
            })

            if(x.size>0){
                var bundle=Bundle()
                bundle.putStringArray("iletisimAdresi",x.toTypedArray())

                bundle.putInt("etkinlikID", eID)
                Log.e("iletisimFRAG", eID.toString())
                Navigation.findNavController(rootView).navigate(R.id.action_etkinlikGuncellemeFragment_to_iletisimUpdateFragment,bundle)
            }


        }

        binding.guncelle.setOnClickListener{
            val etkinlik = getModel()

            if(! binding.tvAd.text.toString().equals(etkinlik.etkinlik.etkinlik_adi)) binding.bindingGuncellemeForm!!.etkinlik.etkinlik_adi =  binding.tvAd.text.toString()
            if(! binding.tvAciklama.text.toString().equals(etkinlik.etkinlik.aciklama)) binding.bindingGuncellemeForm!!.etkinlik.aciklama =  binding.tvAciklama.text.toString()
            if(! binding.tvTarih.text.toString().equals(etkinlik.etkinlik.etkinlik_tarihi)) binding.bindingGuncellemeForm!!.etkinlik.etkinlik_tarihi =  binding.tvTarih.text.toString()
            if(! binding.tvUcret.text.toString().equals(etkinlik.etkinlik.ucret)) binding.bindingGuncellemeForm!!.etkinlik.ucret = binding.tvUcret.text.toString().toInt()
            if(! binding.tvAcikAdres.text.toString().equals(etkinlik.acikAdres)) binding.bindingGuncellemeForm!!.acikAdres =  binding.tvAcikAdres.text.toString()
            if(! binding.tvilceAdi.text.toString().equals(etkinlik.ilce)) binding.bindingGuncellemeForm!!.ilce =  binding.tvilceAdi.text.toString()
            if(! binding.tvSemtAdi.text.toString().equals(etkinlik.semt)) binding.bindingGuncellemeForm!!.semt =  binding.tvSemtAdi.text.toString()
            if(! binding.tvKapsamAdi.text.toString().equals(etkinlik.etkinlikKapsami)) binding.bindingGuncellemeForm!!.etkinlikKapsami =  binding.tvKapsamAdi.text.toString()
           // if(! binding.tvToplulukAdi.text.toString().equals(etkinlik.etkinlikToplulugu)) binding.bindingGuncellemeForm!!.etkinlikToplulugu =  binding.tvToplulukAdi.text.toString()

            etkinlikGuncellemeViewModel.takeBindingData(binding.bindingGuncellemeForm!!)
            Thread.sleep(3000)
            etkinlikGuncellemeViewModel.mainUpdate(eID)
        }

    }

    private fun setIletisim(it: List<String>) :List<String> {

        return it


    }

    private fun setSoyad(it: List<String>) :List<String> {
       var y =adSoyadMapped.add(1,it)
        return adSoyadMapped.get(1)


    }
    private fun setAd(it: List<String>) :List<String>{
        var x = adSoyadMapped.add(0,it)
        return adSoyadMapped.get(0)
        //Log.e("adlar",konusmaciSoyadlari.toString())
    }


    private fun observableSemt(x:List<String>){

        var bundle: Bundle= Bundle()
        bundle.putStringArray("semtListesi",x.toTypedArray())
        bundle.putString("semtAdi",binding.bindingGuncellemeForm?.semt)
        bundle.putString("currentFragment",findNavController().currentDestination?.label.toString())

        Navigation.findNavController(rootView).navigate(R.id.action_etkinlikGuncellemeFragment_to_semtListFragment,bundle)
    }
    private fun observableEtkinlikViewModel() {
        etkinlikGuncellemeViewModel.getEtkinlik().observe(requireActivity(), Observer { it ->
            setupEtkinlikUI(it)
            setModel(it)
        })

    }
    private fun setModel(it: EtkinlikGoruntulemeResponse){
        etkinlikModel = it

    }
    private fun getModel(): EtkinlikGoruntulemeResponse{
        return etkinlikModel
    }
    private fun setupEtkinlikUI(it: EtkinlikGoruntulemeResponse) {
        binding.bindingGuncellemeForm = it //etkinlik Binding

        if(guncelKapsam!="") binding.bindingGuncellemeForm!!.etkinlikKapsami=guncelKapsam
        if(guncelTopluluk.size!=0) binding.bindingGuncellemeForm!!.etkinlikToplulugu = guncelTopluluk
        if(guncelIlce!="") binding.bindingGuncellemeForm!!.ilce = guncelIlce
        if(guncelSemt!="") binding.bindingGuncellemeForm!!.semt = guncelSemt



        }

    companion object {
        var eID=0
        var guncelAd = listOf<String>()
        var guncelSoyad = listOf<String>()
        var guncelKapsam = ""
        var guncelSemt=""
        var guncelIlce=""
        var guncelTopluluk = listOf<String>()

        var ARG_PARAM1 = "guncelAdlar"
        var ARG_PARAM2 = "guncelSoyadlar"
        var ARG_PARAM3 = "guncelKapsam"
        var ARG_PARAM4 ="guncelTopluluk"
        var ARG_PARAM5 = "guncelSemt"
        var ARG_PARAM6 ="guncelIlce"
        var ARG_PARAM7 = "etkinlikID"

        @JvmField
        var adSoyadMapped: MutableList<List<String>> = mutableListOf()
        var map: MutableList<List<String>> = mutableListOf()
        var semt:List<String> = listOf()
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
            EtkinlikGuncellemeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}