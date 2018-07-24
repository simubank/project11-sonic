package com.example.mygroove;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.td.virtualbank.VirtualBank;

public class MainActivity extends AppCompatActivity {

    public static InvestmentFragment f = new InvestmentFragment();
    PreviewFragment suggestions = new PreviewFragment(getBaseContext(), f);
    private Context context = this;
    public static String userId = "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_7a215999-9364-4df1-a80a-5e8c7ecb483a";
    public static VirtualBank vb = VirtualBank.getBank("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoiMjgxMzgyMiIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoiY2Y0MWYxNDktZTlmNC00ZWMwLTlkOTctYzA3NTNkMTBkNGZhIn0.T1_SXKfaNFUeKlkd0oWhmEOAcKm-fMw5BMZbl1w9psY");
    private Toolbar toolbar;
    private Dialog dialog;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        f.init_inv();




        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, MainFragment.newInstance(getBaseContext())).commit();
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
                        selected = MainFragment.newInstance(getBaseContext());
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
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog);
                dialog.setTitle("Title...");
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void dismiss(View view) {
        dialog.dismiss();
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
                    Toast.makeText(this, "Selected Thea Mccallough", Toast.LENGTH_SHORT).show();
                    userId = "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_b8fdd331-7e23-422a-8692-f4a5df6ef909";
                }
                break;
        }
    }

    public void BtnIPClicked(View view) {
        f.BtnIPClicked(view, this);
    }


    public void takeRisk(View view) {
        f.takeRisk(view);
    }

    //ON CLICK SHOW THE SUGGESTIONS
    public void showPrev(View view) {
        f.prev = f.showPrev(view, this);
        f.ask = true;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, PreviewFragment.newInstance(getBaseContext(), f)).commit();
    }

    public void showPrev() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, PreviewFragment.newInstance(getBaseContext(), f)).commit();
    }


    public void callSpecialist(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:18004655463"));
        startActivity(intent);
    }

    public void openWebsite(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.td.com/ca/products-services/investing/td-direct-investing/index-res.jsp"));
        startActivity(browserIntent);
    }



    //public void setPreview(View view){
    //    setPre
   // }


}
