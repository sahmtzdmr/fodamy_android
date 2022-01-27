package com.sadikahmetozdemir.data.shared.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.ImagesModel
import com.sadikahmetozdemir.data.shared.local.NumberOfPersonModel
import com.sadikahmetozdemir.data.shared.local.TimeOfRecipeModel
import com.sadikahmetozdemir.data.shared.local.User
import kotlinx.parcelize.Parcelize


@Parcelize
data class EditorChoiceModel(
    var id: Int,
    var title: String?,
    var ingredients: String?,
    var definition: String?,
    var directions: String?,
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
    var user: User?,
    @SerializedName("time_of_recipe")
    var timeOfRecipe: TimeOfRecipeModel?,
    @SerializedName("number_of_person")
    var numberOfPerson: NumberOfPersonModel?,
    var categoryModel:CategoryModel?,
    var images: List<ImagesModel>?,
    @SerializedName("is_liked")
    var isLiked: Boolean,
    var text: String?,
    var language:String,
    @SerializedName("is_top_user_choice")
    var isTopUserChoice:Boolean,




    ) : Parcelable
