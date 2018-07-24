package com.example.mygroove;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


@SuppressLint("ValidFragment")
public class PreviewFragment extends Fragment {
    private TextView sug_h0;
    private TextView sug_d0;

    private TextView sug_h1;
    private TextView sug_d1;

    private TextView sug_h2;
    private TextView sug_d2;
    InvestmentFragment p;
    private Context context;
    InvestmentFragment f;

    public PreviewFragment(Context context, InvestmentFragment f) {
        this.context = context;
        this.f = f;
        // Required empty public constructor
    }


    public static Fragment newInstance(Context context, InvestmentFragment f) {
        return new PreviewFragment(context, f);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View main = inflater.inflate(R.layout.fragment_preview, container, false);

        sug_h0 = (TextView) main.findViewById(R.id.prev_head_0);
        sug_d0 = (TextView) main.findViewById(R.id.prev_des_0);


        show();
        return main;
    }

    public void setPreview(InvestmentFragment f, MainActivity main) {


    }

    public void show() {
        p = f;
        String[] title = new String[p.prev.portfolios.size()], desc = new String[p.prev.portfolios.size()];
        for (int i = 0; i < p.prev.portfolios.size(); i++) {
            title[i] = p.prev.portfolio(i).investment.getName();
            desc[i] = p.prev.portfolio(i).reason;
        }
        sug_h0.setText(title[0]);
        sug_d0.setText(desc[0]);


    }
}
