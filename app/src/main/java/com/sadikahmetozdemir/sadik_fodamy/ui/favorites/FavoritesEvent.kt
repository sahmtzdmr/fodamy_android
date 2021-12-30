package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

sealed class FavoritesEvent {

    data class ShowMessage(val message: String) : FavoritesEvent()
}
