package com.moblib.utilities;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class RecyclerViewScrolls extends RecyclerView.OnScrollListener {

    private static final int HOLDER_VISIBILITY = 10;
    private int preTotal = 0;
    private boolean loading = true;
    private LinearLayoutManager LLM;

    public RecyclerViewScrolls(
            LinearLayoutManager llm) {
        this.LLM = llm;
    }

    @Override
    public void onScrolled(RecyclerView RV, int x, int y) {
        super.onScrolled(RV, x, y);

        int numOfVisible = RV.getChildCount();
        int numOftotal = LLM.getItemCount();
        int numOfFVisible = LLM.findFirstVisibleItemPosition();

        if (loading) {
            if (numOftotal > preTotal) {
                loading = false;
                preTotal = numOftotal;
            }
        }
        if (!loading && (numOftotal - numOfVisible) <= (numOfFVisible + HOLDER_VISIBILITY)) {
            onLoadMore();
            loading = true;
        }
    }
    public abstract void onLoadMore();
}