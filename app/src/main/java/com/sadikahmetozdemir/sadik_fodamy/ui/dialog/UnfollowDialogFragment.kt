package com.sadikahmetozdemir.sadik_fodamy.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseBottomSheet
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentBottomSheetBinding

class UnfollowDialogFragment : BaseBottomSheet<FragmentBottomSheetBinding, UnfollowDialogViewModel>(R.layout.fragment_bottom_sheet) {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btUnfollow.setOnClickListener {
            onUnfollowClicked()
        }
        binding.btCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun onUnfollowClicked() {
        setFragmentResult("request_unfollow", bundleOf("unfollow" to true))
        findNavController().popBackStack()
    }
}
