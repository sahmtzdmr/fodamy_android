package com.sadikahmetozdemir.sadik_fodamy.shared.remote

import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.local.PaginationModel

data class EditorChoiceResponseModel(
    var data: List<EditorChoiceModel>,
    var pagination: PaginationModel
)
