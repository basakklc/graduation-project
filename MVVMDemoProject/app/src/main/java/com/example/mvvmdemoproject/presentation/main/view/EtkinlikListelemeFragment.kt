package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikListelemeRepositoryImpl
import com.example.mvvmdemoproject.domain.Utils
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.EtkinlikAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModelFactory
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_etkinlik_listeleme.view.*
import java.lang.reflect.Type
import kotlin.reflect.typeOf


/**
 * A simple [Fragment] subclass.
 */
class EtkinlikListelemeFragment : Fragment() {
    private lateinit var etkinlikListelemeViewModel: EtkinlikListelemeViewModel
    private lateinit var adapter: EtkinlikAdapter
    private lateinit var rcListener:EtkinlikAdapter.RecyclerviewClickListener
    lateinit var recyclerView: RecyclerView
    lateinit var rootView: View
    var repository= EtkinlikListelemeRepositoryImpl()
    var etkinlikListelemeUseCase= EtkinlikListelemeUseCase(repository)


    var filtreliEtkinlik :MutableList< List<EtkinlikListelemeResponse>> = mutableListOf()
    var siraliEtkinlik:List<EtkinlikListelemeResponse> = listOf()

    val listOfEtkinlikType: Type =
        object : TypeToken<List<EtkinlikListelemeResponse?>?>() {}.type

    val mutablelistOfEtkinlikType: Type =
        object : TypeToken<MutableList<List<EtkinlikListelemeResponse?>?>>() {}.type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(it.containsKey(ARG_PARAM3)) {
                val liste3 = it.getString(ARG_PARAM3)
                siraliEtkinlik =
                    Utils.getGsonParser().fromJson(liste3,listOfEtkinlikType)
            }
            if(it.containsKey(ARG_PARAM4)) {
                val liste4 = it.getString(ARG_PARAM4)
                filtreliEtkinlik =
                    Utils.getGsonParser().fromJson(liste4,mutablelistOfEtkinlikType)

                Log.e("filtreli",filtreliEtkinlik.toString())


            }





        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView= inflater.inflate(R.layout.fragment_etkinlik_listeleme, container, false)
        recyclerView=rootView.findViewById(R.id.rcylr_etkinlikler)


        var etkinlikViewModelFactory= EtkinlikListelemeViewModelFactory(etkinlikListelemeUseCase)
        etkinlikListelemeViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(EtkinlikListelemeViewModel::class.java)

       setupUI()
       observableViewModel()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        rootView.btn_filtreleme.setOnClickListener {
            Navigation.findNavController(rootView).navigate(R.id.action_etkinlikListelemeFragment_to_etkinlikFiltrelemeFragment)
        }

        rootView.btn_siralama.setOnClickListener{
            Navigation.findNavController(rootView).navigate(R.id.action_etkinlikListelemeFragment_to_etkinlikSiralamaFragment)
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
        rcListener= object : EtkinlikAdapter.RecyclerviewClickListener {
            override fun onClick(v: View, position: Int) {

                var etkinlikResponse = adapter.etkinlikler.get(position)

                etkinlikListelemeViewModel.etkinlikId(etkinlikResponse.etkinlikAdi,etkinlikResponse.ucret,etkinlikResponse.etkinlikTarihi,etkinlikResponse.aciklama)

                var bundle:Bundle=Bundle()
                etkinlikListelemeViewModel.getID().observe(requireActivity(), Observer { it ->
                    bundle.putInt("etkinlikID",it )
                    Navigation.findNavController(rootView).navigate(R.id.action_etkinlikListelemeFragment_to_etkinlikDetayFragment,bundle)
                })



            }

        }
    }


    private fun observableViewModel() {
        etkinlikListelemeViewModel.getEtkinlikler().observe(requireActivity(), Observer { it ->
            renderList(it)

        })
    }

    private fun renderList(it: List<EtkinlikListelemeResponse>) {
        if(! siraliEtkinlik.isEmpty() or !filtreliEtkinlik.isEmpty())
        {
            if(! siraliEtkinlik.isEmpty()){
                adapter.loadData(siraliEtkinlik as MutableList<EtkinlikListelemeResponse>)
                Log.e("testSıra",siraliEtkinlik.toString())
                adapter.notifyDataSetChanged()}
            else if(! filtreliEtkinlik.isEmpty()){
                val y = mutableListOf<EtkinlikListelemeResponse>()
                for (i in 0..1){
                    for (j in 0..filtreliEtkinlik.get(i).size-1)
                        y.add(filtreliEtkinlik.get(i).get(j))
                }
                Log.e("test",y.toString())
                adapter.loadData(y.toList())
                adapter.notifyDataSetChanged()
            }
        }
        else{

            adapter.loadData(it)
            adapter.notifyDataSetChanged()
        }


    }



    companion object {

        var ARG_PARAM1 = "kapsamaGore"
        var ARG_PARAM2 = "toplulugaGore"
        var ARG_PARAM3 = "siraliResponse"
        var ARG_PARAM4 = "filtreliResponse"

        @JvmField
        var adSoyadMapped: MutableList<List<String>> = mutableListOf()
        var map: MutableList<List<String>> = mutableListOf()
        var semt:List<String> = listOf()
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
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
