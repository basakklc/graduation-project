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
import com.example.mvvmdemoproject.databinding.FragmentKapsamListBinding
import com.example.mvvmdemoproject.domain.model.KapsamListItemModel
import com.example.mvvmdemoproject.presentation.main.adapter.KapsamAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class KapsamListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rootView : View
    lateinit var binding: FragmentKapsamListBinding

    var etkinlikKapsami = ""
    var currentFragment = ""

    var kapsamlar: MutableList<KapsamListItemModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            etkinlikKapsami = it.getString("kapsamAdi").toString()
            currentFragment = it.getString("currentFragment").toString()


        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView= inflater.inflate(R.layout.fragment_kapsam_list, container, false)


        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_kapsam_list)


        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        kapsamListViewDataEntry()
        setupKapsamListView(etkinlikKapsami)

        binding.onayla.setOnClickListener {

            val pos  = binding.lvKapsamlar.checkedItemPositions
            var secim = ""
            for(i in 1.. kapsamlar.size-1){
                if(pos[i]){
                    secim=kapsamlar.get(i).kapsamAdi
                    break
                }
            }

            var bundleGuncelKapsam=Bundle()
            bundleGuncelKapsam.putString("guncelKapsam",secim)

            var bundleKapsam=Bundle()
            bundleKapsam.putString("kapsam",secim)
            Thread.sleep(3000)

            if(currentFragment.equals("fragment_kullanici_etkinlik_ekleme"))
            {
                Navigation.findNavController(rootView).navigate(R.id.action_kapsamListFragment_to_kullaniciEtkinlikEkleme,bundleKapsam)

            }
            else if(currentFragment.equals("fragment_etkinlik_gunceleme"))
                Navigation.findNavController(rootView).navigate(R.id.action_kapsamListFragment_to_etkinlikGuncellemeFragment,bundleGuncelKapsam)
        }


    }


    private fun kapsamListViewDataEntry() {
        var res = resources.getStringArray(R.array.kapsamlar) as Array<String>
        for (i in 0..res.size-1){
            kapsamlar.add(
                KapsamListItemModel(res.get(i))
            )
        }


    }
    private fun setupKapsamListView(kapsam:String) {
        var kapsamAdapter = KapsamAdapter(requireContext(), R.layout.kapsam_item_row, kapsamlar)
        binding.lvKapsamlar.adapter = kapsamAdapter

        for(i in 0..kapsamlar.size-1) {
            val s1 = kapsam
            val s2 = kapsamlar.get(i)
            if (s1.equals(s2.kapsamAdi,true)) {
                kapsamAdapter.getView(i,binding.lvKapsamlar)
            }

        }


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KapsamListFragment.
         */
// TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KapsamListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}