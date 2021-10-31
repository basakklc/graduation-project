package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.KullaniciIslemleriRepositoryImpl
import com.example.mvvmdemoproject.domain.model.KayitOlRequest
import com.example.mvvmdemoproject.domain.usecase.KullaniciIslemleriUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.UyelikViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.UyelikViewModelFactory
import kotlinx.android.synthetic.main.fragment_kayit_ol.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class KayitOlFragment : Fragment() {
    private lateinit var uyelikViewModel: UyelikViewModel
    var repository = KullaniciIslemleriRepositoryImpl()
    var kullaniciIslemleriUseCase = KullaniciIslemleriUseCase(repository)

    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_kayit_ol, container, false)
        var etkinlikViewModelFactory = UyelikViewModelFactory(kullaniciIslemleriUseCase)
        uyelikViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(UyelikViewModel::class.java)
        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rootView.btn_kayit_ol.setOnClickListener {

            val ad = rootView.et_kullanici_adi.text.toString()
            val soyad = rootView.et_kullanici_soyadi.text.toString()
            val sifre = rootView.et_sifre.text.toString()
            val sifreTekrar = rootView.et_sifre_tekrar.text.toString()
            val email = rootView.et_email.text.toString()

            var form = KayitOlRequest(ad,soyad,email,sifre,sifreTekrar)

            if(email.isNotEmpty() and ad.isNotEmpty()and soyad.isNotEmpty() and sifre.isNotEmpty() and sifreTekrar.isNotEmpty()) createKullanici(form)

        }

    }

    fun createKullanici(kayitOlRequest: KayitOlRequest){
        uyelikViewModel.kayitOl(kayitOlRequest)
        uyelikViewModel.getKayitResponse().observe(requireActivity(),Observer{
            Log.e("kkkkkk",it.toString())
            if(it.id==0) Toast.makeText(requireContext(),it.error,Toast.LENGTH_SHORT).show()
            else Navigation.findNavController(rootView).navigate(R.id.action_kayitOlFragment_to_girisYapFragment)
        })

    }

    companion object {

        @JvmStatic fun newInstance(param1: String, param2: String) =
                KayitOlFragment()
                    .apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
