package com.example.mvvmdemoproject.presentation.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemoproject.databinding.EtkinlikRawItemBinding
import com.example.mvvmdemoproject.domain.model.EtkinlikListelemeResponse

class EtkinlikAdapter (etkinlikListesi:MutableList<EtkinlikListelemeResponse>,val listener:RecyclerviewClickListener): RecyclerView.Adapter<EtkinlikAdapter.EtkinlikViewHolder>() {

    var etkinlikler=etkinlikListesi
    var rcListener = listener

    override fun getItemCount(): Int {
        return etkinlikler.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EtkinlikViewHolder {
        var layoutInflater:LayoutInflater= LayoutInflater.from(parent.context)
        var etkinlikRawItemBinding:EtkinlikRawItemBinding=EtkinlikRawItemBinding.inflate(layoutInflater,parent,false)
        return EtkinlikViewHolder(etkinlikRawItemBinding)
    }


    override fun onBindViewHolder(holder: EtkinlikViewHolder, position: Int) {
        //  holder.bind(etkinlikler.get(position))

        var etkinlik:EtkinlikListelemeResponse=etkinlikler.get(position)
        holder.etkinlikRawItemBinding.etkinlik=etkinlik


        /* tvBaslik.text=item.etkinlikAdi
         tvTarih.text=item.etkinlikTarihi
         tvKonum.text=item.ilceAdi+item.semtAdi+item.acikAdres
         tvUcret.text=item.ucret.toString()*/

    }

    fun loadData(list: List<EtkinlikListelemeResponse>){
        etkinlikler.addAll(list)
    }

    interface RecyclerviewClickListener{

        fun onClick(v:View,position: Int)

    }



    inner class EtkinlikViewHolder(var etkinlikRawItemBinding: EtkinlikRawItemBinding) : RecyclerView.ViewHolder(etkinlikRawItemBinding.root),View.OnClickListener {

        init{
            etkinlikRawItemBinding.root.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            rcListener.onClick(p0!!,adapterPosition)
        }


/*        fun bind(item:BindingEtkinlik){
            with(etkinlikRawItemBinding){
                tvBaslik.text=item.etkinlikAdi
                tvTarih.text=item.etkinlikTarihi
                tvKonum.text=item.ilceAdi+item.semtAdi+item.acikAdres
                tvUcret.text=item.ucret.toString()
            }*/

        /*etkinlikRawItemBinding.tvBaslik.text=item.etkinlikAdi
        etkinlikRawItemBinding.tvTarih.text=item.etkinlikTarihi
        etkinlikRawItemBinding.tvKonum.text=item.ilceAdi+item.semtAdi+item.acikAdres
        etkinlikRawItemBinding.tvUcret.text=item.ucret.toString()
        etkinlikRawItemBinding.executePendingBindings()
    }*/

    }




}