package com.sadikahmetozdemir.sadik_fodamy.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentCommentDialogBinding

class CommentDialogFragment : BottomSheetDialogFragment() {
    var binding: FragmentCommentDialogBinding? = null
    val viewModel by viewModels<CommentDialogViewModel>()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentDialogBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btDelete.setOnClickListener {
                onDeleteClicked()
            }
            btEdit.setOnClickListener {
                onEditClicked()
            }
        }
    }

    private fun onDeleteClicked() {
        setFragmentResult("request_delete", bundleOf("delete" to true))
        findNavController().popBackStack()
    }

    private fun onEditClicked() {
        setFragmentResult("request_edit", bundleOf("edit" to true))
        findNavController().popBackStack()
    }
}
