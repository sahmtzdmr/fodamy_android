package com.sadikahmetozdemir.data.service.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadikahmetozdemir.data.shared.local.dto.CategoryDatabase
import com.sadikahmetozdemir.data.shared.local.dto.CommentDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDatabase

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeDatabase>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLastAdded(recipes: List<RecipeDatabase>?)


    @Query("select * from recipes where is_editor_choice = 1 order by id desc ")
    fun getEditorChoices(): PagingSource<Int, RecipeDatabase>

    @Query("select * from recipes where is_editor_choice = 1 order by id desc ")
    suspend fun getEditorChoicesFromApi(): List<RecipeDatabase>

    @Query("select * from recipes where is_last_added=1 order by id desc")
    fun getLastAdded(): PagingSource<Int, RecipeDatabase>

    @Query("select * from recipes where is_last_added=1 order by id desc")
    fun getLastAddedFromApi(): List<RecipeDatabase>

    @Query("select * from recipes where id =:recipeId ")
    suspend fun getRecipeDetails(recipeId: Int): RecipeDatabase



}