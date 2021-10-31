package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mvvmdemoproject.R
import kotlinx.android.synthetic.main.fragment_profil_ekrani.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfilEkraniFragment : Fragment() {

    lateinit var rootView: View

    /**
     * menu ACTVİTY manin ??
     * kullanıcı id si safe args ile alınmalı!! -- nav menu fragment içinden yönlendirme ve buradan data .
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profil_ekrani, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rootView.ic_etkinlikIlani.setOnClickListener {

            var bundle = Bundle()
            bundle.putInt("kullaniciId",4) // = safe args ile alınan id değişkeni aktarılacak
            Navigation.findNavController(rootView).navigate(R.id.action_profilEkraniFragment_to_kullaniciEtkinlikIlanlar,bundle)
        }
    }

}
