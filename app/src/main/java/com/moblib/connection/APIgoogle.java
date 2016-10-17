package com.moblib.connection;

import com.moblib.adapters.google.GoogleBookAdapter;
import com.moblib.adapters.google.GoogleBookListAdapter;
import com.moblib.application.MobLibConstant;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.Url;

public interface APIgoogle {

    @GET(MobLibConstant.URLConstants.GOOGLE_BOOKS_API_SEARCH_VOLUMES)
    Call<GoogleBookListAdapter> searchForBooks(@Query(MobLibConstant.URLParamConstants.GOOGLE_BOOKS_API_SEARCH_PARAM) String searchQuery, @Query(MobLibConstant.URLParamConstants.GOOGLE_BOOKS_API_START_INDEX) int startIndex);
    @GET
    Call<GoogleBookAdapter> getDetailsOfBook(@Url String urlToSelfLink);
}
