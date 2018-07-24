package com.example.mygroove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import com.td.virtualbank.VirtualBank;
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
    TextView average;
    private ProgressBar progressBar;
    TextView percent1;
    public static ArrayList<VirtualBankTransaction> wants = new ArrayList<>();
    private ArrayList<VirtualBankTransaction> needs = new ArrayList<>();


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
        requireActivity().setTitle(R.string.fragment_savings_title);
        return inflater.inflate(R.layout.fragment_savings, container, false);
    }

    public void getNeeds() {
        MainActivity.vb.getCustomerTransactions(context, MainActivity.userId, new VirtualBankGetCustomerTransactionsRequest() {
            @Override
            public void onSuccess(ArrayList<VirtualBankTransaction> response) {
                HashMap<String, Double> transactions = new HashMap<>();

            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    public void gettransactions() {
        MainActivity.vb.getCustomerTransactions(context, MainActivity.userId, new VirtualBankGetCustomerTransactionsRequest() {
            @Override
            public void onSuccess(ArrayList<VirtualBankTransaction> response) {
                HashMap<String, Double> transactions = new HashMap<>();
                for (VirtualBankTransaction virtualBankTransaction : response) {
                    Double amount = virtualBankTransaction.currencyAmount;
                    amount = Math.abs(amount);
                    if (!virtualBankTransaction.getType().equals("CreditCardTransaction"))
                        continue;
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
                if (wants == null) {
                    Log.d("TAG", "Empty Wants");
                }
                if (wants != null) {
                    for (VirtualBankTransaction a : wants) {
                        Log.d("TAGGG", "User's wants: " + a.getMerchantName());
                    }
                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    public void mostRecentNeedTransactions(ArrayList<VirtualBankTransaction> response) {
        String[] typicalNeeds = {"fees", "insurance", "mortgage", "tax", "bill", "rent", "cheque"};
        for (VirtualBankTransaction virtualBankTransaction : response) {
            String categoryTag = virtualBankTransaction.getCategoryTags().get(0);
            for (int i = 0; i < typicalNeeds.length; i++) {
                if (categoryTag != null && categoryTag.toLowerCase().equals(typicalNeeds[i])) {
                    needs.add(virtualBankTransaction);
                    if (needs.size() == 5) {
                        return;
                    }
                }
            }
        }
    }

    public void mostRecentWantTransactions(ArrayList<VirtualBankTransaction> response) {
//        String[] typicalWants = {"power", "energy", "condo", "enbridge", "mortgage", "savings", "Micro", "Enbridge", "Rent", "overdraft", "insurance", "provident", "rent", "tax", "hyrdo", "utility", "gas"};
        String[] typicalwants = {"fast Food", "fashion", "transportation", "groceries", "dining"};
        for (VirtualBankTransaction virtualBankTransaction : response) {
            String categoryTag = virtualBankTransaction.getCategoryTags().get(0);
            for (int i = 0; i < typicalwants.length; i++) {
                if (categoryTag != null && categoryTag.toLowerCase().equals(typicalwants[i])) {
                    wants.add(virtualBankTransaction);
                    if (wants.size() == 5) {
                        return;
                    }
                }
            }
        }
    }

    public void updateProgressBar(double total, double count, HashMap<
            String, Double> transactions) {
        monthlyAverage = getView().findViewById(R.id.monthly_average);
        average = getView().findViewById(R.id.average);
        progressBar = getView().findViewById(R.id.progressWheel);
        monthlySpending = Math.round(total / count)*1.1;
        percent1 = getView().findViewById(R.id.percent1);
        Log.d("TAG", "Monthly Spending: " + monthlySpending);
        monthlyAverage.setText("Monthly Average: " + MoneyUtil.format(monthlySpending));
        double progress;
        double thisMonth;
        if (transactions.get("2018-08") != null) {
            thisMonth = transactions.get("2018-08");
        } else
            thisMonth = transactions.get("2018-07");
        progress = (thisMonth / monthlySpending) * 100;
        progressBar.setProgress((int) progress);
        average.setText("This Month's Spending: " + MoneyUtil.format(thisMonth));
        if (progress > 100) {
//            progressBar.getIndeterminateDrawable().setColorFilter(Color.RED,android.graphics.PorterDuff.Mode.MULTIPLY);
            progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        }
        percent1.setText((int) progress + "%");
    }
}
