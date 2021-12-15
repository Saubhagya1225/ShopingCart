package com.shoping.shopingcart

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_confirmation_page.*


class ConfirmationPage : AppCompatActivity() {
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_page)
        progressBar = findViewById(R.id.confirmProgress)
        progressBar.visibility = View.VISIBLE

        btnContinueShopping.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }


        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                //here you can have your logic
            }

            override fun onFinish() {
                tickImg.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                btnContinueShopping.visibility = View.VISIBLE
                congratulationText.visibility = View.VISIBLE
                placedText.visibility = View.VISIBLE

            }
        }.start()


    }
}