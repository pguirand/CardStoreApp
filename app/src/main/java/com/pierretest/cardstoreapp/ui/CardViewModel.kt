package com.pierretest.cardstoreapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pierretest.cardstoreapp.data.models.DataModel
import com.pierretest.cardstoreapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(private val repository: Repository) : ViewModel(){

    private val _singleCard = MutableStateFlow<DataModel?>(null)

    val singleCard : StateFlow<DataModel?> = _singleCard

    fun getRandomCard() {
        viewModelScope.launch {
            try {
                val card = repository.getRandomCard()
                _singleCard.value = card
            } catch (e : Exception) {
                Log.e("ERROR", e.message?: "Unexpected Error")
            }
        }
    }
}