package com.example.mygroove;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class PreviewFragment extends Fragment {

    InvestmentFragment p;
    //MainActivity main;

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



    public void setPreview(View view, InvestmentFragment f, MainActivity main){
        p = f;
        //main.setContentView(R.layout.fragment_preview);

       // tv2.setText(desc);
    }

    public void show(View view, MainActivity main, InvestmentFragment f){
        p = f;
        TextView tv = (TextView) main.findViewById(R.id.inv1text);
        TextView tv2 = (TextView) main.findViewById(R.id.inv1_prev_d);
        String head = p.prev.portfolio(0).investment.getName();
        String desc = p.prev.portfolio(0).reason;
        tv.setText(head);
        tv2.setText(desc);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstance){
        // Inflate the layout for this fragment




    }






}
