package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikOlusturmaRepositoryImpl
import com.example.mvvmdemoproject.databinding.FragmentKullaniciEtkinlikEklemeBinding
import com.example.mvvmdemoproject.databinding.KullaniciEtkinlikFormuBinding
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikOlusturmaRequest
import com.example.mvvmdemoproject.domain.usecase.EtkinlikOlusturmaUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikOlusturmaViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikOlusturmaViewModelFactory
import kotlinx.android.synthetic.main.etkinlik_raw_item.view.*
import kotlinx.android.synthetic.main.kullanici_etkinlik_formu.view.*

class KullaniciEtkinlikOlusturmaFragment : Fragment() {

    lateinit var rootView: View
    private lateinit var etkinlikOlusturmaViewModel: EtkinlikOlusturmaViewModel
    var repository = EtkinlikOlusturmaRepositoryImpl()
    var etkinlikOlusturmaUseCase = EtkinlikOlusturmaUseCase(repository)
    lateinit var binding: KullaniciEtkinlikFormuBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_kullanici_etkinlik_olusturma, container, false)
       // binding = DataBindingUtil.setContentView(requireActivity(),R.layout.kullanici_etkinlik_formu)

        var etkinlikViewModelFactory = EtkinlikOlusturmaViewModelFactory(etkinlikOlusturmaUseCase)
        etkinlikOlusturmaViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikOlusturmaViewModel::class.java)

        setupUI()
        observableViewModel()

        return rootView.root
    }
    private fun setupUI() {


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rootView.btn_ekle.setOnClickListener {
            Toast.makeText(requireContext(),"denemeeee",Toast.LENGTH_LONG).show()

        }
    }
    private fun observableViewModel() {
        etkinlikOlusturmaViewModel.getEtkinlikler().observe(requireActivity(), Observer { //it -> renderList(it)
            //if (it!=null) Toast.makeText(applicationContext,"heyyyy",Toast.LENGTH_LONG).show()
        })
    }

    private fun renderList(it: List<EtkinlikListelemeResponse>) {

    }


}
