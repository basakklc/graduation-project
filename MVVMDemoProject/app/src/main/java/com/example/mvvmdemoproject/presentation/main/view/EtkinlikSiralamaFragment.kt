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
import com.example.mvvmdemoproject.data.repository.EtkinlikListelemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.FragmentEtkinlikSiralamaBinding
import com.example.mvvmdemoproject.domain.Utils
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.SiralamaAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModelFactory

/**
 * A simple [Fragment] subclass.
 */
class EtkinlikSiralamaFragment : Fragment() {
    private lateinit var etkinlikListelemeViewModel: EtkinlikListelemeViewModel
    var repository = EtkinlikListelemeRepositoryImpl()
    var etkinlikListelemeUseCase = EtkinlikListelemeUseCase(repository)

    lateinit var fragmentEtkinlikSiralamaBinding:FragmentEtkinlikSiralamaBinding
    lateinit var arrayAdapter:SiralamaAdapter

    var etkinlikResponse = listOf<EtkinlikListelemeResponse>()
    var kategoriler: MutableList<String> = mutableListOf()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentEtkinlikSiralamaBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_etkinlik_siralama,container,false)
        // Inflate the layout for this fragment
        setupUI()

        var etkinlikViewModelFactory = EtkinlikListelemeViewModelFactory(etkinlikListelemeUseCase)
        etkinlikListelemeViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikListelemeViewModel::class.java)


        return  fragmentEtkinlikSiralamaBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fragmentEtkinlikSiralamaBinding.btnsirala.setOnClickListener {
            //Log.e("tag",arrayAdapter.secilenler().toString())
            val sec = arrayAdapter.secilenler()
            sendParameter(sec)
            val y=getResponse() //kullanılmıyor

        }

    }
    private fun sendParameter(sec: ArrayList<Int>) {

        var ucretAzalan:String? =null
        var tarihEski:String? =null
        var ucretArtan:String? =null
        var tarihYeni:String? =null
        for(i in 0..sec.size-1){
            if(sec.get(i)==0) ucretArtan = "asc"
            if(sec.get(i)==1) ucretAzalan = "desc"
            if(sec.get(i)==2) tarihYeni = "desc"
            if(sec.get(i)==3) tarihEski = "asc"
        }

        if(! ucretArtan.isNullOrEmpty() and ! tarihEski.isNullOrEmpty()) observableSıralama(ucretAzalan,tarihEski)
        else if(! ucretAzalan.isNullOrEmpty() and ! tarihYeni.isNullOrEmpty()) observableSıralama(ucretArtan,tarihYeni)
        else if(! ucretAzalan.isNullOrEmpty() and ! tarihEski.isNullOrEmpty()) observableSıralama(ucretAzalan,tarihEski)
        else if(! ucretArtan.isNullOrEmpty() and ! tarihYeni.isNullOrEmpty()) observableSıralama(ucretArtan,tarihYeni)
        else if(! ucretAzalan.isNullOrEmpty() ) observableSıralama(ucretAzalan,null)
        else if(! ucretArtan.isNullOrEmpty() ) observableSıralama(ucretArtan,null)
        else if(! tarihEski.isNullOrEmpty() ) observableSıralama(null,tarihEski)
        else if(! tarihYeni.isNullOrEmpty() ) observableSıralama(null,tarihYeni)
    }

    private fun observableSıralama(ucret:String?,tarih:String?) {
        etkinlikListelemeViewModel.getSiraliResponse(ucret, tarih).observe(requireActivity(), Observer { it ->

           setResponse(it)
           // Log.e("deneme",it.toString())
        })
    }

    private fun setResponse(it: List<EtkinlikListelemeResponse>?) {
        etkinlikResponse = it!!
        val args = Bundle()
        val etkinlikJsonString: String = Utils.getGsonParser().toJson(etkinlikResponse)
        args.putString("siraliResponse", etkinlikJsonString)

        Navigation.findNavController(fragmentEtkinlikSiralamaBinding.root).navigate(R.id.action_etkinlikSiralamaFragment_to_etkinlikListelemeFragment,args)
    }

    private fun getResponse():List<EtkinlikListelemeResponse>?{
        Log.e("get",etkinlikResponse.toString())
        return etkinlikResponse
    }


    private fun setupUI() {
        dataEntry()
        arrayAdapter=SiralamaAdapter(requireContext(),R.layout.siralamaitem_row,kategoriler)
        fragmentEtkinlikSiralamaBinding.lvSiralama.adapter=arrayAdapter



    }
    private fun dataEntry() {
        var res = resources.getStringArray(R.array.siralama) as Array<String>
        for (i in 0..res.size-1){
            kategoriler.add(
                res.get(i)
            )
        }


    }


}
