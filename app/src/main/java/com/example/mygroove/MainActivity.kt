package com.example.mygroove

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.VolleyError
import com.td.virtualbank.VirtualBank
import com.td.virtualbank.VirtualBankCustomer
import com.td.virtualbank.VirtualBankGetCustomerRequest

class MainActivity : AppCompatActivity() {

    companion object {
        var vb : VirtualBank = VirtualBank.getBank("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMjgxMzgyMiIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoiY2Y0MWYxNDktZTlmNC00ZWMwLTlkOTctYzA3NTNkMTBkNGZhIn0.T1_SXKfaNFUeKlkd0oWhmEOAcKm-fMw5BMZbl1w9psY")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    override fun onStart() {
        super.onStart()
        vb.getCustomer(baseContext, "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_6c8434d3-9d00-45d9-83d6-5c87cc97cdd8", object:VirtualBankGetCustomerRequest() {
            override fun onSuccess(response: VirtualBankCustomer?) {
                Log.d("TAG", ""+response?.givenName);
            }

            override fun onError(error: VolleyError?) {
                Log.e("TAG", "Error", error)
            }

            
        })
    }
}
