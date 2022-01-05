package com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentHomeTablayoutBinding
import com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice.EditorChoiceFragment
import com.sadikahmetozdemir.sadik_fodamy.ui.home.last_added.LastAddedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeTablayoutFragment : BaseFragment<FragmentHomeTablayoutBinding, HomeTablayoutViewModel>(R.layout.fragment_home_tablayout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        renderToolbar()
        viewModel
        dividerTabLayout()
        viewModel?.event?.observe(viewLifecycleOwner) { event ->
            when (event) {
                is HomeTablayoutEvent.ShowMassage -> {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT)
                        .show()
                    println(event.message)
                }
            }
        }
    }
    // tek tek viewmodellarda kullanıldığı için nasıl geçirilir araştır!
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
                viewModel?.logoutRequest()
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
