package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGuncellemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.FragmentIletisimUpdateBinding
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModelFactory
import kotlinx.android.synthetic.main.iletisim_update.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IletisimUpdateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IletisimUpdateFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var iletisimAdresi : List<String> = listOf()
    lateinit var rootView: View
    private lateinit var etkinlikGuncellemeViewModel: EtkinlikGuncellemeViewModel
    var repository = EtkinlikGuncellemeRepositoryImpl()
    var etkinlikGuncellemeUseCase = EtkinlikGuncellemeUseCase(repository)
    lateinit var binding:FragmentIletisimUpdateBinding

    var guncelAdresler : MutableList<String> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments!=null){
            val listeAd = arguments!!.getStringArray(ARG_PARAM1)!! as Array<String>
            iletisimAdresi = listeAd.toList()

            if(arguments!!.containsKey(ARG_PARAM2)) eID=arguments!!.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_iletisim_update, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_iletisim_update)

        var etkinlikViewModelFactory = EtkinlikGuncellemeViewModelFactory(etkinlikGuncellemeUseCase, eID)
        etkinlikGuncellemeViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikGuncellemeViewModel::class.java)
        setupView()
        return  rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.iletisimEkle.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("etkinlikID", eID)
            Navigation.findNavController(rootView).navigate(R.id.action_iletisimUpdateFragment_to_iletisimEklemeFragment,bundle)
        }

        binding.iletisimGuncelle.setOnClickListener {
            val data = takeData()

            etkinlikGuncellemeViewModel.mainIletisimAdresiUpdate(data)

            /*var bundle: Bundle = Bundle()
            bundle.putStringArray("guncelAdlar", data.get(0).toTypedArray())
            bundle.putStringArray("guncelSoyadlar", data.get(1).toTypedArray())*/

            Thread.sleep(3000)
            Navigation.findNavController(rootView).navigate(R.id.action_iletisimUpdateFragment_to_etkinlikGuncellemeFragment)
        }



    }
    private fun takeData():MutableList<String>{
        for(i in 0..binding.rootLayoutiletisim.childCount-1 ){
            val dataAdres = binding.rootLayoutiletisim.getChildAt(i).iletisim_adres.text.toString()
            guncelAdresler.add(i,dataAdres)


        }

        var adresler = guncelAdresler
        return adresler
    }
    private fun setupView() {

        for(i in 0..iletisimAdresi.size-1){
            var inflater = LayoutInflater.from(requireContext())
            var yeniIletisimView = inflater.inflate(R.layout.iletisim_update,null)
            yeniIletisimView.iletisim_adres.setText(iletisimAdresi.get(i))
            binding.rootLayoutiletisim.addView(yeniIletisimView)

            yeniIletisimView.ic_iletisim_sil.setOnClickListener {
                etkinlikGuncellemeViewModel.deleteIletisim(yeniIletisimView.iletisim_adres.text.toString())
                binding.rootLayoutiletisim.removeView(yeniIletisimView)

            }
        }
    }


    companion object {
        var eID=0
        var ARG_PARAM1="iletisimAdresi"
        var ARG_PARAM2 = "etkinlikID"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IletisimUpdateFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IletisimUpdateFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
