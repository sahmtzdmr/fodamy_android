package com.sadikahmetozdemir.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sadikahmetozdemir.data.shared.local.dto.CommentDatabase
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDataBase

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeDataBase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: CommentDatabase)

    @Query("select * from comments")
    suspend fun getAllComments(recipeID: Int): List<CommentDatabase>

    @Query("select * from recipes where is_editor_choice =:recipeType")
    suspend fun getEditorChoiceRecipes(recipeType: Int): List<RecipeDataBase>

    @Query("select * from recipes")
    suspend fun getAllRecipes(recipeType: Int): List<RecipeDataBase>

}