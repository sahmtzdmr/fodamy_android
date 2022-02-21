package com.sadikahmetozdemir.data.service

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
    suspend fun insertRecipes(recipes: List<RecipeDatabase>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(comments: CommentDatabase)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(category: List<CategoryDatabase>)

//    @Query("select * from comments")
//    suspend fun getAllComments(recipeID: Int): List<CommentDatabase>

    @Query("select * from recipes where is_editor_choice = 1")
    suspend fun getEditorChoices(): List<RecipeDatabase>

//    @Query("select * from recipes where is_last_added=1")
//    suspend fun getLastAdded(): List<RecipeDatabase>


}