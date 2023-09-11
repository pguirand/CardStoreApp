package com.pierretest.cardstoreapp.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.pierretest.cardstoreapp.data.repository.Repository
import com.pierretest.cardstoreapp.navigation.navgraph.AppNavigationActions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    context: Context,
    repository: Repository,
    navController : NavHostController,
    authViewModel: AuthViewModel
) {
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = emailState,
            onValueChange = {
                emailState = it
            },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = passwordState,
            onValueChange = {
                passwordState = it
            },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                val email = emailState.trim()
                val password = passwordState
                println("Check $email")
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        context, "Email and Password are required", Toast.LENGTH_LONG)
                }

                CoroutineScope(Dispatchers.Main).launch {
                    try {
                        repository.userLogin(email, password)?.let {
                            println(it.email)
                            Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                            authViewModel.isLoggedIn = true
                            navController.navigate("home")

                        }
                    } catch (e:Exception) {
                        e.printStackTrace()
                        Toast.makeText(context, "Error Occurred", Toast.LENGTH_LONG).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun MyApp() {
    // Your app's content

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    // Preview the login screen
    MyApp()
}