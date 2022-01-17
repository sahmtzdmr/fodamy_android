package com.sadikahmetozdemir.data.shared.remote
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sadikahmetozdemir.data.shared.local.CategoryModel
import com.sadikahmetozdemir.data.shared.local.ImagesModel
import com.sadikahmetozdemir.data.shared.local.NumberOfPersonModel
import com.sadikahmetozdemir.data.shared.local.TimeOfRecipeModel
import com.sadikahmetozdemir.data.shared.local.UserModel
import kotlinx.parcelize.Parcelize


@Parcelize
data class EditorChoiceModel(
    var id: Int?,
    var title: String?,
    var ingredients: String?,
    var directions: String?,
    var difference: String?,
    @SerializedName("is_editor_choice") var isEditorChoice: Boolean?,
    var is_owner: Boolean?,
    var like_count: Int?,
    var comment_count: Int?,
    var user: UserModel?,
    var time_of_recipe: TimeOfRecipeModel?,
    var number_of_person: NumberOfPersonModel?,
    var category: CategoryModel?,
    var images: List<ImagesModel>?,
    var is_liked: Boolean?,
    var text: String?

) : Parcelable
