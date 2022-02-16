package com.sadikahmetozdemir.data.shared.local.converters

import androidx.room.TypeConverter
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDataBase

class JsonConverter {

    @TypeConverter
    fun recipeListToJson(recipes: List<RecipeDataBase>): String {
        return toJson<List<RecipeDataBase>>(recipes)
    }

    @TypeConverter
    fun jsonToRecipeList(recipes: String): List<RecipeDataBase> {
        return fromJson<List<RecipeDataBase>>(recipes)
    }
}