package com.moblib.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.moblib.utilities.DMUtility;

public class SpaceTool extends RecyclerView.ItemDecoration {

    private int DpSpace;

    public SpaceTool(int spacingInDp){
        this.DpSpace = DMUtility.DpToPx(spacingInDp);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int currentPosition = parent.getChildLayoutPosition(view);
        int lastPosition = state.getItemCount()-1;
        if(currentPosition == lastPosition){
            outRect.bottom = 0;
        }else{
            outRect.bottom = DpSpace;
        }
    }
}
