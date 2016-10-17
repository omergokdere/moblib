package com.moblib.activities.google;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moblib.R;
import com.moblib.adapters.google.GoogleBookAdapter;
import com.moblib.application.MobLibConstant;
import com.moblib.application.Moblib;
import com.moblib.connection.APIerrors;
import com.moblib.utilities.ToolbarName;
import com.moblib.utilities.VisibilityManager;
import com.moblib.views.PlaceHolder;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class GoogleBookDetailActivity extends Fragment implements PlaceHolder.OntryAgainClicked, View.OnClickListener {

    private GoogleBookAdapter googleBookFull;
    private VisibilityManager vm;

    public static GoogleBookDetailActivity newInstance(String linkToBookDetails) {
        GoogleBookDetailActivity googleBookDetailActivity = new GoogleBookDetailActivity();
        Bundle args = new Bundle();
        args.putString(MobLibConstant.BundleConstants.BOOK_SELF_LINK, linkToBookDetails);
        googleBookDetailActivity.setArguments(args);
        return googleBookDetailActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.google_book_detail_activity, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar1);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PlaceHolder placeHolder = (PlaceHolder) view.findViewById(R.id.placeholderView);
        placeHolder.setOntryAgainClicked(this);
/*        View viewOnGoogleBooks = view.findViewById(R.id.viewOnGoogleBooks);
        viewOnGoogleBooks.setOnClickListener(this);*/
        vm = new VisibilityManager(placeHolder,/*viewOnGoogleBooks, */view.findViewById(R.id.mainContent));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().post(new ToolbarName(Moblib.getInstance().getResources().getString(R.string.toolbarBookDetail)));
        handleInitialContent();
    }

    private void handleInitialContent() {
        if (googleBookFull == null) {
            vm.showLoading(Moblib.getInstance().getResources().getString(R.string.bookDetailLoading));
            getFullDetailsOfBook();
        } else {
            showBookDetails();
        }
    }

    private void getFullDetailsOfBook() {
        String urlToSelfLink = getArguments().getString(MobLibConstant.BundleConstants.BOOK_SELF_LINK);
        Call<GoogleBookAdapter> call = Moblib.getInstance().getAPIconn().getDetailsOfBook(urlToSelfLink);
        call.enqueue(new Callback<GoogleBookAdapter>() {
            @Override
            public void onResponse(Response<GoogleBookAdapter> response, Retrofit retrofit) {
                if (isAdded()) {
                    if (response.isSuccess()) {
                        googleBookFull = response.body();
                        showBookDetails();
                    } else {
                        vm.showFailure(APIerrors.getUnsuccessfulRequestMessage(response));
                    }
                }
            }
            @Override
            public void onFailure(Throwable t) {
                vm.showFailure(APIerrors.getFailedRequestMessage(t));
            }
        });

    }

    private void showBookDetails() {
        final View view = getView();
        if(view != null){
            ImageView bookImage = (ImageView)view.findViewById(R.id.bookImage);
            TextView bookTitle = (TextView)view.findViewById(R.id.bookTitle);
            TextView bookAuthor = (TextView)view.findViewById(R.id.bookAuthor);
            TextView description = (TextView)view.findViewById(R.id.description);
            TextView categories = (TextView)view.findViewById(R.id.categories);
            TextView publisher = (TextView)view.findViewById(R.id.publisher);
            TextView miscellaneous = (TextView)view.findViewById(R.id.miscellaneous);
            TextView identifier = (TextView)view.findViewById(R.id.identifier);

            if(googleBookFull.getBookInfo().getBookImages() != null){
                Glide.with(getActivity()).load(googleBookFull.getBookInfo().getBookImages().getLargestPossibleImage()).fitCenter().into(bookImage);
            }

            bookTitle.setText(googleBookFull.getBookInfo().getTitle());
            bookAuthor.setText(googleBookFull.getBookInfo().getAuthors());
            description.setText(Html.fromHtml(googleBookFull.getBookInfo().getDescription()));
            categories.setText(googleBookFull.getBookInfo().getCategory());
            publisher.setText(Moblib.getInstance().getResources().getString(R.string.bookDetailPublisherInfo, googleBookFull.getBookInfo().getPublisher(), googleBookFull.getBookInfo().getPublishedDate()));
            miscellaneous.setText(Moblib.getInstance().getResources().getString(R.string.bookDetailMiscellaneousInfo, googleBookFull.getBookInfo().getLanguage(), googleBookFull.getBookInfo().getPageCount()));
            identifier.setText(googleBookFull.getBookInfo().getBookIdentifiers());
        }
        vm.showMainContent();
    }


    @Override
    public void OntryAgainClicked() {
        handleInitialContent();
    }

    @Override
    public void onClick(View view) {
/*        switch (view.getId()){
            case R.id.viewOnGoogleBooks:
                IntentUtils.startInternetBrowser(getActivity(),googleBookFull.getBookInfo().getUrlToBookOnGoogleBooks());
                break;
        }*/
    }
}
