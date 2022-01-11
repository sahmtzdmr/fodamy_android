package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import android.os.Bundle
import android.view.View
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentEditorChoiceBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditorChoiceFragment :
    BaseFragment<FragmentEditorChoiceBinding, EditorChoiceViewModel>(R.layout.fragment_editor_choice) {
    @Inject
    lateinit var editorChoiceAdapter: EditorChoiceAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecylerView.apply {
            setHasFixedSize(true)
            adapter = editorChoiceAdapter
        }
        editorChoiceAdapter.itemClicked = {
            viewModel.openDetailScreen(it)
        }
        getRecipeData()
    }

    private fun getRecipeData() {
        viewModel.recipes.observe(viewLifecycleOwner) {
            editorChoiceAdapter.submitData(
                viewLifecycleOwner.lifecycle,
                it
            )
        }
    }
}
