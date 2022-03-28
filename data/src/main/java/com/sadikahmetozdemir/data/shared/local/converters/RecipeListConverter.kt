package com.sadikahmetozdemir.data.shared.local.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sadikahmetozdemir.data.shared.local.dto.RecipeDatabase

@ProvidedTypeConverter
class RecipeListConverter {

    @TypeConverter
    fun recipeListToJson(recipes: List<RecipeDatabase?>): String {
        return toJson<List<RecipeDatabase>>(recipes)
    }

    @TypeConverter
    fun jsonToRecipeList(recipes: String): List<RecipeDatabase?> {
        return fromJson<List<RecipeDatabase>>(recipes)
    }
}