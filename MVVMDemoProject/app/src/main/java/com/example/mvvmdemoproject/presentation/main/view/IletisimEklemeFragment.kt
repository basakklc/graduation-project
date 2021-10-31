package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGuncellemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.FragmentIletisimEklemeBinding
import com.example.mvvmdemoproject.databinding.FragmentKonusmaciEklemeBinding
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModel
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModelFactory
import kotlinx.android.synthetic.main.iletisim_ekleme.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [IletisimEklemeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IletisimEklemeFragment : Fragment() {


    lateinit var rootLayout:View

    private lateinit var etkinlikGuncellemeViewModel: EtkinlikGuncellemeViewModel
    var repository = EtkinlikGuncellemeRepositoryImpl()
    var etkinlikGuncellemeUseCase = EtkinlikGuncellemeUseCase(repository)
    lateinit var binding:FragmentIletisimEklemeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if(it.containsKey(ARG_PARAM1)){
                eID=it.getInt(ARG_PARAM1)


            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootLayout = inflater.inflate(R.layout.fragment_iletisim_ekleme, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_iletisim_ekleme)

        var etkinlikViewModelFactory = EtkinlikGuncellemeViewModelFactory(etkinlikGuncellemeUseCase, eID)
        etkinlikGuncellemeViewModel = ViewModelProvider(this, etkinlikViewModelFactory).get(EtkinlikGuncellemeViewModel::class.java)

        binding.iiletisimArti.setOnClickListener {
            if (! binding.etiletisimAdresi.text.toString().isNullOrEmpty() ) {

                etkinlikGuncellemeViewModel.createIletisimAdresi(binding.etiletisimAdresi.text.toString())

                var yeniIletisimView = inflater.inflate(R.layout.iletisim_ekleme, null)

                yeniIletisimView.iadres.setText(binding.etiletisimAdresi.text)

                binding.rootLayoutiletisim.addView(yeniIletisimView)
                yeniIletisimView.ic_iletisim_sil.setOnClickListener {
                    etkinlikGuncellemeViewModel.deleteIletisim(yeniIletisimView.iadres.text.toString())
                    binding.rootLayoutiletisim.removeView(yeniIletisimView)

                }
            }
        }

        return rootLayout
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.tamamla.setOnClickListener {
            Navigation.findNavController(rootLayout).navigate(R.id.action_iletisimEklemeFragment_to_etkinlikGuncellemeFragment)
        }
    }


    companion object {
        var eID=0
        var ARG_PARAM1 = "etkinlikID"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment IletisimEklemeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IletisimEklemeFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)

                }
            }
    }
}
