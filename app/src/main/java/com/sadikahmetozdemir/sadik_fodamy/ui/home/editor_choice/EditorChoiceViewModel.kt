package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sadikahmetozdemir.data.shared.repositories.RecipePagingSource
import com.sadikahmetozdemir.sadik_fodamy.base.BaseViewModel
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.domain.repositories.FeedRepository
import com.sadikahmetozdemir.sadik_fodamy.ui.home.main.HomeTablayoutFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorChoiceViewModel @Inject constructor(private val feedRepository: FeedRepository) :
    BaseViewModel() {

    var recipes: MutableLiveData<PagingData<Recipe>> = MutableLiveData()


    init {
        getEditorChoice()
    }

    private fun getEditorChoice() {
        viewModelScope.launch {
            val pager = Pager(
                config = PAGE_CONFIG,
                pagingSourceFactory = {
                    RecipePagingSource(feedRepository)
                }).flow
            pager.cachedIn(viewModelScope).collect { recipes.value = it }
        }
    }

    fun openDetailScreen(recipeID: Int) {
        navigate(HomeTablayoutFragmentDirections.toRecipeDetail(recipeID))
    }

    companion object {
        private val PAGE_CONFIG =
            PagingConfig(maxSize = 100, pageSize = 24, enablePlaceholders = false)
    }
}
