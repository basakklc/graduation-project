package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikListelemeRepositoryImpl
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.EtkinlikAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModelFactory

class EtkinlikListelemeActivity : AppCompatActivity() {

    private lateinit var etkinlikListelemeViewModel:EtkinlikListelemeViewModel
    private lateinit var adapter: EtkinlikAdapter
    lateinit var recyclerView: RecyclerView
    var repository=EtkinlikListelemeRepositoryImpl()
    var etkinlikListelemeUseCase=EtkinlikListelemeUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlik_listeleme)

        recyclerView=findViewById(R.id.rcylr_etkinlikler)

        var etkinlikViewModelFactory= EtkinlikListelemeViewModelFactory(etkinlikListelemeUseCase)
        etkinlikListelemeViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(EtkinlikListelemeViewModel::class.java)

        setupUI()
        observableViewModel()

    }

    private fun setupUI() {
        //recyclerView.layoutManager=LinearLayoutManager(this)

        var linearLayoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager=linearLayoutManager

        //adapter= EtkinlikAdapter(arrayListOf())

        recyclerView.adapter=adapter

    }

    private fun observableViewModel() {
        etkinlikListelemeViewModel.getEtkinlikler().observe(this, Observer { it ->
            renderList(it)
            //if (it!=null) Toast.makeText(applicationContext,"heyyyy",Toast.LENGTH_LONG).show()
        })
    }

    private fun renderList(it: List<EtkinlikListelemeResponse>) {
        adapter.loadData(it)
        adapter.notifyDataSetChanged()
    }

}
