package com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentHomeTablayoutBinding
import com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice.EditorChoiceFragment
import com.sadikahmetozdemir.sadik_fodamy.ui.home.last_added.LastAddedFragment

class HomeTablayoutFragment : Fragment() {
    var binding:FragmentHomeTablayoutBinding?=null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeTablayoutBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter=TablayoutViewPagerAdapter(childFragmentManager)
        adapter.addFragment(EditorChoiceFragment(),"Editör Seçimi")
        adapter.addFragment(LastAddedFragment(),"Son Eklenenler")
        binding?.viewpagerTablayout?.adapter=adapter
        binding?.tablayout?.setupWithViewPager(binding?.viewpagerTablayout)



    }
}