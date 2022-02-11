package com.sadikahmetozdemir.data.shared.remote

import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.ImagesModel
import com.sadikahmetozdemir.data.shared.local.NumberOfPersonModel
import com.sadikahmetozdemir.data.shared.local.TimeOfRecipeModel
import com.sadikahmetozdemir.data.shared.local.User


data class EditorChoiceModel(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String?,
    @SerializedName("ingredients")
    var ingredients: String?,
    @SerializedName("definition")
    var definition: String?,
    @SerializedName("directions")
    var directions: String?,
    @SerializedName("difference")
    var difference: String?,
    @SerializedName("number_of_favorite_count")
    val numberOfFavoriteCount: Int?,
    @SerializedName("is_editor_choice")
    var isEditorChoice: Boolean?,
    @SerializedName("is_owner")
    var isOwner: Boolean?,
    @SerializedName("like_count")
    var likeCount: Int?,
    @SerializedName("comment_count")
    var commentCount: Int?,
    @SerializedName("user")
    var user: User?,
    @SerializedName("time_of_recipe")
    var timeOfRecipe: TimeOfRecipeModel?,
    @SerializedName("number_of_person")
    var numberOfPerson: NumberOfPersonModel?,
    @SerializedName("categoryModel")
    var categoryModel: CategoryModel?,
    @SerializedName("images")
    var images: List<ImagesModel>?,
    @SerializedName("is_liked")
    var isLiked: Boolean,
    @SerializedName("text")
    var text: String?,
    @SerializedName("language")
    var language: String,
    @SerializedName("is_top_user_choice")
    var isTopUserChoice: Boolean,
)
