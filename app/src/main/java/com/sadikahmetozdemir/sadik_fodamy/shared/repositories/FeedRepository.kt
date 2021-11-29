package com.sadikahmetozdemir.sadik_fodamy.shared.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sadikahmetozdemir.sadik_fodamy.api.ApiErrorResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiResponse
import com.sadikahmetozdemir.sadik_fodamy.api.ApiSuccessResponse
import com.sadikahmetozdemir.sadik_fodamy.api.EditorChoiceRecipesAPI
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.*
import kotlinx.coroutines.flow.Flow
import java.io.IOException

import javax.inject.Inject

class FeedRepository @Inject constructor(private val editorChoiceRecipesAPI: EditorChoiceRecipesAPI) {

    fun feedRequest(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<EditorChoiceModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {RecipePagingSource(editorChoiceRecipesAPI)}
        ).flow
    }
    /**
     * let’s define page size, page size is the only required param, rest is optional
     */
    fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = 24)
    }






}