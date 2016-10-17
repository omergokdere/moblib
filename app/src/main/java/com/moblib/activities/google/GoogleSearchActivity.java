package com.moblib.activities.google;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.moblib.R;
import com.moblib.activities.others.ToolbarActivity;
import com.moblib.adapters.google.GoogleBookDetailLauncher;
import com.moblib.adapters.google.GoogleBookSearchingLauncher;
import com.moblib.utilities.ToolbarName;

import de.greenrobot.event.EventBus;

public class GoogleSearchActivity extends ToolbarActivity implements FragmentManager.OnBackStackChangedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.google_activity);

        getSupportFragmentManager().addOnBackStackChangedListener(this);
        EventBus.getDefault().register(this);
        replaceFragment(new GoogleBookSearchingActivity(), R.id.fragmentContainer, false, GoogleBookSearchingActivity.class.getName(), true);

    }

    private void showBookSearchResultsFragment(String searchQuery){
        GoogleBookSearchingResultActivity googleBookSearchingResultActivity = GoogleBookSearchingResultActivity.newInstance(searchQuery);
        replaceFragment(googleBookSearchingResultActivity, R.id.fragmentContainer, true, GoogleBookSearchingResultActivity.class.getName(), true);
    }

    private void showBookDetailFragment(String linkToBookDetails){
        GoogleBookDetailActivity googleBookDetailActivity = GoogleBookDetailActivity.newInstance(linkToBookDetails);
        replaceFragment(googleBookDetailActivity, R.id.fragmentContainer, true, GoogleBookDetailActivity.class.getName(), true);
    }

    /** Event Bus subscribers*/

    public void onEvent(GoogleBookSearchingLauncher googleBookSearchingLauncher){
        showBookSearchResultsFragment(googleBookSearchingLauncher.getSearchQuery());
    }

    public void onEvent(GoogleBookDetailLauncher googleBookDetailLauncher){
        showBookDetailFragment(googleBookDetailLauncher.getLinkToBookDetails());
    }

    public void onEvent(ToolbarName ToolbarName){
        setToolbarName(ToolbarName.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getSupportFragmentManager().popBackStack();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackStackChanged() {
        displayHomeAsUpEnabled(getSupportFragmentManager().getBackStackEntryCount() != 0);
    }
}
