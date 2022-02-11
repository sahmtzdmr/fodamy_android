package com.sadikahmetozdemir.sadik_fodamy.ui.home.last_added

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.data.shared.repositories.LastAddedPagingSource
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.sadik_fodamy.ui.home.main.HomeTablayoutFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LastAddedViewModel @Inject constructor(private val feedRepository: FeedRepository) :
    BaseViewModel() {

    var recipes: MutableLiveData<PagingData<Recipe>> = MutableLiveData()

    init {
        getLastAdded()
    }

    private fun getLastAdded() {

        sendRequest(
            request = {
                Pager(
                    config = PAGE_CONFIG,
                    pagingSourceFactory = { LastAddedPagingSource(feedRepository) }
                ).flow
            },
            success = {
                viewModelScope.launch {
                    it.cachedIn(viewModelScope).collect { recipes.value = it }
                }
            }
        )
    }

    fun openDetailScreen(recipeID: Int) {
        navigate(HomeTablayoutFragmentDirections.actionHomeTablayoutFragmentToNavigationRecipes(recipeID))
    }

    companion object {
        private val PAGE_CONFIG =
            PagingConfig(maxSize = 100, pageSize = 24, enablePlaceholders = false)
    }
}
