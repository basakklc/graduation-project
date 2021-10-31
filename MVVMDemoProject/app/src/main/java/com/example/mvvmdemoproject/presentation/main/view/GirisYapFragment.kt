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
import com.example.mvvmdemoproject.domain.usecase.KullaniciIslemleriUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.UyelikViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.UyelikViewModelFactory
import kotlinx.android.synthetic.main.fragment_giris_yap.view.*


class GirisYapFragment : Fragment() {


    lateinit var rootView: View

    private lateinit var uyelikViewModel: UyelikViewModel
    var repository = KullaniciIslemleriRepositoryImpl()
    var kullaniciIslemleriUseCase = KullaniciIslemleriUseCase(repository)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_giris_yap, container, false)
        var etkinlikViewModelFactory = UyelikViewModelFactory(kullaniciIslemleriUseCase)
        uyelikViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(UyelikViewModel::class.java)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rootView.btn_giris_yap.setOnClickListener {

            uyelikViewModel.giris(rootView.et_email.text.toString(),rootView.et_sifre.text.toString())
            uyelikViewModel.getID().observe(requireActivity(), Observer {
                if(it!=0) {
                    Navigation.findNavController(rootView).navigate(R.id.action_girisYapFragment_to_anasayfaFragment)
                }
                else {
                    Toast.makeText(requireContext(),"Yanlış giriş..",Toast.LENGTH_LONG).show()
                }
            })


        }
        rootView.btn_sifremi_unuttum.setOnClickListener {
            Navigation.findNavController(rootView).navigate(R.id.action_girisYapFragment_to_sifremiUnuttumFragment)
        }


        rootView.btn_kayit_ol.setOnClickListener {

            Navigation.findNavController(rootView).navigate(R.id.action_girisYapFragment_to_kayitOlFragment)
        }
    }

}
