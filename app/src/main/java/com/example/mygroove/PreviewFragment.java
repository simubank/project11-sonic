package com.example.mygroove;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PreviewFragment extends Fragment {

    InvestmentFragment.InvestmentPreview pre;

    public PreviewFragment() {

        // Required empty public constructor
    }



    public static Fragment newInstance() {
        return new PreviewFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_preview, container, false);
    }

    public void setPreview(InvestmentFragment f, MainActivity main){
        pre = f.prev;

        TextView port1_head = (TextView)main.findViewById(R.id.inv1_prev_txt);
        String head = pre.portfolio(0).getName();
        port1_head.setText("p");

    }




}
