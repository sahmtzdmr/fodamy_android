package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import com.sadikahmetozdemir.sadik_fodamy.shared.local.*

data class EditorChoiceModel(
    var id: Int?,
    var title: String?,
    var ingredients: String?,
    var directions: Int?,
    var difference: String?,
    var is_editor_choice: Boolean?,
    var is_owner: Boolean?,
    var like_count: Int?,
    var comment_count: Int?,
    var user: UserModel?,
    var time_of_recipe:TimeOfRecipeModel?,
    var number_of_person:NumberOfPersonModel?,
    var category:CategoryModel?,
    var images:List<ImagesModel>?



)