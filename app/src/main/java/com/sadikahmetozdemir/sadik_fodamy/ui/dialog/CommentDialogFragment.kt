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
import com.sadikahmetozdemir.sadik_fodamy.base.BaseBottomSheet
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentCommentDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentDialogFragment : BaseBottomSheet<FragmentCommentDialogBinding,CommentDialogViewModel>(R.layout.fragment_comment_dialog) {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btDelete.setOnClickListener {
                onDeleteClicked()
            }
        }
    }

    private fun onDeleteClicked() {
        setFragmentResult("request_delete", bundleOf("delete" to true))
        findNavController().popBackStack()
    }
}
