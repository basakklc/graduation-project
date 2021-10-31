package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikListelemeRepositoryImpl
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.EtkinlikAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModelFactory
import kotlinx.android.synthetic.main.fragment_kullanici_etkinlik_ilanlari.view.*

/**
 * A simple [Fragment] subclass.
 */
class KullaniciEtkinlikIlanlari : Fragment() {

    private lateinit var etkinlikListelemeViewModel: EtkinlikListelemeViewModel
    private lateinit var adapter: EtkinlikAdapter
    private lateinit var rcListener: EtkinlikAdapter.RecyclerviewClickListener
    lateinit var recyclerView: RecyclerView
    lateinit var rootView: View
    var repository= EtkinlikListelemeRepositoryImpl()
    var etkinlikListelemeUseCase= EtkinlikListelemeUseCase(repository)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(it.containsKey(ARG_PARAM1)){
                kID=it.getInt(ARG_PARAM1)
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView= inflater.inflate(R.layout.fragment_kullanici_etkinlik_ilanlari, container, false)
        recyclerView=rootView.findViewById(R.id.rcylr_etkinlikler)

        var etkinlikViewModelFactory= EtkinlikListelemeViewModelFactory(etkinlikListelemeUseCase)
        etkinlikListelemeViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(EtkinlikListelemeViewModel::class.java)

        setupUI()
        observableViewModel()


        return rootView
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rootView.btn_etkinlikDetay.setOnClickListener {
            var bundle=Bundle()
            bundle.putInt("etkinlikID",269)
            Navigation.findNavController(rootView)
                .navigate(R.id.action_kullaniciEtkinlikIlanlari_to_kullaniciEtkinlikDetayFragment,bundle)
        }
        rootView.btn_etkinlikOlusturma.setOnClickListener {
            var bundle = Bundle()
            bundle.putInt("kullaniciId", kID)
            Log.e("Kilanlar", kID.toString())
            Navigation.findNavController(rootView).navigate(
                R.id.action_kullaniciEtkinlikIlanlari_to_kullaniciEtkinlikEkleme,
                bundle
            )
        }
    }

    private fun setupUI() {
        //recyclerView.layoutManager=LinearLayoutManager(this)

        setOnClickListener()

        var linearLayoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager

        adapter= EtkinlikAdapter(arrayListOf(),rcListener)

        recyclerView.adapter=adapter

    }
    private fun setOnClickListener() {
        rcListener = object : EtkinlikAdapter.RecyclerviewClickListener {
            override fun onClick(v: View, position: Int) {

                var etkinlikResponse = adapter.etkinlikler.get(position)

                etkinlikListelemeViewModel.etkinlikId(
                    etkinlikResponse.etkinlikAdi,
                    etkinlikResponse.ucret,
                    etkinlikResponse.etkinlikTarihi,
                    etkinlikResponse.aciklama
                )

                var bundle: Bundle = Bundle()
                etkinlikListelemeViewModel.getID().observe(requireActivity(), Observer { it ->
                  Log.e("etkinlikID", it.toString())
                    bundle.putInt("etkinlikID", it)
                    Navigation.findNavController(rootView).navigate(
                        R.id.action_kullaniciEtkinlikIlanlari_to_kullaniciEtkinlikDetayFragment,
                        bundle

                    )
                })


            }

        }


    }


    private fun observableViewModel() {
        etkinlikListelemeViewModel.fetchKullaniciEtkinlikleri()
        Log.e("ilanK", kID.toString())
        etkinlikListelemeViewModel.kullanicininEtkinlikleri().observe(requireActivity(), Observer { it ->

            Log.e("ilanK","it.toString()")
            renderList(it)

        })
    }

   private fun renderList(it: List<EtkinlikListelemeResponse>) {
       adapter.loadData(it)
       adapter.notifyDataSetChanged()
   }





        companion object {

        var kID = 0

        var ARG_PARAM1 = "kullaniciId"
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
