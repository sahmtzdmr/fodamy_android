package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentEditorChoiceBinding
import com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout.HomeTablayoutFragment
import com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout.HomeTablayoutViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditorChoiceFragment :
    BaseFragment<FragmentEditorChoiceBinding, EditorChoiceViewModel>(R.layout.fragment_editor_choice) {
    @Inject
    lateinit var editorChoiceAdapter: EditorChoiceAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditorChoiceBinding.inflate(layoutInflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.homeRecylerView?.apply {
            setHasFixedSize(true)
            adapter = editorChoiceAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
        editorChoiceAdapter.itemClicked = {
            (parentFragment as HomeTablayoutFragment).openRecipeDetail(it)
        }
        getRecipeData()
    }

    fun getRecipeData() {
        viewModel?._recipes?.observe(viewLifecycleOwner) {
            editorChoiceAdapter.submitData(
                viewLifecycleOwner.lifecycle,
                it
            )
        }
    }
}
