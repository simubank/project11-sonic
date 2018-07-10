package com.td.virtualbank;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class VirtualBankGetCustomerAccountsRequest implements Response.Listener<JSONObject>, Response.ErrorListener{
    @Override
    public void onResponse(JSONObject response) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            ArrayList<VirtualBankAccount> array = new ArrayList<>();
            JSONArray bankAccArray = response.getJSONObject("result").getJSONArray("bankAccounts");
            JSONArray creditCardArray = response.getJSONObject("result").getJSONArray("creditCardAccounts");

            for (int i = 0; i < bankAccArray.length(); i++) {
                array.add(gson.fromJson(bankAccArray.getJSONObject(i).toString(),VirtualBankBankAccount.class));
            }
            for (int i = 0; i < creditCardArray.length(); i++) {
                array.add(gson.fromJson(creditCardArray.getJSONObject(i).toString(),VirtualBankCreditCardAccount.class));
            }

            onSuccess(array);
        } catch (JSONException e) {
            Log.e("VirtualBank", "No values found");
        }
    }

    @Override
    public void onErrorResponse(VolleyError error){
        onError(error);
    }

    public abstract void onSuccess(ArrayList<VirtualBankAccount> response);

    public abstract void onError(VolleyError error);
}
