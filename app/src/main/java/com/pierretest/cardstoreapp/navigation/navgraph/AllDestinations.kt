package com.pierretest.cardstoreapp.navigation.navgraph

import androidx.navigation.NavController

const val HOME = "Home"
const val INFO = "Info"
const val SINGLE_CARD = "SingleCard"
const val LIST_OF_CARDS = "List of Cards"

class AppNavigationActions(private val navController: NavController) {

    fun navigateToHome() {
        navController.navigate(HOME) {
            popUpTo(HOME)
        }
    }

    fun navigateToInfo() {
        navController.navigate(INFO) {
            popUpTo(INFO)
        }
    }

    fun navigateToList() {
        navController.navigate(LIST_OF_CARDS) {
            popUpTo(LIST_OF_CARDS)
        }
    }

    fun navigateToCard() {
        navController.navigate(SINGLE_CARD) {
            popUpTo(SINGLE_CARD)
        }
    }
}