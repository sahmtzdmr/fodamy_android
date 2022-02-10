package com.sadikahmetozdemir.sadik_fodamy.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sadikahmetozdemir.sadik_fodamy.BR
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.core.utils.findGenericSuperclass
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.snackbar

abstract class BaseBottomSheet<VDB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : BottomSheetDialogFragment() {
    private var _binding: VDB? = null
    val binding: VDB get() = _binding!!

    lateinit var viewModel: VM

    @Suppress("UNCHECKED_CAST")
    val viewModelClass: Class<VM>
        get() = findGenericSuperclass<BaseBottomSheet<VDB, VM>>()
            ?.actualTypeArguments
            ?.getOrNull(1) as? Class<VM>
            ?: throw IllegalStateException("viewModelClass does not equal Class<VM>")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,

        )
        viewModel = ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialog)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        eventObserver()
        binding.setVariable(BR.vM, viewModel)
        binding.lifecycleOwner = this
        return binding.root
    }
    private fun eventObserver() {
        viewModel.baseEvent.observe(viewLifecycleOwner) { event ->
            evetHandler(event)
        }
    }
    private fun evetHandler(event: BaseViewEvent) {
        when (event) {
            BaseViewEvent.NavigateBack -> findNavController().popBackStack()
            is BaseViewEvent.NavigateTo -> findNavController().navigate(event.directions)
            is BaseViewEvent.ShowMessage -> snackbar(event.message)
            is BaseViewEvent.ShowToast -> Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
        }
    }
}
