package com.example.mvvmdemoproject.presentation.main.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewConfiguration
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGuncellemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.EtkinlikGuncellemeFormuBinding
import com.example.mvvmdemoproject.domain.model.*
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.IlceAdapter
import com.example.mvvmdemoproject.presentation.main.adapter.KapsamAdapter
import com.example.mvvmdemoproject.presentation.main.adapter.SemtAdapter
import com.example.mvvmdemoproject.presentation.main.adapter.ToplulukAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModelFactory
import kotlinx.android.synthetic.main.etkinlik_guncelleme_formu.*

class EtkinlikGuncellemeActivity : AppCompatActivity() {

    //viewmodel
    private lateinit var etkinlikGuncellemeViewModel: EtkinlikGuncellemeViewModel
    var repository = EtkinlikGuncellemeRepositoryImpl()
    var etkinlikGuncellemeUseCase = EtkinlikGuncellemeUseCase(repository)
    lateinit var binding: EtkinlikGuncellemeFormuBinding

    //adaptere bağlanacak listeler
    var kapsamlar: MutableList<KapsamListItemModel> = mutableListOf()
    var topluluklar: MutableList<ToplulukListItemModel> = mutableListOf()
    var ilceler: MutableList<IlceListItemModel> = mutableListOf()
    var semtler: MutableList<SemtListItemModel> = mutableListOf()

    //arayüz öğeleri
    lateinit var button: Button
    lateinit var buttonYayimla: Button
    lateinit var kapsamListView: ListView
    lateinit var toplulukListView: ListView
    lateinit var semtListView: ListView
    lateinit var ilceListView: ListView


    var gecmisIletisimAdresi = listOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_etkinlik_guncelleme)

        binding = DataBindingUtil.setContentView(this@EtkinlikGuncellemeActivity, R.layout.etkinlik_guncelleme_formu)
        var etkinlikViewModelFactory = EtkinlikGuncellemeViewModelFactory(etkinlikGuncellemeUseCase, 226)
        etkinlikGuncellemeViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikGuncellemeViewModel::class.java)



        listViewDataEntry()   // listview için datalar oluşturulur.
        observableViewModel() // verilen id ye view model üzerinden ulaşılır. forma öğelerine uygun atamalar gerçekleştirilir.


    }

