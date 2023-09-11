package com.pierretest.cardstoreapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.pierretest.cardstoreapp.R
import com.pierretest.cardstoreapp.ui.theme.CardStoreAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(
    navController: NavController,
    firebaseAuth: FirebaseAuth,
    context : Context
) {
    val currentUserMail = firebaseAuth.currentUser?.email
//    val userFirstName = currentUser?.displayName?.split(" ")?.get(0) // Extract first name
//    val userLastName = currentUser?.displayName?.split(" ")?.get(1) // Extract last name
    val user = "$currentUserMail" // Replace with the actual user's name
    val description = "Welcome to YugiOh Shopping Card! Explore and shop for your favorite Yu-Gi-Oh! trading cards." +
            "Yu-Gi-Oh! is a popular trading card game (TCG) that originated from the manga and anime series created " +
            "by Kazuki Takahashi. The card game has become a global phenomenon, with millions of players and collectors " +
            "around the world. Here's a detailed description of the Yu-Gi-Oh! card game:"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(text = "YugiOh Shopping Card",
                        )
                    }

                }
            )
        },
        content = {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    // User Information Section
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // User Avatar (You can replace this with the user's actual avatar)
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(16.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // User Name
                        Text(
                            text = "Hello, $user!",
                            textAlign = TextAlign.Justify,
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                item {
                    // App Description Section
                    Column {
                        // App Description Text
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                item {
                    // Shop Now Button
                    Button(
                        onClick = {
                            firebaseAuth.signOut()
                            Toast.makeText(context, "Logged Out Successfully", Toast.LENGTH_LONG).show()
                            navController.navigate("login")


                                  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(text = "Sign Out")
                    }
                }
            }
        }
    )
}

//@Preview
//@Composable
//fun HomePagePreview() {
//    lateinit var firebaseAuth : FirebaseAuth
//    CardStoreAppTheme {
//        HomePage(navController = rememberNavController(), firebaseAuth)
//    }
//}