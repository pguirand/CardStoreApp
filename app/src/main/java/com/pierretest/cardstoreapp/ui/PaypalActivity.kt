package com.pierretest.cardstoreapp.ui


import android.os.Bundle
import android.util.Log
import android.widget.Toast
//import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.paypal.checkout.approve.OnApprove
import com.paypal.checkout.cancel.OnCancel
import com.paypal.checkout.createorder.CreateOrder
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.OrderIntent
import com.paypal.checkout.createorder.UserAction
import com.paypal.checkout.error.OnError
import com.paypal.checkout.order.Amount
import com.paypal.checkout.order.AppContext
import com.paypal.checkout.order.OrderRequest
import com.paypal.checkout.order.PurchaseUnit
import com.paypal.checkout.paymentbutton.PaymentButtonContainer
import com.paypal.pyplcheckout.ui.feature.home.customviews.PayPalActionButton
import com.pierretest.cardstoreapp.R

class PaypalActivity : AppCompatActivity() {
    var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.paypal_activity)

        val amount = intent.getStringExtra("amount")
        println(amount)
//        var inputMore = input?.toInt()?.times(100)
//        val amount = inputMore.toString()
        if (amount != null) {
            // Convert the amount to the desired format or data type if needed
            // For example, if you want to convert it to an integer:
//            val amountInt = amount.toInt()

            // Now you can use the "amountInt" variable in your code
            // ...

        } else {
            // Handle the case where "amount" is null or not provided in the intent extras
            // You might want to show an error message or take appropriate action
            Toast.makeText(this, "Amount not provided in intent extras", Toast.LENGTH_SHORT).show()
        }

       val paypalButton =  findViewById<PaymentButtonContainer>(R.id.payment_button_container)



        paypalButton.setup(
            createOrder = CreateOrder { createOrderActions ->
                val order =
                    OrderRequest(
                        intent = OrderIntent.CAPTURE,
                        appContext = AppContext(userAction = UserAction.PAY_NOW),
                        purchaseUnitList =
                        listOf(
                            PurchaseUnit(
                                amount =
                                Amount(currencyCode = CurrencyCode.USD, value = amount!! )
                            )
                        )
                    )
                createOrderActions.create(order)
            },
            onApprove =
            OnApprove { approval ->
                approval.orderActions.capture { captureOrderResult ->
                    Log.i(TAG, "CaptureOrderResult: $captureOrderResult")
                    Toast.makeText(this,"Payment approved", Toast.LENGTH_SHORT).show()
                }
            },
            onCancel = OnCancel {
                Log.d(TAG, "Buyer canceled the PayPal experience.")
                Toast.makeText(this,"Payment cancelled", Toast.LENGTH_SHORT).show()
            },
            onError = OnError { errorInfo ->
                Log.d(TAG, "Error: $errorInfo")
                Toast.makeText(this,"Payment error", Toast.LENGTH_SHORT).show()
            }

        )
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}