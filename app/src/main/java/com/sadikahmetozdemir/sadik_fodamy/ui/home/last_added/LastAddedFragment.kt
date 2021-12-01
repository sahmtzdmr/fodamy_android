package com.sadikahmetozdemir.sadik_fodamy.ui.home.last_added

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentLastAddedBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LastAddedFragment : Fragment() {
    val viewModel by viewModels<LastAddedViewModel>()

    @Inject
    lateinit var lastAddedAdapter: LastAddedAdapter
    var binding: FragmentLastAddedBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLastAddedBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lastAddedRecylerView?.apply {
            setHasFixedSize(true)
            adapter = lastAddedAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }
        getLastAddedData()


    }

    fun getLastAddedData() {
        viewModel.recipes.observe(viewLifecycleOwner) {
            lastAddedAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }



}