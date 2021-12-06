package com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.core.content.ContextCompat
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
        binding?.toolbar?.ivBack?.visibility=View.GONE
        binding?.toolbar?.tvBack?.visibility=View.GONE


        val root: View? = binding?.tablayout?.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(ContextCompat.getColor(requireContext(),R.color.divider))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable





    }
}
}