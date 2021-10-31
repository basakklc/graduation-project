package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGuncellemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.FragmentKonusmaciUpdateBinding
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ad_soyad_update.view.*
import kotlinx.android.synthetic.main.yeni_ad_soyad.view.*
import kotlinx.android.synthetic.main.yeni_ad_soyad.view.et_konusmaci_adi
import kotlinx.android.synthetic.main.yeni_ad_soyad.view.et_konusmaci_soyadi

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KonusmaciUpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KonusmaciUpdateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    //argument
    var konusmaciAdlari : List<String> = listOf()
    var konusmaciSoyadlari : List<String> =listOf()

    //arayüzün çalışması için
    private lateinit var etkinlikGuncellemeViewModel: EtkinlikGuncellemeViewModel
    var repository = EtkinlikGuncellemeRepositoryImpl()
    var etkinlikGuncellemeUseCase = EtkinlikGuncellemeUseCase(repository)
    lateinit var binding:FragmentKonusmaciUpdateBinding
    lateinit var rootView:View

    //take data
    var guncelkonusmaciAdlari : MutableList<String> = mutableListOf()
    var guncelkonusmaciSoyadlari : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments!=null){
            val listeAd = arguments!!.getStringArray(ARG_PARAM1)!! as Array<String>
            konusmaciAdlari = listeAd.toList()

            val listeSoyad = arguments!!.getStringArray(ARG_PARAM2)!! as Array<String>
            konusmaciSoyadlari = listeSoyad.toList()

            if(arguments!!.containsKey(ARG_PARAM3)){
                eID=arguments!!.getInt(ARG_PARAM3)


            }

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_konusmaci_update, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_konusmaci_update)

        var etkinlikViewModelFactory = EtkinlikGuncellemeViewModelFactory(etkinlikGuncellemeUseCase, eID)
        etkinlikGuncellemeViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikGuncellemeViewModel::class.java)
        setupView()

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.konusmaciEkle.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("currentFragment",findNavController().currentDestination?.label.toString())
            bundle.putInt("etkinlikID", eID)
            Navigation.findNavController(rootView)
                .navigate(R.id.action_konusmaciUpdateFragment_to_konusmaciEklemeFragment,bundle)
        }

        binding.konusmaciGuncelle.setOnClickListener {
            val data = takeData()
            Log.e("data", data.toString())

            etkinlikGuncellemeViewModel.mainKonusmaciUpdate(data.get(0), data.get(1))

            var bundle: Bundle = Bundle()
            bundle.putStringArray("guncelAdlar", data.get(0).toTypedArray())
            bundle.putStringArray("guncelSoyadlar", data.get(1).toTypedArray())

            Thread.sleep(3000)
            Navigation.findNavController(rootView)
                .navigate(R.id.action_konusmaciUpdateFragment_to_etkinlikGuncellemeFragment, bundle)
        }

    }



    private fun takeData() : List<MutableList<String>>{

        for(i in 0..binding.rootLayoutKonusmaci.childCount-1 ){
            val dataAd = binding.rootLayoutKonusmaci.getChildAt(i).adi.text.toString()
            val dataSoyad = binding.rootLayoutKonusmaci.getChildAt(i).soyadi.text.toString()
            guncelkonusmaciAdlari.add(i,dataAd)
            guncelkonusmaciSoyadlari.add(i,dataSoyad)

        }

        var mapped = listOf(guncelkonusmaciAdlari,guncelkonusmaciSoyadlari)
        Log.e("take","AD : "+ guncelkonusmaciAdlari.toString() + "SOYAD:  "+ guncelkonusmaciSoyadlari.toString())
        return mapped

    }


    private fun setupView() {
        Log.e("konusmaci",konusmaciAdlari.toString())
        for(i in 0..konusmaciAdlari.size-1){
            var inflater = LayoutInflater.from(requireContext())
            var yeniKonusmaciView = inflater.inflate(R.layout.ad_soyad_update,null)

            yeniKonusmaciView.adi.setText(konusmaciSoyadlari.get(i))
            yeniKonusmaciView.soyadi.setText(konusmaciAdlari.get(i))


            binding.rootLayoutKonusmaci.addView(yeniKonusmaciView)

            yeniKonusmaciView.i_sil.setOnClickListener {
                etkinlikGuncellemeViewModel.deleteKonusmaci(yeniKonusmaciView.adi.text.toString(),yeniKonusmaciView.soyadi.text.toString())
                binding.rootLayoutKonusmaci.removeView(yeniKonusmaciView)

            }
        }
    }

    companion object {
        var eID = 0

        var ARG_PARAM1 ="konusmaciAdlari"
        var ARG_PARAM2 ="konusmaciSoyadlari"
        var ARG_PARAM3 = "etkinlikID"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KonusmaciUpdateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KonusmaciUpdateFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}