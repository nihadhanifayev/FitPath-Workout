package com.example.fitpath

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.fitpath.databinding.FragmentProgramDetailsOverviewBinding
import com.example.fitpath.models.ProgramDetailsOverviewFragmentViewModel

class ProgramDetailsOverviewFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailsOverviewBinding
    private lateinit var viewmodel:ProgramDetailsOverviewFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_program_details_overview,container,false)

        val bundle:ProgramDetailsOverviewFragmentArgs by navArgs()
        val program = bundle.program
        design.program = program
        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:ProgramDetailsOverviewFragmentViewModel by viewModels()
        this.viewmodel = tempViewModel
    }
}