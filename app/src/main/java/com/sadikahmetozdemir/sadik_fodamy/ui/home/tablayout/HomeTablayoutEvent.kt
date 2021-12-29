package com.sadikahmetozdemir.sadik_fodamy.ui.home.tablayout

sealed class HomeTablayoutEvent {

    data class ShowMassage(val message: String) : HomeTablayoutEvent()
}