// verilen id ye view model üzerinden ulaşılır. forma öğelerine uygun atamalar gerçekleştirilir.
    private fun observableViewModel() {
        etkinlikGuncellemeViewModel.getEtkinlik().observe(this, Observer { it ->
            setupUI(it)
        })

    }

        private fun setupUI(it: EtkinlikGoruntulemeResponse) {

        binding.bindingGuncellemeForm = it

        setupKapsamListView(it.etkinlikKapsami)
        setupToplulukListView(it.etkinlikToplulugu)
        setupIlceListView(it.ilce)
        setupSemtListView(it.semt)




        //gecmisIletisimAdresi = binding.bindingGuncellemeForm.iletisimAdresi
        gecmisIletisimAdresi = listOf("beyza", "bsk")

        button = findViewById(R.id.gosteTr)


        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                //liste.text = adapters.secilenler().toString()
                //lis.text = adapters.secilenler().toString()
            }

        })
        buttonYayimla = findViewById(R.id.gondermek)

        buttonYayimla.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                etkinlikGuncellemeViewModel.takeBindingData(binding.bindingGuncellemeForm as EtkinlikGoruntulemeResponse)
                etkinlikGuncellemeViewModel.mainUpdate(226)
                // etkinlikGuncellemeViewModel.mainAdresUpdate()

                //etkinlikGuncellemeViewModel.mainEtkinlikKapsamiUpdate()
                ////etkinlikGuncellemeViewModel.mainIletisimAdresiUpdate(gecmisIletisimAdresi)
                //etkinlikGuncellemeViewModel.mainKonusmaciUpdate
                //etkinlikGuncellemeViewModel.mainAdresUpdate()

            }
            // etkinlikGuncellemeViewModel.mainUpdate()

        })
    } //forma öğelerine uygun atamalar gerçekleştirilir.

        // ilgili etkinliğin listViewlere bind işlemi gerçekleştirilir
            private fun setupToplulukListView(topluluk: List<String>) {
                var toplulukAdapter = ToplulukAdapter(this, R.layout.topluluk_item_row, topluluklar)
                binding.lvTopluluklar.adapter = toplulukAdapter

                var indis =0
                while(indis<topluluk.size){
                    for (i in 0..topluluklar.size-1)
                    {
                        val s1 = topluluk.get(indis).trim()// caz
                        val s2 = topluluklar.get(i).toplulukAdi.trim()

                        if (s1.equals(s2)) {
                            Log.e("activity",i.toString())
                            toplulukAdapter.getView(i,findViewById(R.id.lv_topluluklar))
                            //                    //println("Equal")
                        }

                    }
                    indis += 1
                }




            }
            private fun setupKapsamListView(kapsam:String) {
                var kapsamAdapter = KapsamAdapter(this, R.layout.kapsam_item_row, kapsamlar)
                binding.lvKapsamlar.adapter = kapsamAdapter

                for(i in 0..kapsamlar.size-1) {
                    val s1 = kapsam
                    val s2 = kapsamlar.get(i)
                    if (s1.equals(s2.kapsamAdi,true)) {
                        kapsamAdapter.getView(i,findViewById(R.id.lv_kapsamlar))
                        break
                        //println("Equal")
                    }

                }
            }
            private fun setupIlceListView(ilce: String) {
                var ilceAdapter = IlceAdapter(this, R.layout.ilce_item_row, ilceler)
                binding.lvIlceler.adapter = ilceAdapter

                for(i in 0..ilceler.size-1) {
                    val s1 = ilce
                    val s2 = ilceler.get(i)
                    if (s1.equals(s2.ilceAdi,true)) {
                        ilceAdapter.getView(i,findViewById(R.id.lvIlceler))
                        break
                        //println("Equal")
                    }

                }
            }
            private fun setupSemtListView(semt: String) {

                var semtAdapter = SemtAdapter(this, R.layout.semt_item_row, semtler)
                binding.lvSemtler.adapter = semtAdapter

                for(i in 0..semtler.size-1) {
                    val s1 = semt
                    val s2 = semtler.get(i)
                    if (s1.equals(s2.semtAdi,true)) {
                        Log.e("tag",s1.equals(s2.semtAdi,true).toString())
                        semtAdapter.getView(i,findViewById(R.id.lv_semtler))
                        break
                        //println("Equal")
                    }

                }


            }


//ListView verilerinin ilklenmesi
    private fun listViewDataEntry(){
        kapsamListViewDataEntry()
        semtListViewDataEntry()
        toplulukListViewDataEntry()
        ilceListViewDataEntry()


    }

        private fun kapsamListViewDataEntry() {
            var res = resources.getStringArray(R.array.kapsamlar) as Array<String>
            for (i in 0..res.size-1){
                kapsamlar.add(
                    KapsamListItemModel(res.get(i))
                )
            }


        }
        private fun semtListViewDataEntry() {
            var res = resources.getStringArray(R.array.semtler) as Array<String>
            for (i in 0..res.size-1){
                semtler.add(
                    SemtListItemModel(res.get(i))
                )
            }


        }
        private fun toplulukListViewDataEntry() {
            var res = resources.getStringArray(R.array.topluluklar) as Array<String>
            for (i in 0..res.size-1){
                topluluklar.add(
                    ToplulukListItemModel(res.get(i))
                )
            }


        }
        private fun ilceListViewDataEntry() {
            var res = resources.getStringArray(R.array.ilceler) as Array<String>
            for (i in 0..res.size-1){
                ilceler.add(
                    IlceListItemModel(res.get(i))
                )
            }


        }

}




