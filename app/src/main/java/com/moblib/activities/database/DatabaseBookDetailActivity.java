package com.moblib.activities.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.moblib.R;

public class DatabaseBookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_book_detail_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textTitle = (TextView) findViewById(R.id.tvTitle);
        TextView textAuthor = (TextView) findViewById(R.id.tvAuthor);
        TextView textDescription = (TextView) findViewById(R.id.tvDescription);
        TextView textCategory = (TextView) findViewById(R.id.tvCategory);
        TextView textPublisher = (TextView) findViewById(R.id.tvPublisher);
        TextView textLanguage = (TextView) findViewById(R.id.tvLanguage);
        TextView textOtherAuthor = (TextView) findViewById(R.id.tvOtherAuthor);
        TextView textISBN = (TextView) findViewById(R.id.tvISBN);


        textTitle.setText( getIntent().getExtras().getString("tvTitle"));
        textAuthor.setText( getIntent().getExtras().getString("tvAuthor"));
        textDescription.setText( getIntent().getExtras().getString("tvDescriptionItem"));
        textCategory.setText( getIntent().getExtras().getString("tvCategoryItem"));
        textPublisher.setText( getIntent().getExtras().getString("tvPublisherItem"));
        textLanguage.setText( getIntent().getExtras().getString("tvLanguageItem"));
        textOtherAuthor.setText( getIntent().getExtras().getString("tvOtherAuthorItem"));
        textISBN.setText( getIntent().getExtras().getString("tvISBNitem"));

    }

}
