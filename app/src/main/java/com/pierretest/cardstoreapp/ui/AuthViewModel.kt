package com.pierretest.cardstoreapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.pierretest.cardstoreapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Response<T>(
    val status:ResponseStatus,
    val message:String?=null,
    val body:T? = null
)

enum class ResponseStatus{
    SUCCESS,
    FAILURE,
    ERROR
}

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    var isLoggedIn : Boolean = false

        init {
//            createAccount(){
//                if(it.status == ResponseStatus.SUCCESS){
//
//                }else{
//
//                }
//            }
            isLoggedIn = repository.isLoggedIn()
        }
    fun getUser():FirebaseUser?=repository.currentUser

}