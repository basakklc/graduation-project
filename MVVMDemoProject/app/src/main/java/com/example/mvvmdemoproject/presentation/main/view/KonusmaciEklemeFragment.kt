package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGuncellemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.FragmentKonusmaciEklemeBinding
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModelFactory
import kotlinx.android.synthetic.main.ad_soyad_ekleme.view.*
import kotlinx.android.synthetic.main.fragment_konusmaci_ekleme.view.*
import kotlinx.android.synthetic.main.yeni_ad_soyad.view.*
import kotlinx.android.synthetic.main.yeni_ad_soyad.view.et_konusmaci_adi
import kotlinx.android.synthetic.main.yeni_ad_soyad.view.et_konusmaci_soyadi
import kotlinx.android.synthetic.main.yeni_ad_soyad.view.ic_konusmaci_sil

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KonusmaciEklemeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KonusmaciEklemeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rootLayout:View
    var currentFragment = ""

    private lateinit var etkinlikGuncellemeViewModel: EtkinlikGuncellemeViewModel
    var repository = EtkinlikGuncellemeRepositoryImpl()
    var etkinlikGuncellemeUseCase = EtkinlikGuncellemeUseCase(repository)
    lateinit var binding:FragmentKonusmaciEklemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentFragment = arguments!!.getString(ARG_PARAM1)!!

            if(it.containsKey(ARG_PARAM2)){
                eID=it.getInt(ARG_PARAM2)


            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootLayout =  inflater.inflate(R.layout.fragment_konusmaci_ekleme, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_konusmaci_ekleme)

        var etkinlikViewModelFactory = EtkinlikGuncellemeViewModelFactory(etkinlikGuncellemeUseCase, eID)
        etkinlikGuncellemeViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikGuncellemeViewModel::class.java)


        binding.iKonusmaciArti.setOnClickListener {

            if (!binding.konusmaciAdi.text.toString().isNullOrEmpty() and !binding.konusmaciSoyadi.text.toString().isNullOrEmpty()) {

                var yeniAdSoyadView = inflater.inflate(R.layout.ad_soyad_ekleme, null)
                yeniAdSoyadView.adi.setText(binding.konusmaciAdi.text)
                yeniAdSoyadView.soyadi.setText(binding.konusmaciSoyadi.text)
                yeniAdSoyadView.btn_sil.setOnClickListener {
                    binding.rootLayoutKonusmaci.removeView(yeniAdSoyadView)
                    if(currentFragment.equals("fragment_kullanici_etkinlik_ekleme")){

                    }
                    else{
                        etkinlikGuncellemeViewModel.deleteKonusmaci(yeniAdSoyadView.adi.text.toString(),yeniAdSoyadView.soyadi.text.toString())
                    }

                }
                binding.rootLayoutKonusmaci.addView(yeniAdSoyadView)

                if(currentFragment.equals("fragment_kullanici_etkinlik_ekleme")){

                }


                else{
                    etkinlikGuncellemeViewModel.createKonusmaci(binding.konusmaciAdi.text.toString(),binding.konusmaciSoyadi.text.toString())
                }



            }
        }

        return rootLayout
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.tamamla.setOnClickListener {
            if(currentFragment.equals("fragment_kullanici_etkinlik_ekleme") ){

                val adList= mutableListOf<String>()
                val soyadList= mutableListOf<String>()
                for (i in 0..binding.rootLayoutKonusmaci.childCount-1){

                        val dataAd=binding.rootLayoutKonusmaci.getChildAt(i).adi.text.toString()
                        val dataSoyad=binding.rootLayoutKonusmaci.getChildAt(i).soyadi.text.toString()

                        adList.add(i,dataAd)
                        soyadList.add(i,dataSoyad)
                }

                var bundle=Bundle()
                bundle.putStringArray("konusmaciAdlari",adList.toTypedArray())
                bundle.putStringArray("konusmaciSoyadlari",soyadList.toTypedArray())
                Log.e("adList","AD : " +adList.toString()+"SOYAD : "+soyadList.toString())

                Navigation.findNavController(rootLayout).navigate(R.id.action_konusmaciEklemeFragment_to_kullaniciEtkinlikEkleme,bundle) ///ad,/soyad
            }
            else{
                Navigation.findNavController(rootLayout).navigate(R.id.action_konusmaciEklemeFragment_to_etkinlikGuncellemeFragment)
            }

        }
    }



    companion object {
        var eID=0

        var ARG_PARAM1 ="currentFragment"
        var ARG_PARAM2 = "etkinlikID"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KonusmaciEklemeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KonusmaciEklemeFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
