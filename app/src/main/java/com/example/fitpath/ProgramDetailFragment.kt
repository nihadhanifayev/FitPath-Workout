package com.example.fitpath

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fitpath.databinding.FragmentProgramDetailBinding

class ProgramDetailFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentProgramDetailBinding.inflate(inflater,container,false)

        return design.root
    }

}