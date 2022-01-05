package com.sadikahmetozdemir.sadik_fodamy.base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sadikahmetozdemir.sadik_fodamy.BR
import com.sadikahmetozdemir.sadik_fodamy.core.utils.findGenericSuperclass
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.snackbar
import java.lang.IllegalStateException

abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> constructor(
    @LayoutRes private val layoutId: Int
) : Fragment() {
    @Suppress("UNCHECKED_CAST")
    val viewModelClass: Class<VM>
        get() = findGenericSuperclass<BaseFragment<VDB, VM>>()
            ?.actualTypeArguments
            ?.getOrNull(1) as? Class<VM>
            ?: throw IllegalStateException("viewModelClass does not equal Class<VM>")
    lateinit var viewModel: VM
    lateinit var binding: VDB
    var rootView: View? = null
    private var isViewCreated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(viewModelClass)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (isViewCreated) {
            return rootView
        }
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        binding.setVariable(BR.vM, viewModel)
        rootView = binding.root
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel?.baseEvent?.observe(viewLifecycleOwner) {
                onViewEvent(it)
            }
        }
    }

    private fun onViewEvent(event: BaseViewEvent) {
        when (event) {
            is BaseViewEvent.NavigateTo -> {
                findNavController().navigate(event.directions)
            }
            is BaseViewEvent.ShowMessage -> {
                snackbar(event.message)
            }
            BaseViewEvent.NavigateBack -> {
                findNavController().popBackStack()
            }
        }
    }
}
