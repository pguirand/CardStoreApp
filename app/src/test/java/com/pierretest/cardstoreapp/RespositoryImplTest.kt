package com.pierretest.cardstoreapp

import com.google.firebase.auth.FirebaseAuth
import com.pierretest.cardstoreapp.data.models.CardImageModel
import com.pierretest.cardstoreapp.data.models.CardPriceModel
import com.pierretest.cardstoreapp.data.models.CardSetModel
import com.pierretest.cardstoreapp.data.models.DataModel
import com.pierretest.cardstoreapp.data.models.SingleCardModel
import com.pierretest.cardstoreapp.data.remote.ApiCall
import com.pierretest.cardstoreapp.data.repository.Repository
import com.pierretest.cardstoreapp.data.repository.RepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RespositoryImplTest {

    lateinit var repository: Repository

    @Mock
    lateinit var apiCall: ApiCall

    @Mock
    lateinit var firebaseAuth: FirebaseAuth

    @Before
    fun startUp() {
        MockitoAnnotations.openMocks(this)
        repository = RepositoryImpl(apiCall, firebaseAuth)
    }

    @Test
    fun when_id_card_is_not_null() = runTest {
        val card = TestData.card1
        Mockito.`when`(apiCall.getRandomCard()).thenReturn(card)
        assertNotNull(repository.getRandomCard().id)
    }

    @Test
    fun when_card_is_not_null() = runTest {
        val card = TestData.card1

        Mockito.`when`(apiCall.getRandomCard()).thenReturn(card)
        val randomCard = repository.getRandomCard()

        // Assert that the returned card is not null
        assertNotNull(randomCard)
    }

    @Test
    fun when_card_match_with_random() = runTest {
        val card = TestData.card1

        Mockito.`when`(apiCall.getRandomCard()).thenReturn(card)

        val randomCard = repository.getRandomCard()

        // Assert that the returned card matches the mock data
        assertEquals(card, randomCard)
    }

    @Test
    fun when_api_call_match_repo_call() = runTest {
        Mockito.`when`(apiCall.getRandomCard()).thenReturn(TestData.card1)

        // Call the repository method multiple times
        repository.getRandomCard()
        repository.getRandomCard()
        repository.getRandomCard()

        // Verify that the apiCall.getRandomCard() method was called three times
        Mockito.verify(apiCall, Mockito.times(3)).getRandomCard()
    }


}