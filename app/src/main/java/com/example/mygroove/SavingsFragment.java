package com.example.mygroove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.example.mygroove.MainActivity;
import com.td.virtualbank.VirtualBankCustomer;
import com.td.virtualbank.VirtualBankGetCustomerRequest;
import com.td.virtualbank.VirtualBankGetCustomerTransactionsRequest;
import com.td.virtualbank.VirtualBankTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressLint("ValidFragment")
public class SavingsFragment extends Fragment {
    private Context context;
    private double monthlySpending;
    private TextView monthlyAverage;
    private ProgressBar progressBar;

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

        Log.d("TAG", "CHECK ME: " + context);
        MainActivity.vb.getCustomerTransactions(context, "cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_46ecc00a-84f5-4b64-a1fa-4354edeba8c4", new VirtualBankGetCustomerTransactionsRequest() {
            @Override
            public void onSuccess(ArrayList<VirtualBankTransaction> response) {
                HashMap<String, Double> transactions = new HashMap<>();
                for (VirtualBankTransaction virtualBankTransaction : response) {
                    Double amount = virtualBankTransaction.currencyAmount;
                    Log.d("TAG", "Amount: " + amount);
                    Double temp;
                    if (transactions.get(virtualBankTransaction.postDate.substring(0, 7)) == null)
                        transactions.put(virtualBankTransaction.postDate.substring(0, 7), amount);
                    else {
                        temp = Double.valueOf(Math.round(transactions.get(virtualBankTransaction.postDate.substring(0, 7)) + amount));
                        transactions.put(virtualBankTransaction.postDate.substring(0, 7), temp);
                    }
                }
                int count = 0;
                Double total = 0.0;
                for (Double entry : transactions.values()) {
                    count++;
                    total += entry;
                }
                updateProgressBar(total, count, transactions);
                mostRecentWantTransactions(response);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    public void mostRecentWantTransactions(ArrayList<VirtualBankTransaction> response) {
        String[] typicalWants = {"power", "energy", "condo", "enbridge", "mortgage", "savings", "overdraft", "insurance", "provident", "rent", "tax", "hyrdo", "utility", "gas"};
        String search = "keyword";
        ArrayList<String> wants = null;
        for (VirtualBankTransaction virtualBankTransaction : response) {
            String description = virtualBankTransaction.getDescription();
            String vendor = virtualBankTransaction.getMerchantName();
            for (int i = 0; i < typicalWants.length; i++) {
                if (description != null && description.toLowerCase().indexOf(typicalWants[i].toLowerCase()) != -1 || vendor != null && vendor.toLowerCase().indexOf(typicalWants[i].toLowerCase()) != -1) {
                    return;
                } else if (description != null && vendor != null) {
                    if (vendor != null) {
                        wants.add(virtualBankTransaction.getMerchantName());
                    } else {
                        wants.add(virtualBankTransaction.getDescription());
                    }
                    if (wants.size() == 5) {
                        return;
                    }
                }
            }
        }
        for (String a : wants) {
            Log.d("TAGGG", "User's wants: " + a);
        }
    }

    public void updateProgressBar(double total, double count, HashMap<String, Double> transactions) {
        monthlyAverage = getView().findViewById(R.id.monthly_average);
        progressBar = getView().findViewById(R.id.progressWheel);
        monthlySpending = Math.round(total / count);
        Log.d("TAG", "Monthly Spending: " + monthlySpending);
        monthlyAverage.setText("Monthly Average: " + monthlySpending);
        int progress = (int) ((transactions.get("2018-07") / monthlySpending) * 100);
        progressBar.setProgress(progress);
    }
}
