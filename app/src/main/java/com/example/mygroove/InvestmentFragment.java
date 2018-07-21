package com.example.mygroove;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;
import com.example.mygroove.MainActivity;


public class InvestmentFragment extends Fragment {

    //VIEW ARRAYS
    String[] btnISArr;
    Button[] buttonArr;
    Button btnFirstNext, btn_sumb;
    int[] biArr;


    Investment[] investments;
    Investment tempMutual, temp2, temp3, temp4, temp5;// = new Investment("Mutual Fund", 2.5,1000);
    InvestmentPreview prev;// = new InvestmentPreview(view, main);
    //
    ArrayList test = new ArrayList();
    HashMap<String,Investment> op = new HashMap<>();

    //VIEW CHARACTERISTIC VARS
    boolean isBlockedScrollView = true;

    public InvestmentFragment() {
        // Required empty public constructor
        //DECLARE VIEW

    }

    public static Fragment newInstance() {
        return new InvestmentFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_investment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance){
        //DECLARE VIEW
        //sv = (ScrollView)getActivity().findViewById(R.id.scrl);
        btnFirstNext = getActivity().findViewById(R.id.btn_next_1);
        btn_sumb = getActivity().findViewById(R.id.btn_ip_sum);

        //declare view arrays
        btnISArr = new String[12];
        buttonArr = new Button[12];
        biArr = new int[12];


        //assign view arrays
        for(int i=0;i < 12;i++){
            btnISArr[i] = "btn_ip_"+i+"";
            String s = "btn_ip_"+i+"";
            biArr[i] = getResources().getIdentifier(s, "id", "com.example.mygroove");
            buttonArr[i] = getActivity().findViewById(biArr[i]);
        }



        //next2.setEnabled(false);
        //btn_sumb.setEnabled(false);
        //buttonArr[i].getBackground().setAlpha(255);






    }


    Map<String, String> optns = new HashMap<String, String>();

    public void BtnIPClicked(View view) {
            Button btn = (Button) view;
            String btn_id = "" + btn.getId();

            if (optns.containsKey(btn_id)) {
                change_btn_size(btn, -1, 0.93);
            } else {
                change_btn_size(btn, 1, 0.93);
            }


    }

    public void change_btn_size(Button btn, int dir, double mag) {

        ViewGroup.LayoutParams params = btn.getLayoutParams();
        int init_h = params.height;
        int init_w = params.width;
        String btn_id = "" + btn.getId();
        int new_color = Color.rgb(102, 153, 0);

        if (dir > 0) {
            params.height *= mag;
            params.width *= mag;
            optns.put(btn_id, "s");
            btn.setBackgroundColor(Color.GRAY);
            btn.setTextColor(Color.BLACK);

        } else {
            params.height /= mag;
            params.width /= mag;
            optns.remove(btn_id);
            btn.setBackgroundColor(new_color);
            btn.setTextColor(Color.WHITE);

        }

        float ipy = btn.getY();
        float ipx = btn.getLeft();
        float newx = ipx + (((float) init_w * (float) (.07)) / 2) * dir;
        float newy = ipy + (((float) init_h * (float) (.07)) / 2) * dir;
        btn.setX(newx);
        btn.setY(newy);
        btn.setLayoutParams(params);

    }

    public void BtnFirstNext(View view, MainActivity main){

        ScrollView sv = (ScrollView) main.findViewById(R.id.scrl);
        View s2 = main.findViewById(R.id.ip_section2);
        sv.smoothScrollTo(0, s2.getTop());
        //q_set = 2;

        for(int i=6;i < 12;i++){
           // buttonArr[i].setEnabled(false);
            //btnFirstNext.setEnabled(false);
            //buttonArr[i].setAlpha((float)0.25);
            //btnFirstNext.setAlpha((float)0.25);

        }

    }



    //CALCULATE RISK
    public int checkRiskTolRank(View view, MainActivity main){
        SeekBar riskBar = main.findViewById(R.id.seekBar);
        int riskBarProg = riskBar.getProgress();
        int riskBarMax = riskBar.getMax();
        double riskBarVal = riskBarProg/riskBarMax;
        double riskBound1=0, riskBound2 =0.3, riskBound3 = 0.4, riskBound4=0.6, riskBound5=0.7, riskBound6=1, riskError=0.1;
        int riskBarRank;


        //GET RISK TOLERANCE
        if(riskBarVal < riskBound2){
            return 1;
        }else if(riskBarVal >= riskBound2 && riskBarVal <riskBound3){
            return 2;
        }else if(riskBarVal >= riskBound3 && riskBarVal <riskBound4){
            return 3;
        }else if(riskBarVal >= riskBound4 && riskBarVal <riskBound5){
            return 4;
        }else if(riskBarVal >= riskBound5 && riskBarVal <=riskBound6){
            return 5;
        }
        return 0;



    }

