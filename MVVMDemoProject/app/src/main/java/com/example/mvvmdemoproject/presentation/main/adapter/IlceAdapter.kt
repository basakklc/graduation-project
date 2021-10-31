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
import com.example.mvvmdemoproject.databinding.IlceItemRowBinding
import com.example.mvvmdemoproject.domain.model.IlceListItemModel

class IlceAdapter (var context: Context, var resource:Int, var ilceList:List<IlceListItemModel>) : BaseAdapter() {

    lateinit var checkedTextView : CheckedTextView
    lateinit var listView : ListView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var holder : IlceHolder
        var binding: IlceItemRowBinding

        if(convertView == null){
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),resource,parent,false);
            holder= IlceHolder(binding)
            holder.v=binding.root
            holder.v.setTag(holder)
        }
        else{
            holder=convertView.getTag() as IlceHolder

        }
        checkedTextView = holder.v.findViewById(R.id.checkedTextView)
        holder.binding.listItemIlce=ilceList.get(position)

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
        return ilceList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return ilceList.size
    }


    class IlceHolder(var binding: IlceItemRowBinding ) {
        var v: View = binding.root
        var b: IlceItemRowBinding = binding


    }


}