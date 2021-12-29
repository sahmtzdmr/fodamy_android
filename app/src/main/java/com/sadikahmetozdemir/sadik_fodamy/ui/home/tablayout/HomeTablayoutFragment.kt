package com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentHomeTablayoutBinding
import com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice.EditorChoiceFragment
import com.sadikahmetozdemir.sadik_fodamy.ui.home.last_added.LastAddedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTablayoutFragment : Fragment() {
    var binding: FragmentHomeTablayoutBinding? = null
    val viewModel by viewModels<HomeTablayoutViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeTablayoutBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        renderToolbar()
        dividerTabLayout()

        viewModel.event.observe(viewLifecycleOwner) { event ->
            when (event) {
                is HomeTablayoutEvent.ShowMassage -> {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT)
                        .show()
                    println(event.message)
                }
            }
        }
    }
    fun openRecipeDetail(recipeID: Int) {

        findNavController().navigate(HomeTablayoutFragmentDirections.toRecipeDetail(recipeID))
    }
    fun renderToolbar() {
        binding?.toolbar?.apply {
            ivBack.visibility = View.GONE
            tvBack.visibility = View.GONE
            tvFoodDetailTitle.visibility = View.GONE
            ivShare.visibility = View.GONE
            ivLogout.setOnClickListener {

                viewModel.logoutRequest()
            }
        }
    }
    fun dividerTabLayout() {

        val root: View? = binding?.tablayout?.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(ContextCompat.getColor(requireContext(), R.color.divider))
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }
    fun setUpViewPager() {
        val adapter = TablayoutViewPagerAdapter(childFragmentManager)
        adapter.addFragment(EditorChoiceFragment(), "Editör Seçimi")
        adapter.addFragment(LastAddedFragment(), "Son Eklenenler")
        binding?.viewpagerTablayout?.adapter = adapter
        binding?.tablayout?.setupWithViewPager(binding?.viewpagerTablayout)
    }
}
