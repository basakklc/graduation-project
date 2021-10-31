package com.example.mvvmdemoproject.presentation.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikFiltrelemeRepositoryImpl
import com.example.mvvmdemoproject.data.repository.EtkinlikListelemeRepositoryImpl
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikFiltrelemeUseCase
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.EtkinlikAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikFiltrelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikFiltrelemeViewModelFactory
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModelFactory
import kotlinx.android.synthetic.main.activity_etkinlik_filtreleme.*

class EtkinlikFiltrelemeActivity : AppCompatActivity() {

    private lateinit var etkinlikFiltrelemeViewModel: EtkinlikFiltrelemeViewModel
    private lateinit var adapter: EtkinlikAdapter
    lateinit var recyclerView: RecyclerView
    var repository= EtkinlikFiltrelemeRepositoryImpl()
    var etkinlikFiltrelemeUseCase= EtkinlikFiltrelemeUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlik_filtreleme)


        //recyclerView=findViewById(R.id.rcylr_etkinlikler)

        var etkinlikViewModelFactory= EtkinlikFiltrelemeViewModelFactory(etkinlikFiltrelemeUseCase)
        etkinlikFiltrelemeViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(
            EtkinlikFiltrelemeViewModel::class.java)

        //setupUI()
        observableViewModel()

    }

   /* private fun setupUI() {
        //recyclerView.layoutManager=LinearLayoutManager(this)

        var linearLayoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager

        adapter= EtkinlikAdapter(arrayListOf())

        recyclerView.adapter=adapter

    }*/

    private fun observableViewModel() {
        etkinlikFiltrelemeViewModel.getEtkinlik().observe(this, Observer { it ->
           text.text = it.toString()
            //renderList(it)
            //if (it!=null) Toast.makeText(applicationContext,"heyyyy",Toast.LENGTH_LONG).show()
        })
    }

    private fun renderList(it: List<EtkinlikListelemeResponse>) {
        adapter.loadData(it)
        adapter.notifyDataSetChanged()

    }
}
