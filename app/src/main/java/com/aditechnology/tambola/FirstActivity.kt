package com.aditechnology.tambola

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener

class FirstActivity :AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_first)

        MobileAds.initialize(
            (this as Context)!!,
            OnInitializationCompleteListener { initializationStatus: InitializationStatus? -> }!!
        )
        val mAdView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        val playgame: Button = findViewById<Button?>(R.id.play_coins)
        val genrateTicket: Button = findViewById<Button?>(R.id.button_ticket)
        val share_btn: Button = findViewById<Button?>(R.id.share_btn)
        playgame.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)

            // Start the new Activity
            startActivity(intent)
        }
        genrateTicket.setOnClickListener{
            val intent = Intent(this, TicketActivity::class.java)

            // Start the new Activity
            startActivity(intent)
        }
        share_btn.setOnClickListener {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Check out this app: https://play.google.com/store/apps/details?id=com.aditechnology.tambola")
                type = "text/plain"
            }

            // Start the share intent
            startActivity(Intent.createChooser(shareIntent, "Share App Via"))

        }


    }




}

