package com.moblib.adapters.google;

public class GoogleBookSearchingLauncher {
    private String gBookQuery;

    public GoogleBookSearchingLauncher(String gBookQuery) {
        this.gBookQuery = gBookQuery;
    }

    public String getSearchQuery() {
        return gBookQuery;
    }
}