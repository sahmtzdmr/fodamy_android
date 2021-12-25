package com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout

import com.sadikahmetozdemir.sadik_fodamy.ui.RecipeDetailEvent

sealed class HomeTablayoutEvent {

    data class ShowMassage(val message: String) : HomeTablayoutEvent()


}