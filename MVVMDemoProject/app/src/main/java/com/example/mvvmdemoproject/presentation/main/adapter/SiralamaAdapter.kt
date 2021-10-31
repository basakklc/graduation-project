package com.example.mvvmdemoproject.presentation.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckedTextView
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.databinding.SiralamaitemRowBinding

class SiralamaAdapter (var context: Context, var resource:Int, var siralamaKategorileri:List<String>) : BaseAdapter() {
    var secilenlerListesi = arrayListOf<Int>()

    lateinit var checkedTextView : CheckedTextView
    lateinit var listView : ListView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var holder : SiralamaHolder
        var binding: SiralamaitemRowBinding
        if(convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),resource,parent,false);
            holder= SiralamaHolder(binding)
            holder.v=binding.root
            holder.v.setTag(holder)
        }

        else{
            holder=convertView.getTag() as SiralamaHolder
        }

        checkedTextView = holder.v.findViewById(R.id.ctv_siralama)
        holder.binding.ctvSiralama.text=siralamaKategorileri.get(position)

        checkedTextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                var selectedItemId= position
                if(secilenlerListesi.contains(selectedItemId) ){

                    secilenlerListesi.remove(selectedItemId)
                    (parent as ListView).setItemChecked(position,false)//checkedTextView.isChecked=false
                }
                else{
                    secilenlerListesi.add(selectedItemId)
                    (parent as ListView).setItemChecked(position,true)
                }
            }
        })

        return holder.v

    }

    fun getView ( position : Int, listView: ListView){
        listView.setItemChecked(position,true)
        secilenlerListesi.add(position)

        var item = listView.getChildAt(position) //as CheckedTextView
    }

    override fun getItem(p0: Int): Any {
        return siralamaKategorileri.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return siralamaKategorileri.size
    }

    fun secilenler(): ArrayList<Int>{
      return  secilenlerListesi
    }

    class SiralamaHolder(var binding: SiralamaitemRowBinding ) {
        var v: View = binding.root
        var b: SiralamaitemRowBinding = binding


    }


}


