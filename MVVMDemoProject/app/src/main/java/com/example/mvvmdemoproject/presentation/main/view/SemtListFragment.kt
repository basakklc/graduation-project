package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.data.repository.EtkinlikGuncellemeRepositoryImpl
import com.example.mvvmdemoproject.databinding.FragmentSemtListBinding
import com.example.mvvmdemoproject.domain.model.SemtListItemModel
import com.example.mvvmdemoproject.domain.usecase.EtkinlikGuncellemeUseCase
import com.example.mvvmdemoproject.presentation.main.adapter.SemtAdapter
import com.example.mvvmdemoproject.presentation.main.viewmodel.EtkinlikGuncellemeViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SemtListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SemtListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var currentFragment = ""
    var semt =""
    var semtler: MutableList<SemtListItemModel> = mutableListOf()
    var semtListesi = listOf<String>()
    lateinit var binding : FragmentSemtListBinding

    lateinit var rootView:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(arguments!=null){

            val liste = arguments!!.getStringArray(ARG_PARAM1)!! as Array<String>
            semtListesi = liste.toMutableList()

            semt = arguments!!.getString(ARG_PARAM2).toString()
            currentFragment = arguments!!.getString(ARG_PARAM3)!!

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_semt_list, container, false)

        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_semt_list)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        semtListViewDataEntry()
        setupSemtListView(semt)

        binding.onayla.setOnClickListener {
            val pos  = binding.lvSemtler.checkedItemPositions
            var secim = ""
            for(i in 1.. semtler.size-1){
                if(pos[i]){
                    secim=semtler.get(i).semtAdi
                    break
                }
            }

            var bundleGuncelSemt=Bundle()
            bundleGuncelSemt.putString("guncelSemt",secim)

            var bundleSemt=Bundle()
            bundleSemt.putString("semt",secim)
            Thread.sleep(3000)

            if(currentFragment.equals("fragment_kullanici_etkinlik_ekleme"))
            {
                Navigation.findNavController(rootView).navigate(R.id.action_semtListFragment_to_kullaniciEtkinlikEkleme,bundleSemt)

            }
            else if(currentFragment.equals("fragment_etkinlik_gunceleme"))
                Navigation.findNavController(rootView).navigate(R.id.action_semtListFragment_to_etkinlikGuncellemeFragment,bundleGuncelSemt)
        }


    }
    private fun semtListViewDataEntry() {
        var res =semtListesi


        for (i in 0..res.size-1){
            semtler.add(
                SemtListItemModel(res.get(i))
            )
        }


    }
    private fun setupSemtListView(semt: String) {

        var semtAdapter = SemtAdapter(requireContext(), R.layout.semt_item_row, semtler)
        binding.lvSemtler.adapter = semtAdapter

        for(i in 0..semtler.size-1) {
            val s1 = semt
            val s2 = semtler.get(i)
            if (s1.equals(s2.semtAdi,true)) {
                Log.e("tag",s1.equals(s2.semtAdi,true).toString())
                semtAdapter.getView(i,binding.lvSemtler)
                break
                //println("Equal")
            }

        }


    }

    companion object {
        var ARG_PARAM1 ="semtListesi"
        var ARG_PARAM2 ="semtAdi"
        var ARG_PARAM3 = "currentFragment"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SemtListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SemtListFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
