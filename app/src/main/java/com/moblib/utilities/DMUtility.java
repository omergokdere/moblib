package com.moblib.utilities;

import android.util.DisplayMetrics;

import com.moblib.application.Moblib;

public class DMUtility {

    public static int DpToPx(int dp){
        DisplayMetrics dm = Moblib.getInstance().getResources().getDisplayMetrics();
        return Math.round(dp * (dm.densityDpi / 160.0F));
    }
}