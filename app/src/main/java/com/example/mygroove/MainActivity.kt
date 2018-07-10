package com.example.mygroove

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.td.virtualbank.VirtualBank

class MainActivity : AppCompatActivity() {

    companion object {
        var vb : VirtualBank = VirtualBank.getBank(authToken)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

    }
}
