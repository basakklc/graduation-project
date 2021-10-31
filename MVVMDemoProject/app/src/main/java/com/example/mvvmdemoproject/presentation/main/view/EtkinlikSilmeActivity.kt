package com.example.mvvmdemoproject.presentation.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikOlusturmaRepositoryImpl
import com.example.mvvmdemoproject.data.repository.EtkinlikSilmeRepositoryImpl
import com.example.mvvmdemoproject.domain.usecase.EtkinlikOlusturmaUseCase
import com.example.mvvmdemoproject.domain.usecase.EtkinlikSilmeUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikOlusturmaViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikOlusturmaViewModelFactory
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikSilmeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikSilmeViewModelFactory

class EtkinlikSilmeActivity : AppCompatActivity() {

    private lateinit var etkinlikSilmeViewModel: EtkinlikSilmeViewModel

    var repository= EtkinlikSilmeRepositoryImpl()
    var etkinlikSilmeUseCase= EtkinlikSilmeUseCase(repository)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlik_silme)


        var etkinlikViewModelFactory= EtkinlikSilmeViewModelFactory(etkinlikSilmeUseCase,171)
        etkinlikSilmeViewModel= ViewModelProvider(this,etkinlikViewModelFactory).get(
            EtkinlikSilmeViewModel::class.java)

        observableViewModel()
    }

    private fun observableViewModel() {
        etkinlikSilmeViewModel.getEtkinlik().observe(this, Observer { it ->

            //if (it!=null) Toast.makeText(applicationContext,"heyyyy",Toast.LENGTH_LONG).show()
        })
    }
}
