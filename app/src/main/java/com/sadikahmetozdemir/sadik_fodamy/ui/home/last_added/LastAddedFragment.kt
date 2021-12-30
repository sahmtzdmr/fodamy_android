package com.sadikahmetozdemir.sadik_fodamy.ui.home.last_added

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentLastAddedBinding
import com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout.HomeTablayoutFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LastAddedFragment : BaseFragment<FragmentLastAddedBinding, LastAddedViewModel>(R.layout.fragment_last_added) {
    @Inject
    lateinit var lastAddedAdapter: LastAddedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lastAddedRecylerView?.apply {
            setHasFixedSize(true)
            adapter = lastAddedAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        lastAddedAdapter._itemClicked =
            {
                (parentFragment as HomeTablayoutFragment).openRecipeDetail(it)
            }
        getLastAddedData()
    }

    fun getLastAddedData() {
        viewModel?.recipes?.observe(viewLifecycleOwner) {
            lastAddedAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}
