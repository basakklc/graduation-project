package com.example.mvvmdemoproject.presentation.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGoruntulemeRepositoryImpl
import com.example.mvvmdemoproject.data.repository.EtkinlikListelemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.ActivityEtkinlikGoruntulemeBinding
import com.example.mvvmdemoproject.domain.model.EtkinlikGoruntulemeResponse
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGoruntulemeUseCase
import com.example.mvvmdemoproject.domain.usecase.EtkinlikListelemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.EtkinlikAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGoruntulemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGoruntulemeViewModelFactory
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikListelemeViewModelFactory

class EtkinlikGoruntulemeActivity : AppCompatActivity() {

    private lateinit var etkinlikGoruntulemeViewModel: EtkinlikGoruntulemeViewModel
    var repository= EtkinlikGoruntulemeRepositoryImpl()
    var etkinlikGoruntulemeUseCase= EtkinlikGoruntulemeUseCase(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlik_goruntuleme)


        var etkinlikViewModelFactory= EtkinlikGoruntulemeViewModelFactory(etkinlikGoruntulemeUseCase,226)
        etkinlikGoruntulemeViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(EtkinlikGoruntulemeViewModel::class.java)

        observableViewModel()

    }

    private fun setupUI(it:EtkinlikGoruntulemeResponse) {
        var binding: ActivityEtkinlikGoruntulemeBinding =
            DataBindingUtil.setContentView(this@EtkinlikGoruntulemeActivity,R.layout.activity_etkinlik_goruntuleme)
        binding.etkinlikGoruntuleme=it
    }

    private fun observableViewModel() {
        etkinlikGoruntulemeViewModel.getEtkinlik().observe(this, Observer { it ->
            setupUI(it)
            //if (it!=null) Toast.makeText(applicationContext,"heyyyy",Toast.LENGTH_LONG).show()
        })
    }

}
