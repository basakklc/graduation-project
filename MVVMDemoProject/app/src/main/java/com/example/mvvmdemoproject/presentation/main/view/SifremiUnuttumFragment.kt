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
import com.example.mvvmdemoproject.domain.model.SifreYenilemeResponse
import com.example.mvvmdemoproject.domain.usecase.KullaniciIslemleriUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.UyelikViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.UyelikViewModelFactory
import kotlinx.android.synthetic.main.fragment_sifremi_unuttum.view.*

/**
 * A simple [Fragment] subclass.
 */
class SifremiUnuttumFragment : Fragment() {

    lateinit var rootView: View


    private lateinit var uyelikViewModel: UyelikViewModel
    var repository = KullaniciIslemleriRepositoryImpl()
    var kullaniciIslemleriUseCase = KullaniciIslemleriUseCase(repository)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_sifremi_unuttum, container, false)
        var etkinlikViewModelFactory = UyelikViewModelFactory(kullaniciIslemleriUseCase)
        uyelikViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(UyelikViewModel::class.java)
        return rootView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rootView.btn_sifre_yenile.setOnClickListener {
            observableData()
        }

    }

    private fun observableData() {
        val mail = rootView.et_email.text.toString()
        val sifre = rootView.et_sifre.text.toString()
        val sifreTekrar = rootView.et_sifre_tekrar.text.toString()
        if(sifre.isNotEmpty() and sifreTekrar.isNotEmpty() and sifreTekrar.isNotEmpty() ) {

            uyelikViewModel.sifreYenileme(mail,sifre,sifreTekrar)
            uyelikViewModel.getSifreResponse().observe(requireActivity(), Observer {

                Log.e("BTN",it.toString())
                if(it.email.equals(500) and it.sifre.equals(500))   Toast.makeText(requireContext(),"Bu mail adresi kayıtlı değil",Toast.LENGTH_SHORT).show()    //Log.e("email500","adres kayıtsız")
                else if(it.sifre.equals(500) and it.email.equals(200))   Toast.makeText(requireContext(),"Girilen şifeler uyuşmuyor",Toast.LENGTH_SHORT).show() //Log.e("sifre500","uyuşmuyor")
                else if( it.sifre.equals(200) and it.email.equals(200))  Toast.makeText(requireContext(),"Başarılı",Toast.LENGTH_SHORT).show()  // Log.e("sifre200","başarılı")

            })

        }
    }

    private fun navigate() {

            Navigation.findNavController(rootView).navigate(R.id.action_sifremiUnuttumFragment_to_girisYapFragment)



    }

}
