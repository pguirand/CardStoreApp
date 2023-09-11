package com.pierretest.cardstoreapp.ui

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.pierretest.cardstoreapp.R
import com.pierretest.cardstoreapp.data.models.DataModel
import java.text.NumberFormat
import java.util.Locale


@Composable
fun SingleCardScreen(
    context : Context,
    singleCardId:Int=0,
    viewModel: CardViewModel = hiltViewModel()
) {

//    LaunchedEffect(Unit) {
//        viewModel.getRandomCard()
//    }

    val hasFetchedCard = remember { mutableStateOf(false) }

    if (!hasFetchedCard.value) {
        viewModel.getRandomCard()
        hasFetchedCard.value = true
    }

    val card by viewModel.singleCard.collectAsState()


    if (card==null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
//                    .background(Color.Red)
                    .align(Alignment.Center),
                color = Color.Red
            )
        }

//        Text(text = "No existing Card")
    } else {
        CardInfo(context = context, cardModel = card!!, onReloadClick = { viewModel.getRandomCard() })
    }

}


@Composable
fun CardInfo(context: Context,cardModel : DataModel, onReloadClick : () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${cardModel.name}",
                style = MaterialTheme.typography.headlineMedium,
//                maxLines = 2,
                textAlign = TextAlign.Center

            )
        }
        Row(
            modifier = Modifier
        ) {
            Image(
                modifier = Modifier
                    .size(250.dp)
                    .padding(2.dp),
                painter = rememberAsyncImagePainter(cardModel.cardImages?.get(0)?.imageUrl),
                contentDescription = "${cardModel.name}}"
            )
        }

        Row(
            modifier = Modifier
                .padding(2.dp),
            Arrangement.Center

        ) {
            Text(
                text = "${cardModel.desc}",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
            )
        }

        Separator1()
        Text(text = "Type : ${cardModel.type}")
        Text(text = "Race : ${cardModel.race}")

        var amazonPrice = cardModel.cardPrices?.get(0)?.amazonPrice

        var amount = amazonPrice?.toDouble()
        amount = amount?.times(10)?.let {
            val truncatedAmount = kotlin.math.truncate(it * 100) / 100
            truncatedAmount
        }
//        Text(text = "Amazon Price : $amazonPrice")

        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)
        val formattedInput = currencyFormat.format(amount)
        Text(text = "Amazon Price : $formattedInput")

        val input = amount.toString()

//        PayPalButtonWrapper()

        Button(
            onClick = { onReloadClick() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Reload", modifier = Modifier.padding(8.dp))
        }
        Button(
            onClick = {
                if (amount != null) {
                    if (amount <= 0) {
                        Toast.makeText(context, "Price should me more than 0", Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(context, PaypalActivity::class.java)
                        intent.putExtra("amount", input) //Optional parameters
                        context.startActivity(intent);
                    }
                } else {
                    Toast.makeText(context, "Price cannot be null", Toast.LENGTH_SHORT).show()
                }

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Buy",
                    modifier = Modifier.padding(8.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground) ,
                    contentDescription = "Payment Icon",
                    modifier = Modifier.size(24.dp)
                    )

            }
            
        }
        Separator1()

    }

}

@Composable
fun Separator1() {
    Spacer(modifier = Modifier.height(6.dp))
    Divider(
        modifier = Modifier
            .padding(20.dp)
            .height(2.dp)
    )
    Spacer(modifier = Modifier.height(6.dp))
}


sealed class CheckoutResult {
    data class Success(val nonce: String) : CheckoutResult()
    data class Error(val errorMessage: String) : CheckoutResult()
}

suspend fun performPayPalCheckout(amount: String, currency: String): CheckoutResult {
    val isSuccess = true

    if (isSuccess) {
        return CheckoutResult.Success("RANDOM_SUCCESSFUL_NONCE")
    } else {
        return CheckoutResult.Error("Payment failed. Please try again later.")
    }
}
@Preview
@Composable
fun CardInfoPreview() {
//    val exempleCard = DataModel()
//    CardInfo(cardModel = exempleCard) {
//
//    }
}
