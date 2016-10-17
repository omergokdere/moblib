package com.moblib.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.moblib.R;

public class ProgressBarTool extends RecyclerView.ViewHolder {

    private ProgressBar pb;
    public ProgressBarTool(View itemView) {
        super(itemView);
        pb = (ProgressBar)itemView.findViewById(R.id.progressBar);
    }

    public ProgressBar getProgressBar() {
        return pb;
    }
}
