package com.pierretest.cardstoreapp

import android.app.Application
import com.paypal.checkout.PayPalCheckout
import com.paypal.checkout.config.CheckoutConfig
import com.paypal.checkout.config.Environment
import com.paypal.checkout.config.SettingsConfig
import com.paypal.checkout.createorder.CurrencyCode
import com.paypal.checkout.createorder.UserAction
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CardShopApp : Application() {
    var clientId = "AXIDIQMNzJB-ePCyUgbV6edOFrBg-mVRIqDHPhqk-6GEgxesw_YMRmxBy3lK1JWfR_2HfJ9ctt-ARwRC"
    var returnUrl = "com.pierretest.cardstoreapp://paypalpay"

    override fun onCreate() {
        super.onCreate()
        val config = CheckoutConfig(
            application = this,
            clientId = clientId,
            environment = Environment.SANDBOX,
            returnUrl = returnUrl,
            currencyCode = CurrencyCode.USD,
            userAction = UserAction.PAY_NOW,
            settingsConfig = SettingsConfig(
                loggingEnabled = true
            )
        )
        PayPalCheckout.setConfig(config)
    }
}