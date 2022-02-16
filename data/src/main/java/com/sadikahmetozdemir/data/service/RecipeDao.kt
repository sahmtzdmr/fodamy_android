package com.sadikahmetozdemir.data.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDataBase

@Dao
interface RecipeDao {

    @Insert
    suspend fun  insertRecipes(recipes: List<RecipeDataBase>)

}