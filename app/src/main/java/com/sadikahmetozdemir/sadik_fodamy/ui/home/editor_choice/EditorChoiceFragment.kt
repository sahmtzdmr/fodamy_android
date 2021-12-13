package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentEditorChoiceBinding
import com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout.HomeTablayoutFragment
import com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout.HomeTablayoutFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EditorChoiceFragment : Fragment(){


    val viewModel by viewModels<EditorChoiceViewModel>()

    @Inject
    lateinit var editorChoiceAdapter: EditorChoiceAdapter
    private var _binding: FragmentEditorChoiceBinding? = null
    private val binding: FragmentEditorChoiceBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
        editorChoiceAdapter.itemClicked={
            (parentFragment as HomeTablayoutFragment).openRecipeDetail(it)

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