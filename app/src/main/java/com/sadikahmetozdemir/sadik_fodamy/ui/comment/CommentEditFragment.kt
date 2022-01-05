package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import android.os.Bundle
import android.view.View
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.base.BaseFragment
import com.sadikahmetozdemir.sadik_fodamy.databinding.FragmentCommentEditBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentEditFragment : BaseFragment<FragmentCommentEditBinding, CommentEditViewModel>(R.layout.fragment_comment_edit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
