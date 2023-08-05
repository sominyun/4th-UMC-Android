package com.soyaa.workbookchap6_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainVPAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2 //아이템개수

    override fun createFragment(position: Int): Fragment { //어떤프래그먼트 보여줄지
        return when (position) {
            0->OneFragment()
            1->TwoFragment()
            else-> OneFragment()
        }
    }
}