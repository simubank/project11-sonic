package com.example.mygroove;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.td.virtualbank.VirtualBank;
import com.td.virtualbank.VirtualBankCustomer;
import com.td.virtualbank.VirtualBankGetCustomerRequest;

public class MainActivity extends AppCompatActivity {

    public static InvestmentFragment f = new InvestmentFragment();

    PreviewFragment suggestions = new PreviewFragment();
    private Context context = this;
    public static String userId = "";
    public static VirtualBank vb = VirtualBank.getBank("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMjgxMzgyMiIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoiY2Y0MWYxNDktZTlmNC00ZWMwLTlkOTctYzA3NTNkMTBkNGZhIn0.T1_SXKfaNFUeKlkd0oWhmEOAcKm-fMw5BMZbl1w9psY");
    private Toolbar toolbar;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        f.init_inv();



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, MainFragment.newInstance()).commit();
        }

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selected = null;
                switch (menuItem.getItemId()) {
                    case R.id.option1:
                        selected = SavingsFragment.newInstance(getBaseContext());
                        break;
                    case R.id.option2:
                        selected = MainFragment.newInstance();
                        break;
                    case R.id.option3:
                            selected = InvestmentFragment.newInstance();
                            //firstTime = false;

                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_layout, selected).commit();
                return true;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
//                Fragment selected = SettingsFragment.newInstance();
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.frame_layout, selected).commit();
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Title...");
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.user1:
                if (checked) {
                    Toast.makeText(this, "Selected Ren Drzazgowski", Toast.LENGTH_SHORT).show();
                    userId = "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_7a215999-9364-4df1-a80a-5e8c7ecb483a";
                }
                break;
            case R.id.user2:
                if (checked) {
                    Toast.makeText(this, "Selected Elmo Swint", Toast.LENGTH_SHORT).show();
                    userId = "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_a3a7dd7b-675b-4ff7-b037-3ebdc7de198b";
                }
                break;
            case R.id.user3:
                if (checked) {
                    Toast.makeText(this, "Selected Leam Pipes", Toast.LENGTH_SHORT).show();
                    userId = "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_bdf33543-8a53-4a3b-8ab8-2aa3d1a0b532";
                }
                break;
        }
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


    public void BtnIPClicked(View view) {
        f.BtnIPClicked(view);
    }

    public void BtnFirstNext(View view){
        f.BtnFirstNext(view, this);
    }

    //ON CLICK SHOW THE SUGGESTIONS
    public void showPrev(View view){

        f.prev = f.showPrev(view, this);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, PreviewFragment.newInstance()).commit();
        //setContentView(R.layout.fragment_preview);
        suggestions.setPreview(view, f, this);


    }

    //public void setPreview(View view){
    //    setPre
   // }


}
