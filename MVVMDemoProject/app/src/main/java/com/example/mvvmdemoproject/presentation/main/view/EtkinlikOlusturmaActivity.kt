package com.example.mvvmdemoproject.presentation.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikListelemeRepositoryImpl
import com.example.mvvmdemoproject.data.repository.EtkinlikOlusturmaRepositoryImpl
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.domain.usecase.EtkinlikOlusturmaUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.EtkinlikAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModelFactory
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikOlusturmaViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikOlusturmaViewModelFactory


class EtkinlikOlusturmaActivity : AppCompatActivity() {

    private lateinit var etkinlikOlusturmaViewModel: EtkinlikOlusturmaViewModel

    var repository= EtkinlikOlusturmaRepositoryImpl()
    var etkinlikOlusturmaUseCase= EtkinlikOlusturmaUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlik_olusturma)


        var etkinlikViewModelFactory= EtkinlikOlusturmaViewModelFactory(etkinlikOlusturmaUseCase)
        etkinlikOlusturmaViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(
            EtkinlikOlusturmaViewModel::class.java)

        setupUI()
        observableViewModel()

    }

    private fun setupUI() {


    }

    private fun observableViewModel() {
        etkinlikOlusturmaViewModel.getEtkinlikler().observe(this, Observer { //it -> renderList(it)
            //if (it!=null) Toast.makeText(applicationContext,"heyyyy",Toast.LENGTH_LONG).show()
        })
    }

    private fun renderList(it: List<EtkinlikListelemeResponse>) {

    }
}
