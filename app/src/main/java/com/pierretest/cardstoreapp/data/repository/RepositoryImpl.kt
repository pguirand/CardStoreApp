package com.pierretest.cardstoreapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.pierretest.cardstoreapp.data.models.DataModel
import com.pierretest.cardstoreapp.data.remote.ApiCall
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImpl @Inject constructor (
    val apiCall: ApiCall,
    private val firebaseAuth: FirebaseAuth
    ) : Repository{
    override suspend fun getRandomCard(): DataModel {
        return apiCall.getRandomCard()
    }

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun getCardById(idCard: Int): DataModel {
        return apiCall.getCardById(idCard)
    }

    override suspend fun createEmailPasswordAccount(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): FirebaseUser? {
        firebaseAuth.createUserWithEmailAndPassword(email, password).await().user?.let {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName("$firstName $lastName")
//                .setPhotoUri(Uri.parse(""))
                .build()
            it.updateProfile(profileUpdates).await()
            return it
        }
        return null
    }

    override suspend fun userLogin(email: String, password: String): FirebaseUser? {
        println("Login Test $email")
        firebaseAuth.signInWithEmailAndPassword(email, password).await().user?.let {
            println("Authhh $it")
            return  it
        }
        return null
    }

    override fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser !=null
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()

    }


}