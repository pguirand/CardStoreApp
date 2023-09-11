package com.pierretest.cardstoreapp.ui

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){
// You can define your data fields and business logic here

    fun login(email: String, password: String) {
        // Implement your login logic here, e.g., make an API request
        // and handle the response or perform any necessary authentication.
        // You can also use LiveData or State to update the UI based on login status.
        // For simplicity, we'll just print the login credentials here.

        println("Email: $email, Password: $password")
    }

}