package com.moblib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.moblib.R;

public class PlaceHolder extends LinearLayout implements View.OnClickListener {

    private View viewLoading, viewFailLoading;
    private TextView msgLoading, msgFailLoading;
    private Button tryAgain;
    private OntryAgainClicked tryAgainClicked;

    public interface OntryAgainClicked {
        void OntryAgainClicked();
    }
    public PlaceHolder(Context context) {
        super(context);
        init(context);
    }

    public PlaceHolder(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PlaceHolder(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setOntryAgainClicked(OntryAgainClicked OntryAgainClicked) {
        this.tryAgainClicked = OntryAgainClicked;
    }

    private void init(Context context){
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.setOrientation(LinearLayout.VERTICAL);
        this.setGravity(Gravity.CENTER);

        viewLoading = inflate(context, R.layout.searching_ph, null);
        viewFailLoading = inflate(context, R.layout.connection_ph, null);
        msgLoading = (TextView)viewLoading.findViewById(R.id.loadingMessage);
        msgFailLoading = (TextView)viewFailLoading.findViewById(R.id.failedMessage);
        viewFailLoading.findViewById(R.id.retry).setOnClickListener(this);
    }

    public void showFailure(String message) {
        removeAllViews();
        addView(viewFailLoading);
        msgFailLoading.setText(message);
    }

    public void showLoading(String message) {
        removeAllViews();
        addView(viewLoading);
        msgLoading.setText(message);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.retry:
                if (tryAgainClicked != null) {
                    tryAgainClicked.OntryAgainClicked();
                }
                break;
        }
    }
}
