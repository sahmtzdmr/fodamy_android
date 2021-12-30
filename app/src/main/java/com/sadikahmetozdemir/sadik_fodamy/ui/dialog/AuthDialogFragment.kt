package com.sadikahmetozdemir.sadik_fodamy.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentAuthDialogBinding

class AuthDialogFragment : DialogFragment() {
    var binding: FragmentAuthDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAuthDialogBinding.inflate(layoutInflater)
        binding?.btCancel?.setOnClickListener {
            dismiss()
        }
        binding?.btLogin?.setOnClickListener {
            findNavController().navigate(AuthDialogFragmentDirections.toLoginFragment())
        }

        return binding?.root
    }
}