    //THE SUGGESTIONS TO BE SHOWN
    public class InvestmentPreview{

        //Portfolios to show
        public ArrayList<Portfolio> portfolios;

        //Attributes reflected in preview
        boolean riskSensitive = true;
        int riskRank;
        int termPref = 1;

        public InvestmentPreview(View view, MainActivity main){
            portfolios = new ArrayList<>();
            IPMatch(view,main);
        }


        //SUGGESTED PORTFOLIOS
        public class Portfolio{
            public Investment investment;
            public String reason;
            public Portfolio(Investment inv_, String rsn_){
                investment = inv_;
                reason =  rsn_;
            }
        }

        //METHOD TO ADD INVESTMENT TO PREVIEW
        public void add(Investment investment_, String desc){
            portfolios.add(new Portfolio(investment_, desc));
        }

        public Portfolio portfolio(int i){
            return portfolios.get(i);
        }


        //FIND THE PREVIEWS
        public void IPMatch(View view, MainActivity main){

            //risk tolerance
            int riskToleranceRank = checkRiskTolRank(view,main);


            //GET RISK TOLERANCE
            switch(riskToleranceRank){
                case 1:
                    riskRank = 1;
                    break;
                case 2:
                    if(riskSensitive)riskRank = 1;
                    else riskRank = 2;
                    break;
                case 3:
                    riskRank = 2;
                    break;
                case 4:
                    if(riskSensitive)riskRank = 2;
                    else riskRank = 3;
                    break;
                case 5:
                    riskRank =3;
                    break;
            }


            if(riskRank ==  1){
                if(termPref == 1){
                    this.add(temp2, "What is risk tolerance?\\n\\n\n" +
                            "        Your risk tolerance can be best described as how much of a risk you are willing to take to earn a return.\n" +
                            "        Not all investments can guarantee a return but usually the higher the r");
                }
                else{

                }

            }else  if(riskRank ==  2){
                if(termPref == 1){
                    this.add(temp3, "");
                }
                else{

                }

            }else  if(riskRank ==  3){
                if(termPref == 1){
                    this.add(temp4, "");
                }
                else{

                }

            }

            //this.add(tempMutual,"");

        }



    }


    //ON CLICK SHOW THE SUGGESTIONS
    public InvestmentPreview showPrev(View view, MainActivity main){

        //CREATE A PREVIEW

        prev = new InvestmentPreview(view, main);
        String name = prev.portfolio(0).investment.getName();
        //UPDATE SCREEN W/ PREVIEW
       // TextView tv = main.findViewById(R.id.textView2);
       // tv.setText(name);//(prev.portfolios.get(0).investment.getName()));
        return prev;
    }





    //INITIALIZE NECESSARY VARIABLES -- CREATE ETF DICTIONARY
    public void init_inv(){

        tempMutual = new Investment("Mutual Fund", 2.5,1000);
        temp2 = new Investment("temp2", 1.5,100);
        temp3 = new Investment("temp3", 0.5,500);
        temp4 = new Investment("temp4", 3.5,1000);
        temp5 = new Investment("temp5", 2.5,1000);

        op.put(tempMutual.getName(), tempMutual);
        op.put(temp2.getName(), tempMutual);

    }




    //DESCRIBES THE ETF -- ADD CHILD CLASSES LTR
    public class Investment{
        String name;
        double interest;
        int minDeposit;

        public Investment(){}

        public Investment(String name_, double interest_, int minDeposit_){
            name = name_;
            interest = interest_;
            minDeposit = minDeposit_;

        }

        public String getName(){
            return name;
        }

    }

    //AN INVESTMENT MADE BY USER
    public class InvestmentPortfolio{
        String name;
        double interest;
        double principal;
        double maturity;
        int maturityMonth, maturityYear, maturityDay;
        Date dom;
        Investment investment;
        int tempMat;


        public InvestmentPortfolio(Investment investment_, double principal, int tempMat_){

        }
    }



    //DESCRIBES USER INVESTMENT PROFILE --SEPRATE
    public class InvestmentProfile{

        Map<String,Investment> MyInvestments = new HashMap<>();
        int riskRank;
        int termPrefence;


    }














}
