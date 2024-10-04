package com.example.fitpath

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.toColorInt
import androidx.navigation.fragment.navArgs
import com.example.fitpath.databinding.FragmentProgramDetailsOverviewBinding

class ProgramDetailsOverviewFragment : Fragment() {
    private lateinit var design:FragmentProgramDetailsOverviewBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = FragmentProgramDetailsOverviewBinding.inflate(inflater,container,false)

        val bundle:ProgramDetailsOverviewFragmentArgs by navArgs()
        val program = bundle.program

        design.textViewProgramTitleOverview.text = program.program_name.toUpperCase()
        design.textViewDayOneTitle.text = program.dayOne
        design.textViewDayTwoTitle.text = program.dayTwo
        design.textViewDayThreeTitle.text = program.dayThree
        design.textViewDayFourTitle.text = program.dayFour
        design.textViewDayFifeTitle.text = program.dayFife
        design.textViewDaySixTitle.text = program.daySix
        design.textViewDaySevenTitle.text = program.daySeven

        return design.root
    }
}