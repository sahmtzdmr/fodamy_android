package com.sadikahmetozdemir.data.shared.remote

import com.sadikahmetozdemir.data.shared.local.PaginationModel


data class EditorChoiceResponseModel(
    var data: List<EditorChoiceModel>?,
    var pagination: PaginationModel?
)
