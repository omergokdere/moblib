package com.moblib.activities.google;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moblib.R;
import com.moblib.adapters.google.GoogleBookAdapter;
import com.moblib.adapters.google.GoogleBookListAdapter;
import com.moblib.adapters.google.GoogleBookSearchResultsAdapter;
import com.moblib.application.MobLibConstant;
import com.moblib.application.Moblib;
import com.moblib.connection.APIerrors;
import com.moblib.utilities.RecyclerViewScrolls;
import com.moblib.utilities.ToolbarName;
import com.moblib.utilities.VisibilityManager;
import com.moblib.views.PlaceHolder;
import com.moblib.views.SpaceTool;

import java.util.ArrayList;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class GoogleBookSearchingResultActivity extends Fragment implements PlaceHolder.OntryAgainClicked {

    private RecyclerView bookLists;
    private VisibilityManager vm;
    private GoogleBookSearchResultsAdapter mAdapter;
    private String mSearchQuery;
    private int mStartIndex;
    private boolean mIsDoingSearch;

    public static GoogleBookSearchingResultActivity newInstance(String searchQueryParams) {
        GoogleBookSearchingResultActivity googleBookSearchingResultActivity = new GoogleBookSearchingResultActivity();
        Bundle args = new Bundle();
        args.putString(MobLibConstant.BundleConstants.SEARCH_QUERY_PARAMS, searchQueryParams);
        googleBookSearchingResultActivity.setArguments(args);
        return googleBookSearchingResultActivity;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSearchQuery = getArguments().getString(MobLibConstant.BundleConstants.SEARCH_QUERY_PARAMS);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.google_book_searching_result_activity, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar1);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookLists = (RecyclerView) view.findViewById(R.id.list);
        bookLists.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        bookLists.setLayoutManager(linearLayoutManager);
        bookLists.addItemDecoration(new SpaceTool(10));
        bookLists.addOnScrollListener(new RecyclerViewScrolls(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                carryOutSearch();
            }
        });

        PlaceHolder placeHolder = (PlaceHolder) view.findViewById(R.id.placeholderView);
        placeHolder.setOntryAgainClicked(this);
        vm = new VisibilityManager(placeHolder, bookLists);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().post(new ToolbarName(Moblib.getInstance().getResources().getString(R.string.toolbarBookSearchingResult)));
        handleInitialContent();
    }

    private void handleInitialContent() {
        if (mStartIndex == 0) { //If we are coming back from the back stack, only the view is destroyed but not the members.
            mAdapter = new GoogleBookSearchResultsAdapter(new ArrayList<GoogleBookAdapter>());
            bookLists.setAdapter(mAdapter);
            vm.showLoading(Moblib.getInstance().getResources().getString(R.string.bookSearchingLoading));
            carryOutSearch();
        } else {
            bookLists.setAdapter(mAdapter);
            vm.showMainContent();
        }
    }

    /** Checking mStartIndex is equal to 0 is a way to check if we have not on the first page already so that I can display failure or no books placeholder if it is the first request. This is important because if the user is scrolling
     *  to get the next page of results, I do not want to show these placeholder if something goes wrong thus
     * */
    private void carryOutSearch(){
        if(!mIsDoingSearch){
            if(mStartIndex > 0){ /** Show a progress item if the user is getting the next page of results */
                mAdapter.addProgressItem();
            }
            mIsDoingSearch = true;
            Call<GoogleBookListAdapter> call = Moblib.getInstance().getAPIconn().searchForBooks(mSearchQuery,mStartIndex);
            call.enqueue(new Callback<GoogleBookListAdapter>() {
                @Override
                public void onResponse(Response<GoogleBookListAdapter> response, Retrofit retrofit) {
                    if(isAdded()){
                        mIsDoingSearch = false;
                        mAdapter.removeProgressItem(); /** Remove anyway, it is safe*/
                        if(response.isSuccess()){
                            ArrayList<GoogleBookAdapter> books = response.body().getBookItems();
                            if(books != null){ /** If the search yielded no books, the array is null so  */
                                if(mStartIndex == 0){
                                    vm.showMainContent();
                                }
                                mAdapter.addItems(books);
                                mStartIndex = mAdapter.getItemCount() + 1; /** Used for pagination. We must tell the API where we want to start in the collection of books. Simple calculation of our current adapter array size + 1 to start from the next book   */
                            }else{
                                if(mStartIndex == 0){
                                    vm.showFailure(Moblib.getInstance().getResources().getString(R.string.noBooks));
                                }
                            }
                        }else{
                            if(mStartIndex == 0){
                                vm.showFailure(APIerrors.getUnsuccessfulRequestMessage(response));
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    mIsDoingSearch = false;
                    mAdapter.removeProgressItem();
                    if(mStartIndex == 0){
                        vm.showFailure(APIerrors.getFailedRequestMessage(t));
                    }
                }
            });
        }

    }

    @Override
    public void OntryAgainClicked() {
        handleInitialContent();
    }
}
