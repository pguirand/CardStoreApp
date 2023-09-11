package com.pierretest.cardstoreapp.data.repository

import com.google.firebase.auth.FirebaseUser
import com.pierretest.cardstoreapp.data.models.DataModel

interface Repository {

    suspend fun getRandomCard() : DataModel

    suspend fun getCardById(idCard : Int) : DataModel

    val currentUser: FirebaseUser?

    suspend fun createEmailPasswordAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser?

    suspend fun userLogin(email : String, password : String) : FirebaseUser?

    fun isLoggedIn() : Boolean

    suspend fun signOut()

}