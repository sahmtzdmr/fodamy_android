package com.sadikahmetozdemir.sadik_fodamy.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.data.shared.repositories.UserLikePagingSource
import com.sadikahmetozdemir.data.shared.repositories.UserProfileRecipesPagingSource
import com.sadikahmetozdemir.data.utils.DataHelperManager
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.entities.UserProfile
import com.sadikahmetozdemir.domain.repositories.UserRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle,
    private val dataHelperManager: DataHelperManager
) : BaseViewModel() {
    val likes: MutableLiveData<PagingData<Recipe>> = MutableLiveData()
    val recipes: MutableLiveData<PagingData<Recipe>> = MutableLiveData()
    private val _user: MutableLiveData<UserProfile> = MutableLiveData()
    val user: LiveData<UserProfile> get() = _user
    private var userID: Int = savedStateHandle.get(USER_ID) ?:0

    init {
        viewModelScope.launch{
        if (userID==0){
         userID=dataHelperManager.getID()
        }
            getUserProfile()
            getUserLikes(userID)
            getUserProfileRecipes(userID)
        }
    }

    fun getUserProfile() = viewModelScope.launch {
        sendRequest(
            request = { userRepository.userProfileRequest(userID) },
            success = {
                _user.value = it
            }
        )
    }

    fun getUserLikes(userID: Int) {
        sendRequest(
            request = {
                Pager(config = PAGE_CONFIG, pagingSourceFactory = {
                    UserLikePagingSource(userRepository, userID)
                }).flow
            },
            success = {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect {
                        likes.value = it
                    }
                }
            }
        )
    }

    private fun getUserProfileRecipes(userID: Int) {
        sendRequest(
            request = {
                Pager(
                    config = PAGE_CONFIG,
                    pagingSourceFactory = {
                        UserProfileRecipesPagingSource(
                            userRepository,
                            userID
                        )
                    }
                ).flow
            }, success = {
            viewModelScope.launch {
                it.cachedIn(viewModelScope).collect {
                    recipes.value = it
                }
            }
        }
        )
    }

    companion object {
        const val USER_ID = "userId"
        private val PAGE_CONFIG =
            PagingConfig(maxSize = 100, pageSize = 24, enablePlaceholders = false)
    }
}
