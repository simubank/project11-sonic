package com.example.mygroove;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.VolleyError;
import com.td.virtualbank.VirtualBank;
import com.td.virtualbank.VirtualBankCustomer;
import com.td.virtualbank.VirtualBankGetCustomerRequest;

public class MainActivity extends AppCompatActivity {
    public VirtualBank vb = VirtualBank.getBank("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMjgxMzgyMiIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoiY2Y0MWYxNDktZTlmNC00ZWMwLTlkOTctYzA3NTNkMTBkNGZhIn0.T1_SXKfaNFUeKlkd0oWhmEOAcKm-fMw5BMZbl1w9psY");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, MainFragment.newInstance()).commit();
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selected = null;
                switch (menuItem.getItemId()) {
                    case R.id.option1:
                        selected = TransactionFragment.newInstance();
                        break;
                    case R.id.option2:
                        selected = MainFragment.newInstance();
                        break;
                    case R.id.option3:
                        selected = InvestmentFragment.newInstance();
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, selected).commit();
                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        vb.getCustomer(getBaseContext(), "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_6c8434d3-9d00-45d9-83d6-5c87cc97cdd8", new VirtualBankGetCustomerRequest() {
            @Override
            public void onSuccess(VirtualBankCustomer response) {
                Log.d("TAG", "" + response.getBirthDate());
            }

            @Override
            public void onError(VolleyError error) {
                Log.e("TAG", "Error", error);
            }
        });
    }
}
