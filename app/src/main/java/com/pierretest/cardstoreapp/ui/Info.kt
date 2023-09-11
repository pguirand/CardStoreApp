package com.pierretest.cardstoreapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pierretest.cardstoreapp.ui.theme.CardStoreAppTheme


@Composable
fun CardInfo(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to Yu-Gi-Oh! Shopping Card",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Yu-Gi-Oh! is a popular trading card game (TCG) that originated from the manga and anime series created by Kazuki Takahashi. " +
                    "The card game has become a global phenomenon, with millions of players and collectors around the world.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                // You can add any action here
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text(text = "Perform Action")
        }
    }
}

@Composable
fun InfoPreview() {
    CardStoreAppTheme {
        CardInfo(navController = rememberNavController())
    }
}