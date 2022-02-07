package com.sadikahmetozdemir.sadik_fodamy.ui.user


import com.sadikahmetozdemir.domain.repositories.UserRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : BaseViewModel() {




}