package com.soyaa.workbookchap6_1_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.soyaa.workbookchap6_1_2.databinding.FragmentHeartBinding

class HeartFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHeartBinding.inflate(layoutInflater).root
    }
}