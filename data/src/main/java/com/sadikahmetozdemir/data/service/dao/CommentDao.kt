package com.sadikahmetozdemir.data.service.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadikahmetozdemir.data.shared.local.dto.CommentDatabase

@Dao
interface CommentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: List<CommentDatabase>)


    @Query("select * from comments where recipe_id =:recipeId order by id desc")
    fun getRecipeComments(recipeId: Int): PagingSource<Int, CommentDatabase>

}