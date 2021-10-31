package com.example.mvvmdemoproject.presentation.main.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckedTextView
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import com.example.mvvmdemoproject.R
import com.example.mvvmdemoproject.databinding.SemtItemRowBinding
import com.example.mvvmdemoproject.domain.model.SemtListItemModel
import kotlinx.android.synthetic.main.semt_item_row.view.*
import kotlin.math.abs

class SemtAdapter (var context: Context, var resource:Int, var semtList:List<SemtListItemModel>) : BaseAdapter() {

    lateinit var checkedTextView :CheckedTextView
    lateinit var listView :ListView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var holder : SemtHolder
        var binding: SemtItemRowBinding

        if(convertView == null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),resource,parent,false);
            holder= SemtHolder(binding)
            holder.v=binding.root
            holder.v.setTag(holder)
        }
        else{
            holder=convertView.getTag() as SemtHolder

        }
        checkedTextView = holder.v.findViewById(R.id.checkedTextView)
        holder.binding.semtListItem=semtList.get(position)


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

    override fun getItem(p0: Int): Any {
        return semtList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return semtList.size
    }


    class SemtHolder(var binding: SemtItemRowBinding ) {
        var v: View = binding.root
        var b: SemtItemRowBinding = binding


    }


}



