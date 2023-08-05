package com.soyaa.workbookchap5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.soyaa.workbookchap5.databinding.ListviewItemBinding

class CustomAdapter(context: Context, private val businessCardArraylist:ArrayList<BusinessCard>): BaseAdapter(){

    private val inflater=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater
    private lateinit var binding:ListviewItemBinding

    override fun getCount(): Int =businessCardArraylist.size

    override fun getItem(p0: Int): Any =businessCardArraylist[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {

        binding = ListviewItemBinding.inflate(inflater, p2, false)
        binding.nameListviewItem.text = businessCardArraylist[position].name
        binding.contentsListviewItem.text = businessCardArraylist[position].contents

        return binding.root


    }
}
