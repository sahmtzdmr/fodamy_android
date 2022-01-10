package com.sadikahmetozdemir.sadik_fodamy.ui.home.main

sealed class HomeTablayoutEvent {

    data class ShowMassage(val message: String) : HomeTablayoutEvent()
}
