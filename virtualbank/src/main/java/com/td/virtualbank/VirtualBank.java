package com.td.virtualbank;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VirtualBank {

    private String auth = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDQlAiLCJ0ZWFtX2lkIjoidGVzdFRlYW1JZCIsImV4cCI6OTIyMzM3MjAzNjg1NDc3NSwiYXBwX2lkIjoidGVzdEFwcElkIn0.o53TISzOUq0T1dc96IiEc8rreexYLCrdI3HunhtPvJ4";
    private String baseURL = "https://dev.botsfinancial.com/api/";
    private  static VirtualBank vb = null;

    //account id
    //7244c741-cb43-40b9-9729-57a05b170222
    private VirtualBank(String auth) {
        this.auth = auth;
    }

    public static VirtualBank getBank(String auth) {
        if (vb == null) {
            vb = new VirtualBank(auth);
        }
        return vb;
    }

    public static VirtualBank getBank() {
        if (vb == null) {
            //ERROR
        }
        return vb;
    }

    public void getCustomer(Context context, String id, VirtualBankGetCustomerRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "customers/" + id;
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getBankAccount(Context context, String id, VirtualBankGetBankAccountRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "accounts/" + id;
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getCreditCardAccount(Context context, String id, VirtualBankGetCreditCardAccountRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "accounts/" + id;
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
    }

    public void getTransactions(Context context, String id, VirtualBankGetTransactionsRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "accounts/" + id + "/transactions";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void postTransfer(Context context, String fromAccountId, String toAccountId, Double amount, String currency, VirtualBankPostTransferRequest VBResponse) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "transfers";
        JSONObject body = new JSONObject();
        body.put("amount", amount);
        body.put("currency", currency);
        body.put("fromAccountID", fromAccountId);
        body.put("toAccountID",toAccountId);
        body.put("receipt", "string");
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url,body,VBResponse,VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getCustomerCreditCardAccounts(Context context, String CustomerId, VirtualBankGetCustomerCreditCardAccountsRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "customers/" + CustomerId + "/accounts";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getCustomerBankAccounts(Context context, String CustomerId, VirtualBankGetCustomerBankAccountsRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "customers/" + CustomerId + "/accounts";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getCustomerAccounts(Context context, String CustomerId, VirtualBankGetCustomerAccountsRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "customers/" + CustomerId + "/accounts";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getTransfer(Context context, String id, VirtualBankGetTransferRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "transfers/" + id;
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getBranch(Context context, String id, VirtualBankGetBranchRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "branches/" + id;
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getBranches(Context context, VirtualBankGetBranchesRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "branches/";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getCustomerTransactions(Context context, String id, VirtualBankGetCustomerTransactionsRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "customers/" + id + "/transactions";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getTransaction(Context context, String id, VirtualBankGetTransactionRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "transactions/" + id;
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void getAppAccount(Context context, VirtualBankGetAppAccountRequest VBResponse) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "accounts/self";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void patchAppAccount(Context context, Double balance, VirtualBankPatchAppAccountRequest VBResponse) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "accounts/self";
        JSONObject json = new JSONObject();
        json.put("balance", balance);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PATCH, url, json, VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }

    public void putTransactionTags(Context context, String id, String[] tag, VirtualBankPutTransactionTagsRequest VBResponse) throws JSONException {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL + "transactions/" + id + "/tags";
        Gson gson = new Gson();

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, url, new JSONObject(String.valueOf(new JSONArray(tag))), VBResponse, VBResponse) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonRequest);
    }
}