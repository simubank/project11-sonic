package com.example.mygroove;

import java.text.NumberFormat;

public class MoneyUtil {

    public static String format(Double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(amount);
    }
}
