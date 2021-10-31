package com.example.mvvmdemoproject.presentation.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.CheckedTextView
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.databinding.KapsamItemRowBinding
import com.example.mvvmdemoproject.domain.model.KapsamListItemModel
import kotlinx.android.synthetic.main.kapsam_item_row.view.*


class KapsamAdapter(var context:Context,var resource:Int,var kapsamList:List<KapsamListItemModel>) : BaseAdapter() {

    var secilenlerListesi = arrayListOf<Int>()
    var secim= ""
    override fun getItem(p0: Int): Any {

        return kapsamList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return kapsamList.size
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var holder : KapsamHolder
        var binding: KapsamItemRowBinding
        if(convertView == null){
            //  var  layoutInflater:LayoutInflater = LayoutInflater.from(context);
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),resource,parent,false);
            holder= KapsamHolder(binding)
            holder.v=binding.root
            holder.v.setTag(holder)
        }
        else{
            holder=convertView.getTag() as KapsamHolder

        }
        holder.binding.kapsamListItem=kapsamList.get(position)

        var checkedTextView :CheckedTextView= holder.v.findViewById(R.id.checkedTextView)

        checkedTextView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                (parent as ListView).setItemChecked(position,true)
            }
        })

        return holder.v
    }
    fun getView ( position : Int, listView: ListView){
        listView.setItemChecked(position,true)
        var item = listView.getChildAt(position) //as CheckedTextView
    }

    fun secilenler(): ArrayList<Int>{
        return secilenlerListesi
    }
    class KapsamHolder(var binding: KapsamItemRowBinding){
        var v:View=binding.root
        var b:KapsamItemRowBinding=binding
    }

}


