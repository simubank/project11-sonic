package com.example.mygroove;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.td.virtualbank.VirtualBankAccount;
import com.td.virtualbank.VirtualBankCreditCardAccount;
import com.td.virtualbank.VirtualBankGetCustomerAccountsRequest;
import com.td.virtualbank.VirtualBankGetCustomerCreditCardAccountsRequest;
import com.td.virtualbank.VirtualBankGetCustomerTransactionsRequest;
import com.td.virtualbank.VirtualBankTransaction;

import java.util.ArrayList;
import java.util.HashMap;

@SuppressLint("ValidFragment")
public class MainFragment extends Fragment {

    private Context context;
    private TextView name;
    private TextView balance;
    private TextView transaction1;
    private TextView transaction2;
    private TextView transaction3;
    private TextView transaction4;
    private TextView transaction5;
    private String[] wants1 = new String[5];
    private String[] wants2 = new String[5];

    public MainFragment(Context context) {
        this.context = context;
    }

    public static Fragment newInstance(Context context) {
        return new MainFragment(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        requireActivity().setTitle(R.string.fragment_home_title);
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        name = view.findViewById(R.id.name1);
        balance = view.findViewById(R.id.balance1);
        transaction1 = view.findViewById(R.id.transaction1);
        transaction2 = view.findViewById(R.id.transaction2);
        transaction3 = view.findViewById(R.id.transaction3);
        transaction4 = view.findViewById(R.id.transaction4);
        transaction5 = view.findViewById(R.id.transaction5);
        getUserData();
        return view;
    }

    public void getUserData() {
        if (MainActivity.userId.equals("cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_7a215999-9364-4df1-a80a-5e8c7ecb483a")) {
            name.setText("Ren Drzazgowski");
        } else {
            name.setText("Thea Mccallough");
        }

        MainActivity.vb.getCustomerAccounts(context, MainActivity.userId, new VirtualBankGetCustomerAccountsRequest() {
            @Override
            public void onSuccess(ArrayList<VirtualBankAccount> response) {
                Log.d("TAG", String.valueOf(response.get(0).balance));
                balance.setText("Balance: " + MoneyUtil.format(response.get(0).balance));
            }

            @Override
            public void onError(VolleyError error) {

            }
        });

        gettransactions();
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
                        temp = (double) Math.round(transactions.get(virtualBankTransaction.postDate.substring(0, 7)) + amount);
                        transactions.put(virtualBankTransaction.postDate.substring(0, 7), temp);
                    }
                }
                int count = 0;
                Double total = 0.0;
                for (Double entry : transactions.values()) {
                    count++;
                    total += entry;
                }
                mostRecentWantTransactions(response);
//                if (wants == null) {
//                    Log.d("TAG", "Empty Wants");
//                }
//                if (wants != null) {
//                    for (VirtualBankTransaction a : wants) {
//                        Log.d("TAGGG", "User's wants: " + a.getMerchantName());
//                    }
//                }
            }

            @Override
            public void onError(VolleyError error) {

            }
        });
    }

    public void mostRecentWantTransactions(ArrayList<VirtualBankTransaction> response) {
        Log.d("TAG", "METHOD RUN");
        String[] typicalwants = {"fast Food", "fashion", "transportation", "groceries", "dining"};
        ArrayList<VirtualBankTransaction> wants = new ArrayList<>();
        wants.clear();
        for (VirtualBankTransaction virtualBankTransaction : response) {
            String categoryTag = virtualBankTransaction.getCategoryTags().get(0);
            for (int i = 0; i < typicalwants.length; i++) {
                if (categoryTag != null && categoryTag.toLowerCase().equals(typicalwants[i])) {
                    wants.add(virtualBankTransaction);
                    if (wants.size() == 5) {
                        if (MainActivity.userId.equals("cf41f149-e9f4-4ec0-9d97-c0753d10d4fa_7a215999-9364-4df1-a80a-5e8c7ecb483a")) {
                            if (wants1[0] == null) {
                                for (int j = 0; j < 5; j++) {
                                    wants1[j] = wants.get(j).getMerchantName() + ": $" + wants.get(j).currencyAmount;
                                }
                            }
                            transaction1.setText(wants1[0]);
                            transaction2.setText(wants1[1]);
                            transaction3.setText(wants1[2]);
                            transaction4.setText(wants1[3]);
                            transaction5.setText(wants1[4]);
                        } else {
                            if (wants2[0] == null) {
                                for (int j = 0; j < 5; j++) {
                                    wants2[j] = wants.get(j).getMerchantName() + ": $" + wants.get(j).currencyAmount;
                                }
                            }
                            transaction1.setText(wants1[0]);
                            transaction2.setText(wants1[1]);
                            transaction3.setText(wants1[2]);
                            transaction4.setText(wants1[3]);
                            transaction5.setText(wants1[4]);
                        }


                    }
                }
            }
        }
        Log.d("TAGG", "Wants size" + wants.size());
    }
}
