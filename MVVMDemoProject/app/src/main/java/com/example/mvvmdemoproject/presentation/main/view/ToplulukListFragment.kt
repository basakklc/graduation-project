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


import com.example.mvvmdemoproject.databinding.FragmentToplulukListBinding
import com.example.mvvmdemoproject.domain.model.ToplulukListItemModel
import com.example.mvvmdemoproject.presentation.main.adapter.ToplulukAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToplulukListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToplulukListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentToplulukListBinding
    lateinit var rootView: View

    var currentFragment = ""

    var etkinlikTopluluklari = listOf<String>()
    var topluluklar: MutableList<ToplulukListItemModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if(it.containsKey("toplulukAdi")){
                val topluluklari = it.getStringArray("toplulukAdi") as Array<String>
                etkinlikTopluluklari = topluluklari.asList()
            }

            currentFragment = it.getString("currentFragment").toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView= inflater.inflate(R.layout.fragment_topluluk_list, container, false)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_topluluk_list)


        return rootView


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toplulukListViewDataEntry()
        setupToplulukListView(etkinlikTopluluklari)

        binding.onayla.setOnClickListener {
            val pos  = binding.lvTopluluklar.checkedItemPositions
            var secim = mutableListOf<String>()
            for(i in 1.. topluluklar.size-1){
                if(pos[i]){
                    secim.add(topluluklar.get(i).toplulukAdi)

                }
            }
            //Log.e("taf2",secim)

            var bundleGuncelTopluluk=Bundle()
            bundleGuncelTopluluk.putStringArray("guncelTopluluk",secim.toTypedArray())

            var topluluk=Bundle()
            topluluk.putStringArray("topluluk",secim.toTypedArray())

            Thread.sleep(3000)
            if(currentFragment.equals("fragment_kullanici_etkinlik_ekleme"))
            {
                Navigation.findNavController(rootView).navigate(R.id.action_toplulukListFragment_to_kullaniciEtkinlikEkleme,topluluk)

            }
            else if(currentFragment.equals("fragment_etkinlik_gunceleme"))
                Navigation.findNavController(rootView).navigate(R.id.action_toplulukListFragment_to_etkinlikGuncellemeFragment,bundleGuncelTopluluk)

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
    private fun setupToplulukListView(topluluk: List<String>) {
        var toplulukAdapter = ToplulukAdapter(requireContext(), R.layout.topluluk_item_row, topluluklar)
        binding.lvTopluluklar.adapter = toplulukAdapter

        var indis =0
        while(indis<topluluk.size){
            for (i in 0..topluluklar.size-1)
            {
                val s1 = topluluk.get(indis).trim()// caz
                val s2 = topluluklar.get(i).toplulukAdi.trim()

                if (s1.equals(s2,true)) {
                    toplulukAdapter.getView(i,binding.lvTopluluklar)
                    //                    //println("Equal")
                }

            }
            indis += 1
        }




    }

    companion object {

        private var ARG_PARAM1 = "toplulukAdi"
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ToplulukListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ToplulukListFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
