package com.sadikahmetozdemir.sadik_fodamy.ui.user


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.UserProfile
import com.sadikahmetozdemir.domain.repositories.UserRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataHelperManager: DataHelperManager,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _recipes: MutableLiveData<PagingData<Recipe>> = MutableLiveData()
    val recipes: LiveData<PagingData<Recipe>> get() = _recipes
    private val _user: MutableLiveData<UserProfile> = MutableLiveData()
    val user: LiveData<UserProfile> get() = _user
    private var userID: Int = savedStateHandle.get(USER_ID) ?: 0
    init {
        getUserProfile()
    }


    fun getUserProfile() = viewModelScope.launch {
        sendRequest(
                request = { userRepository.userProfileRequest(userID) },
                success = {
                    _user.value = it
                }
            )
        }
    companion object {
        const val USER_ID = "UserId"
    }
    }


