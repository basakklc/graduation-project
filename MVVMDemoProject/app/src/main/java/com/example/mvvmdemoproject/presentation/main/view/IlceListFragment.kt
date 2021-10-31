package com.example.mvvmdemoproject.presentation.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation

import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.databinding.FragmentIlceListBinding
import com.example.mvvmdemoproject.domain.model.IlceListItemModel
import com.example.mvvmdemoproject.presentation.main.adapter.IlceAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IlceListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IlceListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var ilce = ""
    var ilceler: MutableList<IlceListItemModel> = mutableListOf()

    var currentFragment = ""

    lateinit var binding: FragmentIlceListBinding
    lateinit var rootView:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ilce = it.getString("ilceAdi").toString()
            currentFragment = it.getString("currentFragment").toString()

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_ilce_list)

        rootView =  inflater.inflate(R.layout.fragment_ilce_list, container, false)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ilceListViewDataEntry()
        setupIlceListView(ilce)

        binding.onayla.setOnClickListener {
            val pos  = binding.lvIlce.checkedItemPositions
            var secim = ""
            for(i in 1.. ilceler.size-1){
                if(pos[i]){
                    secim=ilceler.get(i).ilceAdi
                    break
                }
            }
            var bundleGuncelIlce=Bundle()
            bundleGuncelIlce.putString("guncelIlce",secim)

            var bundleIlce=Bundle()
            bundleIlce.putString("ilce",secim)
            Thread.sleep(3000)

            if(currentFragment.equals("fragment_kullanici_etkinlik_ekleme"))
            {
                Navigation.findNavController(rootView).navigate(R.id.action_ilceListFragment_to_kullaniciEtkinlikEkleme,bundleIlce)

            }
            else if(currentFragment.equals("fragment_etkinlik_gunceleme"))
                Navigation.findNavController(rootView).navigate(R.id.action_ilceListFragment_to_etkinlikGuncellemeFragment,bundleGuncelIlce)

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

    private fun setupIlceListView(ilce: String) {
        var ilceAdapter = IlceAdapter(requireContext(), R.layout.ilce_item_row, ilceler)
        binding.lvIlce.adapter = ilceAdapter

        for(i in 0..ilceler.size-1) {
            val s1 = ilce
            val s2 = ilceler.get(i)
            if (s1.equals(s2.ilceAdi,true)) {
                ilceAdapter.getView(i,binding.lvIlce)
                break
                //println("Equal")
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
         * @return A new instance of fragment IlceListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IlceListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
