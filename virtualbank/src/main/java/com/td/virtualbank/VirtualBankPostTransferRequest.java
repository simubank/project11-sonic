package com.td.virtualbank;

import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import org.json.JSONObject;

public abstract class VirtualBankPostTransferRequest implements Listener<JSONObject>, Response.ErrorListener {

    @Override
    public void onResponse(JSONObject response) {
        onSuccess();
    }

    @Override
    public void onErrorResponse(VolleyError error){
        onError(error);
    }

    public abstract void onSuccess();

    public abstract void onError(VolleyError error);

}