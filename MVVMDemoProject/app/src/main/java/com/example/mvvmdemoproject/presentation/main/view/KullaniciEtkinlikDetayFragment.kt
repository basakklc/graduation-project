package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGoruntulemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.FragmentEtkinlikDetayBinding
import com.example.mvvmdemoproject.databinding.FragmentKullaniciEtkinlikDetayBinding
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGoruntulemeUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGoruntulemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGoruntulemeViewModelFactory
import kotlinx.android.synthetic.main.fragment_kullanici_etkinlik_detay.view.*

/**
 * A simple [Fragment] subclass.
 */
class KullaniciEtkinlikDetayFragment : Fragment() {

    lateinit var fragmentKullaniciEtkinlikDetayBinding: FragmentKullaniciEtkinlikDetayBinding
    private lateinit var etkinlikGoruntulemeViewModel: EtkinlikGoruntulemeViewModel
    var repository= EtkinlikGoruntulemeRepositoryImpl()
    var etkinlikGoruntulemeUseCase= EtkinlikGoruntulemeUseCase(repository)
    var eID=0

//    lateinit var rootView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(it.containsKey(ARG_PARAM1)){
                eID=it.getInt(ARG_PARAM1)
                Log.e("aaaaaaaaaaa",eID.toString())

            }
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentKullaniciEtkinlikDetayBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_kullanici_etkinlik_detay,container,false)

        var etkinlikViewModelFactory= EtkinlikGoruntulemeViewModelFactory(etkinlikGoruntulemeUseCase,eID)
        etkinlikGoruntulemeViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(EtkinlikGoruntulemeViewModel::class.java)
        observableViewModel()

        return fragmentKullaniciEtkinlikDetayBinding.root


    }
    private fun observableViewModel() {
        etkinlikGoruntulemeViewModel.getEtkinlik().observe(requireActivity(), Observer {
            setupUI(it)
        })
    }

    private fun setupUI(it: EtkinlikGoruntulemeResponse?) {
        fragmentKullaniciEtkinlikDetayBinding.etkinlikGoruntuleme=it

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentKullaniciEtkinlikDetayBinding.btndn.setOnClickListener {
            Log.e("buton","oldu")
            Navigation.findNavController(fragmentKullaniciEtkinlikDetayBinding.root).navigate(R.id.action_kullaniciEtkinlikDetayFragment_to_kullaniciEtkinlikIlanlari)
        }
        fragmentKullaniciEtkinlikDetayBinding.btnEtkinlikGuncelleme.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("etkinlikID",eID)
            Navigation.findNavController(fragmentKullaniciEtkinlikDetayBinding.root).navigate(R.id.action_kullaniciEtkinlikDetayFragment_to_etkinlikGuncellemeFragment,bundle)
        }
    }

    companion object {

        var ARG_PARAM1 = "etkinlikID"
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

                }
            }
    }

}
