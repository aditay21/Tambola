package com.aditechnology.tambola

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TicketActivity :AppCompatActivity() {
   private lateinit var recyclerView: RecyclerView
   private lateinit var screenlock :Switch
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.genrate_ticket)


        MobileAds.initialize(
            (this as Context)!!,
            OnInitializationCompleteListener { initializationStatus: InitializationStatus? -> }!!
        )
       val mAdView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


         recyclerView = findViewById(R.id.recyclerView)
        val lockStartTime :Button = findViewById(R.id.lockStartTimeButton)
        val unLockStartTime :Button = findViewById(R.id.unLockStartTimeButton)
        val addTicket :Button = findViewById(R.id.genrateTicket)
        val lockStartTimeText :TextView = findViewById(R.id.lockStartTimeText)
         screenlock = findViewById(R.id.lock_button)
        unLockStartTime.isClickable = false
        unLockStartTime.isEnabled = false

        lockStartTime.setOnClickListener {

            if (mainItems.size>0) {
                val calendar = Calendar.getInstance()
                val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
                val currentTime = timeFormat.format(calendar.time)
                lockStartTimeText.text = "Game Start Time: $currentTime"
                lockStartTime.isClickable = false
                lockStartTime.isEnabled = false
                addTicket.isClickable = false
                addTicket.isEnabled = false
                unLockStartTime.isClickable = true
                unLockStartTime.isEnabled = true
                screenlock.isChecked = true
            }else{
                Toast.makeText(applicationContext,"Please add atleast 1 Ticket",Toast.LENGTH_SHORT).show()
            }
        }
        unLockStartTime.setOnClickListener {
            lockStartTimeText.text = "Game Start Time: 00:00"
            lockStartTime.isClickable = true
            lockStartTime.isEnabled = true
            addTicket.isClickable = true
            addTicket.isEnabled = true

            unLockStartTime.isClickable = false
            unLockStartTime.isEnabled = false
        }

        addTicket.setOnClickListener {
            mainItems.clear()
            CustomDialog(this).show()
        }
    }


    override fun onResume() {
        super.onResume()



    }
    val mainItems  = mutableListOf<String>()
    fun  showG(inputText:String){


        for (i in 0 until inputText.toInt()) {
            mainItems.add("Ticket "+(i+1))
        }


        //  val mainItems = listOf("Ticket 1", "Ticket 2", "Ticket 3")
        val mainAdapter = TicketMainAdapter(mainItems)

        recyclerView.adapter = mainAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    @Override
    override fun onBackPressed() {
        if (screenlock.isChecked){
            Toast.
            makeText(applicationContext,"Screen is locked,Please unlock",Toast.LENGTH_SHORT)
                .show()
        }else {
            super.onBackPressed()
        }

    }


}



class CustomDialog(val acticity: Context) : Dialog(acticity) {

    private lateinit var editText: EditText
    private lateinit var submitButton: Button

   
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setCanceledOnTouchOutside(false)
        setContentView(R.layout.dialog_layout)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Find views
        val editText : EditText = findViewById(R.id.editText)
        val submitButton :Button = findViewById(R.id.submitButton)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    // Prevent user from entering "0" as the first character
                    if (it.isNotEmpty() && it[0] == '0') {
                        Toast.makeText(context,"Zero Not allowed",Toast.LENGTH_SHORT).show()
                        editText.setText("")
                    } }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        // Set click listener for Submit button
        submitButton.setOnClickListener {
            val inputText = editText.text.toString()
            if (inputText.toInt()>5){
                Toast.makeText(context,"Max 5 Ticket Allowed",Toast.LENGTH_SHORT).show()
                editText.setText("")
            }else {
                (acticity as TicketActivity).showG(inputText)

                dismiss()
            }
        }
    }




}

