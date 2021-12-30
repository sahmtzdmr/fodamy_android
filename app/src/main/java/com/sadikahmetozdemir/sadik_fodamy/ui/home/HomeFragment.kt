package com.sadikahmetozdemir.sadik_fodamy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentEditorChoiceBinding
import com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice.EditorChoiceAdapter
import com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice.EditorChoiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel by viewModels<EditorChoiceViewModel>()

    @Inject
    lateinit var editorChoiceAdapter: EditorChoiceAdapter
    private var _binding: FragmentEditorChoiceBinding? = null
    private val binding: FragmentEditorChoiceBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditorChoiceBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecylerView.apply {
            setHasFixedSize(true)
            adapter = editorChoiceAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
        }
        getRecipeData()
    }

    fun getRecipeData() {
        viewModel._recipes.observe(viewLifecycleOwner) {

            editorChoiceAdapter.submitData(
                viewLifecycleOwner.lifecycle,
                it
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
