package com.moblib.activities.others;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.moblib.R;

public class ToolbarActivity extends AppCompatActivity {

    public void setToolbarName(String ToolbarName) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (!TextUtils.isEmpty(ToolbarName)) {
                actionBar.setTitle(ToolbarName);
            }
        }

      }

    public void displayHomeAsUpEnabled(boolean show){
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show);
        }
    }

    public void replaceFragment(@NonNull Fragment fragment, int containerId, boolean addToBackStack, String tag, boolean animated) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (animated) {
            ft.setCustomAnimations(R.anim.slide_out_from_right, R.anim.slide_out_from_left, R.anim.slide_in_from_left, R.anim.slide_in_from_right);
        }
        ft.replace(containerId, fragment,tag);
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }
}
