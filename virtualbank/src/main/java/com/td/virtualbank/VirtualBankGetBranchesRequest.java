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

public abstract class VirtualBankGetBranchesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    @Override
    public void onResponse(JSONObject response) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            ArrayList<VirtualBankBranch> array = new ArrayList<>();
            JSONArray jsonArray = response.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                array.add(gson.fromJson(jsonArray.getJSONObject(i).toString(),VirtualBankBranch.class));
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

    public abstract void onSuccess(ArrayList<VirtualBankBranch> response);

    public abstract void onError(VolleyError error);

}
