package com.pierretest.cardstoreapp.navigation.navgraph

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.pierretest.cardstoreapp.data.repository.Repository
import com.pierretest.cardstoreapp.ui.AuthViewModel
import com.pierretest.cardstoreapp.ui.CardViewModel
import com.pierretest.cardstoreapp.ui.HomePage
import com.pierretest.cardstoreapp.ui.CardInfo
import com.pierretest.cardstoreapp.ui.LoginScreen
import com.pierretest.cardstoreapp.ui.SingleCardScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardShopAppNavGraph(
    context : Context,
    repository: Repository,
    authViewModel: AuthViewModel
//    navController: NavHostController
) {
    var displayBottomBar by remember { mutableStateOf(true) }
    var displayTopBar by remember { mutableStateOf(true) }
    val navController = rememberNavController()
    val cardViewModel : CardViewModel = hiltViewModel()

    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    navController.addOnDestinationChangedListener { _, destination, _ ->
        when (destination.route) {
            "SingleCardScreen/{cardId}" -> {
                displayBottomBar = false
                displayTopBar = true
            }
            "login" -> {
                displayBottomBar = false
                displayTopBar = false
            }

            "home" -> {
                displayBottomBar = true
                displayTopBar = false
            }
//
//            "info" -> {
//                displayBottomBar = false
//                displayTopBar = true
//            }

            else -> {
                displayBottomBar = true
                displayTopBar = false
            }
        }

    }

    Scaffold(
        topBar = {
            if (displayTopBar) {
                TopAppBar(
                    title = {
                        Text(text = "")
                    },
                    actions = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "")
                    },
                    navigationIcon = {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier.clickable {
                                navController.navigateUp()
//                                cardViewModel.clearGM()
                            })
                    }
                )
            }
        },
        bottomBar = {
            if (displayBottomBar) {
                BottomNavigationBar(navController)
            }
        },
        content = { paddingValues ->
            NavHost(
                navController,
                startDestination = "nav",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable("nav") {CheckAuth(navController, firebaseAuth )}
                composable("home") { HomePage(navController, firebaseAuth, context ) }
                composable("info") { CardInfo(navController) }
                composable("login") { LoginScreen(context, repository, navController, authViewModel) }
                composable("singlecard") { SingleCardScreen(context) }
/*                composable(
                    route = "CategoryDetailScreen/{catName}"
                ) {
                    val catName = it.arguments?.getString("catName") ?:""

                    if (catName.isNotEmpty()) {
                        CategoryDetailScreen(catName = catName, navController)
                    } else {
                        Toast.makeText(LocalContext.current, "Empty ID", Toast.LENGTH_SHORT).show()
                        return@composable
                    }
                }
                composable(
                    route = "DetailGameScreen/{gameId}",
                    arguments = listOf(
                        navArgument(name = "gameId") {
                            type = NavType.IntType
                        }
                    )
                ) { backStackEntry ->
                    val gameId = backStackEntry.arguments?.getInt("gameId") ?: 0
                    if (gameId == 0) {
                        Toast.makeText(LocalContext.current, "Empty ID", Toast.LENGTH_SHORT).show()
                        return@composable
                    }
//                    val viewModel: GameViewModel = hiltViewModel()
                    // Call a composable to show the detail page based on the gameId
                    // For example: DetailGameScreen(gameId)
//                    val game = gameViewModel.getGameById(gameId)
//                    val game1 = gameViewModel.getOneGameById(gameId)
                    DetailGameScreen(gameId)*/

//                }
            }
        }
    )

}

@Composable
fun CheckAuth( navController: NavHostController, firebaseAuth: FirebaseAuth) {
    if (firebaseAuth.currentUser!=null) {

        navController.navigate("home")
    } else {
        navController.navigate("login")
    }

}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigation {
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "home",
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "home") },
            label = { Text("Home") }
        )
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "singlecard",
            onClick = { navController.navigate("singlecard") },
            icon = { Icon(Icons.Default.List, contentDescription = "singleCard") },
            label = { Text("Cards") }
        )
        BottomNavigationItem(
            selected = navController.currentDestination?.route == "info",
            onClick = { navController.navigate("info") },
            icon = { Icon(Icons.Default.Info, contentDescription = "info") },
            label = { Text("Info") }
        )

    }
}
