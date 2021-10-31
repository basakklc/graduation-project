package com.example.mvvmdemoproject.presentation.main.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.mvvmdemoproject.R
import java.lang.StringBuilder

class FilterAdapter(var listGroup:ArrayList<String>,var listChild:HashMap<String,ArrayList<String>?>):BaseExpandableListAdapter() {

    var topluluklar = arrayListOf<String>()
    var kapsamlar = arrayListOf<String>()

    override fun getGroup(p0: Int): Any {
        return listGroup.get(p0)
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        var layoutInflater=LayoutInflater.from(p3?.context)
        var view=layoutInflater.inflate(android.R.layout.simple_expandable_list_item_1,p3,false)
        var textView:TextView=view.findViewById(android.R.id.text1)
        textView.text=getGroup(p0) as CharSequence
        textView.setTypeface(null,Typeface.BOLD)
        textView.setTextColor(Color.BLUE)

        return view

    }

    override fun getChildrenCount(p0: Int): Int {
        return listChild.get(listGroup.get(p0))!!.size
    }

    override fun getChild(p0: Int, p1: Int): Any {
        return listChild.get(listGroup.get(p0))!!.get(p1)
    }

    override fun getGroupId(p0: Int): Long {
        return 1
    }

    fun topluluklar(): ArrayList<String>{
        return topluluklar
    }
    fun kapsamlar(): ArrayList<String>{
        return kapsamlar
    }

    override fun getChildView(group: Int, child: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        var layoutInflater=LayoutInflater.from(p4?.context)
        var view=layoutInflater.inflate(R.layout.filter_list_item,p4,false)
        var textView:CheckedTextView=view.findViewById(R.id.cb_filter)
        textView.text=getChild(group,child) as CharSequence


        textView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                textView.isChecked=true
                var selectedItem=(getChild(group,child) as String).trim()
                if(topluluklar.contains(selectedItem) or kapsamlar.contains(selectedItem)){
                    if (group==1) topluluklar.remove(selectedItem) else kapsamlar.remove(selectedItem)
                    textView.isChecked=false
                }
                else{
                    if (group==1) topluluklar.add(selectedItem) else kapsamlar.add(selectedItem)
                }
            }

        })

        return view

    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return 1
    }

    override fun getGroupCount(): Int {
        return listGroup.size
    }


}