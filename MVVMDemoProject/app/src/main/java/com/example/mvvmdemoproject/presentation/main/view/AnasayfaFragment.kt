package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mvvmdemoproject.R
import kotlinx.android.synthetic.main.fragment_anasayfa.view.*

/**
 * A simple [Fragment] subclass.
 */
class AnasayfaFragment : Fragment() {
    lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_anasayfa, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rootView.kampus.setOnClickListener {
            Navigation.findNavController(rootView).navigate(R.id.action_anasayfaFragment_to_kampusSayfaFragment)
        }
    }
}
