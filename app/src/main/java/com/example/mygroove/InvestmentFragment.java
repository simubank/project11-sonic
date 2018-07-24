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
    Button btnFirstNext, btn_sumb, safebtn, riskbtn;
    int[] biArr;
    MainActivity main_;

    int riskSelected = -1;
    boolean ask = false;
    int term = 2;

    Investment[] investments;
    Investment tempMutual, temp2, temp3, temp4, temp5;// = new Investment("Mutual Fund", 2.5,1000);
    Investment TDCMFF, TDDGF, TDSTBF, EM;

    String TDCMFF_L = "";
    String TDDGF_L = "";
    String TDSTBF_L = "";
    String EM_L = "";
    String TDCMFF_S = "•\tThe fundamental investment objective is to earn a high rate of interest income and at the same time to preserve capital and maintain liquidity by investing primarily in high-quality money market securities, generally maturing in not more than one year.\n";
    String TDDGF_S = "•\tThe fundamental investment objective is to provide high level of after-tax income and steady growth by investing primarily in high-quality, high- yield equity securities and other income-producing instruments of Canadian issuers.\n";
    String TDSTBF_S = "•\tThe fundamental investment objective is to maximize income while simultaneously preserving investment capital and liquidity by investing primarily in debt obligations issued or guaranteed by the Canadian federal or provincial governments or any agency of such governments, as well as debt obligations of Schedule I Canadian chartered banks, debt obligations of loan or trust companies and debt obligations of corporations. In each case such obligations may have a term to maturity of up to five years.\n";
    String EM_S = "";
    InvestmentPreview prev;// = new InvestmentPreview(view, main);
    //
    ArrayList test = new ArrayList();
    ArrayList<String> riskType = new ArrayList<>();
    HashMap<String, Investment> op = new HashMap<>();

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
        requireActivity().setTitle(R.string.fragmnet_investments_title);
        return inflater.inflate(R.layout.fragment_investment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstance) {
        // if(main_.f.ask) main_.showPrev();
        btn_sumb = getActivity().findViewById(R.id.btn_ip_sum);

        //declare view arrays
        btnISArr = new String[12];
        buttonArr = new Button[12];
        biArr = new int[12];


        //safebtn = getActivity().findViewById(R.id.safe_btn);
        //riskbtn = getActivity().findViewById(R.id.risk_btn);


        //assign view arrays
        for (int i = 0; i < 12; i++) {
            btnISArr[i] = "btn_ip_" + i + "";
            String s = "btn_ip_" + i + "";
            biArr[i] = getResources().getIdentifier(s, "id", "com.example.mygroove");
            buttonArr[i] = getActivity().findViewById(biArr[i]);
        }


        //next2.setEnabled(false);
        //btn_sumb.setEnabled(false);
        //buttonArr[i].getBackground().setAlpha(255);


    }


    Map<String, String> optns = new HashMap<String, String>();

    public void BtnIPClicked(View view, MainActivity main) {
        Button btn = (Button) view;
        String btn_id = "" + btn.getId();

        // if btn = (Button) getActivity().findViewById()

      /*  if(btn_id == "btn_ip_11"){
            for(int i=0;i < 12;i++){
                if (optns.containsKey(""+buttonArr[11].getId())) {
                    change_btn_size(btn, -1, 0.93);
                }
            }

        }else {*/


        if (btn == main.findViewById(R.id.safe_btn)) {
            if (optns.containsKey(btn_id)) {
                change_btn_size(btn, -1, 0.93);
                riskSelected = -1;

            } else {
                change_btn_size(btn, 1, 0.93);
                riskSelected = 0;
            }
        } else if (btn == main.findViewById(R.id.risk_btn)) {
            if (optns.containsKey(btn_id)) {
                change_btn_size(btn, -1, 0.93);
                riskSelected = -1;

            } else {
                change_btn_size(btn, 1, 0.93);
                riskSelected = 1;
            }
            // }
        } else {
            if (optns.containsKey(btn_id)) {
                change_btn_size(btn, -1, 0.93);
                if (btn == main.findViewById(R.id.btn_ip_11)) term = 2;

            } else {
                change_btn_size(btn, 1, 0.93);
                if (btn == main.findViewById(R.id.btn_ip_11)) term = 1;
            }
        }


    }

    public void takeSafe(View view) {
        // riskSelected = 0;

        Button btn = (Button) view;
        String btn_id = "" + btn.getId();
        if (optns.containsKey(btn_id)) {
            change_btn_size(btn, 2, 0.98);

        } else {
            change_btn_size(btn, 0, 0.98);
        }
        // }

    }

    public void takeRisk(View view) {
        riskSelected = 1;
    }

    public void change_btn_size2(Button btn, int dir, double mag) {

        ViewGroup.LayoutParams params = btn.getLayoutParams();
        int init_h = params.height;
        int init_w = params.width;
        String btn_id = "" + btn.getId();
        int new_color = Color.rgb(102, 153, 0);

        if (dir == 0) {
            btn.setBackgroundColor(Color.GRAY);
            btn.setTextColor(Color.BLACK);
        } else if (dir == 2) {
            btn.setBackgroundColor(new_color);
            btn.setTextColor(Color.WHITE);
        } else if (dir > 0) {
            params.height *= mag;
            params.width *= mag;
            btn.setBackgroundColor(Color.GRAY);
            btn.setTextColor(Color.BLACK);

        } else {
            params.height /= mag;
            params.width /= mag;
            btn.setBackgroundColor(new_color);
            btn.setTextColor(Color.WHITE);

        }

        if (dir != 0) {
            if (dir != 2) {
                float ipy = btn.getY();
                float ipx = btn.getLeft();
                float newx = ipx + (((float) init_w * (float) ((1 - mag))) / 2) * dir;
                float newy = ipy + (((float) init_h * (float) ((1 - mag))) / 2) * dir;
                btn.setX(newx);
                btn.setY(newy);
                btn.setLayoutParams(params);
            }
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


    //CALCULATE RISK
    public int checkRiskTolRank(View view, MainActivity main) {
        SeekBar riskBar = main.findViewById(R.id.seekBar);
        int riskBarProg = riskBar.getProgress();
        int riskBarMax = riskBar.getMax();
        double riskBarVal = riskBarProg / riskBarMax;
        double riskBound1 = 0, riskBound2 = 0.3, riskBound3 = 0.4, riskBound4 = 0.6, riskBound5 = 0.7, riskBound6 = 1, riskError = 0.1;
        int riskBarRank;


        //GET RISK TOLERANCE
        if (riskBarVal < riskBound2) {
            return 1;
        } else if (riskBarVal >= riskBound2 && riskBarVal < riskBound3) {
            return 2;
        } else if (riskBarVal >= riskBound3 && riskBarVal < riskBound4) {
            return 3;
        } else if (riskBarVal >= riskBound4 && riskBarVal < riskBound5) {
            return 4;
        } else if (riskBarVal >= riskBound5 && riskBarVal <= riskBound6) {
            return 5;
        }
        return 0;


    }

    //THE SUGGESTIONS TO BE SHOWN
    public class InvestmentPreview {

        //Portfolios to show
        public ArrayList<Portfolio> portfolios;

        //Attributes reflected in preview
        boolean riskSensitive = true;
        int riskRank;
        int termPref = 1;

        public InvestmentPreview(View view, MainActivity main) {
            portfolios = new ArrayList<>();
            IPMatch(view, main);
        }


        //SUGGESTED PORTFOLIOS
        public class Portfolio {
            public Investment investment;
            public String reason;
            public String learn;

            public Portfolio(Investment inv_, String lrn_, String rsn_) {
                investment = inv_;
                reason = rsn_;
                learn = lrn_;
            }
        }

        //METHOD TO ADD INVESTMENT TO PREVIEW
        public void add(Investment investment_, String learn, String desc) {
            portfolios.add(new Portfolio(investment_, learn, desc));
        }

        public Portfolio portfolio(int i) {
            return portfolios.get(i);
        }


        //FIND THE PREVIEWS
        public void IPMatch(View view, MainActivity main) {

            //risk tolerance
            int riskToleranceRank = checkRiskTolRank(view, main);

            if (riskSelected == 1) riskSensitive = false;
            else if (riskSelected == 0) riskSensitive = true;
            else riskSensitive = true;

            main_ = main;
            termPref = term;

            Button none = (Button) main.findViewById(R.id.btn_ip_11);
            String none_s = "" + none.getId();

            if (optns.containsKey(none_s)) termPref = 1;

            //GET RISK TOLERANCE
            switch (riskToleranceRank) {
                case 1:
                    riskRank = 1;
                    break;
                case 2:
                    if (riskSensitive) riskRank = 1;
                    else riskRank = 2;
                    break;
                case 3:
                    riskRank = 2;
                    break;
                case 4:
                    if (riskSensitive) riskRank = 2;
                    else riskRank = 3;
                    break;
                case 5:
                    riskRank = 3;
                    break;
            }

            if (riskRank == 1) {
                if (termPref == 1) {
                    this.add(TDCMFF, TDCMFF_L, TDCMFF_S);
                } else {
                    this.add(TDCMFF, TDCMFF_L, TDCMFF_S);
                }

            } else if (riskRank == 2) {
                if (termPref == 1) {
                    this.add(TDSTBF, TDSTBF_L, TDSTBF_S);
                } else {
                    this.add(TDDGF, TDDGF_L, TDDGF_S);

                }

            } else if (riskRank == 3) {
                if (termPref == 1) {
                    this.add(TDDGF, TDDGF_L, TDDGF_S);
                } else {
                    this.add(TDDGF, TDDGF_L, TDDGF_S);

                }

            }

            if (riskRank == 1) {
                if (termPref == 2) {
                    this.add(TDCMFF, TDCMFF_L, TDCMFF_S);
                } else {
                    this.add(TDCMFF, TDCMFF_L, TDCMFF_S);
                }

            } else if (riskRank == 2) {
                if (termPref == 2) {
                    this.add(TDDGF, TDDGF_L, TDDGF_S);
                } else {
                    this.add(TDDGF, TDDGF_L, TDDGF_S);

                }

            } else if (riskRank == 3) {
                if (termPref == 2) {
                    this.add(EM, EM_L, EM_S);
                } else {
                    this.add(EM, EM_L, EM_S);

                }

            }


            //this.add(tempMutual,"");

        }


    }


    //ON CLICK SHOW THE SUGGESTIONS
    public InvestmentPreview showPrev(View view, MainActivity main) {

        //CREATE A PREVIEW

        prev = new InvestmentPreview(view, main);
        String name = prev.portfolio(0).investment.getName();
        //UPDATE SCREEN W/ PREVIEW
        return prev;
    }


    //INITIALIZE NECESSARY VARIABLES -- CREATE ETF DICTIONARY
    public void init_inv() {
        TDCMFF = new Investment("TD Canadian Money Market Fund 1 ", -1, 100);
        TDDGF = new Investment("TD Dividend Growth Fund 1", 1.5, 100);
        TDSTBF = new Investment("TD short Term Bond Fund 1", 0.5, 100);
        EM = new Investment("EQUITY MARKET", -1, 0);


        op.put(TDCMFF.getName(), TDCMFF);
        op.put(TDDGF.getName(), TDDGF);
        op.put(TDSTBF.getName(), TDSTBF);
        op.put(EM.getName(), EM);

    }


    //DESCRIBES THE ETF -- ADD CHILD CLASSES LTR
    public class Investment {
        String name;
        double interest;
        int minDeposit;

        public Investment() {
        }

        public Investment(String name_, double interest_, int minDeposit_) {
            name = name_;
            interest = interest_;
            minDeposit = minDeposit_;

        }

        public String getName() {
            return name;
        }

    }

    //AN INVESTMENT MADE BY USER
    public class InvestmentPortfolio {
        String name;
        double interest;
        double principal;
        double maturity;
        int maturityMonth, maturityYear, maturityDay;
        Date dom;
        Investment investment;
        int tempMat;


        public InvestmentPortfolio(Investment investment_, double principal, int tempMat_) {

        }
    }


    //DESCRIBES USER INVESTMENT PROFILE --SEPRATE
    public class InvestmentProfile {

        Map<String, Investment> MyInvestments = new HashMap<>();
        public ArrayList<Investment> selectedInvestments = new ArrayList<>();
        int riskRank;
        int termPrefence;


        public void add(Investment inv) {
            selectedInvestments.add(inv);
        }

    }


}
