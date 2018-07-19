package com.example.mygroove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.mygroove.MainActivity;
import com.td.virtualbank.VirtualBankCustomer;
import com.td.virtualbank.VirtualBankGetCustomerRequest;

@SuppressLint("ValidFragment")
public class SavingsFragment extends Fragment {
    private Context context;
    public SavingsFragment(Context context) {
        this.context = context;
        gettransactions();
    }

    public static SavingsFragment newInstance(Context context) {
        SavingsFragment fragment = new SavingsFragment(context);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_savings, container, false);
    }

    public void gettransactions() {

        Log.d("TAG", "CHECK ME: "+context);
        MainActivity.vb.getCustomer(context, "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_46ecc00a-84f5-4b64-a1fa-4354edeba8c4", new VirtualBankGetCustomerRequest() {
            @Override
            public void onSuccess(VirtualBankCustomer response) {
                Log.d("TAGG", "CHECK ME: "+response);
                Log.d("TAGG", "CHECK ME: "+response.getAge());

            }

            @Override
            public void onError(VolleyError error) {
                // handle the error
            }
        });
    }
}
