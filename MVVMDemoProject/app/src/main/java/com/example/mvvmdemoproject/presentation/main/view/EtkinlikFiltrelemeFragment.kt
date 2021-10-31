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
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikListelemeRepositoryImpl
import com.example.mvvmdemoproject.domain.Utils
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.FilterAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModelFactory
import kotlinx.android.synthetic.main.fragment_etkinlik_filtreleme.view.*

/**
 * A simple [Fragment] subclass.
 */
class EtkinlikFiltrelemeFragment : Fragment() {
    companion object {

        var listChild: HashMap<String, ArrayList<String>?> = HashMap()
        var filtrelemeKategorileri = listOf<String>("KAPSAMLAR", "TOPLULUKLAR")
        var listGroup: ArrayList<String> = arrayListOf(filtrelemeKategorileri.get(0), filtrelemeKategorileri.get(1))
        var toplulukList: ArrayList<String>? = ArrayList()
        var kapsamList: ArrayList<String>? = ArrayList()

        private var kapsamaGoreResponse : List<EtkinlikListelemeResponse> = listOf()
        private var toplulugaGoreResponse : List<EtkinlikListelemeResponse> = listOf()
        private var resp :MutableList< List<EtkinlikListelemeResponse>> = mutableListOf()
    }

    lateinit var topluluklar:ArrayList<String>
    lateinit var kapsamlar:ArrayList<String>

    lateinit var filterAdapter: FilterAdapter
    private lateinit var etkinlikListelemeViewModel: EtkinlikListelemeViewModel
    var repository= EtkinlikListelemeRepositoryImpl()
    var etkinlikListelemeUseCase= EtkinlikListelemeUseCase(repository)


    lateinit var rootView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater.inflate(R.layout.fragment_etkinlik_filtreleme, container, false)

        var etkinlikViewModelFactory= EtkinlikListelemeViewModelFactory(etkinlikListelemeUseCase)
        etkinlikListelemeViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(EtkinlikListelemeViewModel::class.java)
        observableViewModel()

        return rootView
    }





    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rootView.uygula.setOnClickListener {
            observableFilterModel()
//if ->  resp.size>0
                navigate(resp)
                Log.e("tagbtn", resp.toString())


            resp = mutableListOf()
        }
    }


    private fun navigate(respo:MutableList<List<EtkinlikListelemeResponse>>) {
        val args = Bundle()
        val etkinlikResponseJsonString = Utils.getGsonParser().toJson(respo)
        args.putString("filtreliResponse",etkinlikResponseJsonString)
        Navigation.findNavController(rootView).navigate(R.id.action_etkinlikFiltrelemeFragment_to_etkinlikListelemeFragment,args)
    }



    //responseların alınması
    private fun observableFilterModel() {


            etkinlikListelemeViewModel.getToplulukResponse(topluluklar.joinToString (",")).observe(requireActivity(), Observer {
                setToplulukResponse(it)
            })


            etkinlikListelemeViewModel.getKapsamResponse(kapsamlar.joinToString (",")).observe(requireActivity(), Observer {
                setKapsamResponse(it)
            })

    }

    //filtreleme sonucunun aktarımı
    fun setKapsamResponse(etkinlikler: List<EtkinlikListelemeResponse>) {

        resp.add(etkinlikler)
        kapsamaGoreResponse = etkinlikler

    }
    fun setToplulukResponse(etkinlikler: List<EtkinlikListelemeResponse>){
        resp.add(etkinlikler)
        toplulugaGoreResponse =etkinlikler

    }

    //liste öğelerinin alınması
    private fun observableViewModel() {
        etkinlikListelemeViewModel.getKapsamlar().observe(requireActivity(), Observer {
            // Log.e("deneme+k",it.toString())
            kapsamAktarma(it)
        })

        etkinlikListelemeViewModel.getTopluluklar().observe(requireActivity(), Observer {
            //  Log.e("deneme+t",it.toString())
            toplulukAktarma(it)
        })
        setupExpListView()

    }

    // grup ve childlara atamalar.
    private fun toplulukAktarma(topluluklar: List<String>?) {

        toplulukList = topluluklar as ArrayList<String>?
        listChild.put(listGroup.get(1), toplulukList)
        //setTopluluk(listChild)

    }
    private fun kapsamAktarma(kapsamlar: List<String>?) {

        kapsamList = kapsamlar as ArrayList<String>?
        listChild.put(listGroup.get(0), kapsamList)
       // setKapsam(listChild)
        //Log.e("deneme+k", listGroup.toString()+" +++ "+ listChild.toString())

    }

    //atama yapıldıktan sonra listenin oluşturulması
    private fun setupExpListView() {

        filterAdapter = FilterAdapter(listGroup, listChild)
        rootView.exp_list_view.setAdapter(filterAdapter)
        kapsamlar = filterAdapter.kapsamlar()
        topluluklar = filterAdapter.topluluklar()
    }

}



/* private fun setKapsam(listChild: HashMap<String, ArrayList<String>?>) {
        Log.e("set+k", " +++ "+ listChild.toString())
    }

    private fun setTopluluk(listChild: HashMap<String, ArrayList<String>?>){
        Log.e("set+k", " +++ "+ listChild.toString())
    }*/
/*
    fun getKapsamResponse():List<EtkinlikListelemeResponse>{
        return kapsamaGoreResponse
    }
    fun getToplulukResponse():List<EtkinlikListelemeResponse>{
        return toplulugaGoreResponse

    }
 */