package com.moblib.activities.database;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.moblib.R;
import com.moblib.adapters.database.DatabaseBookAdapter;
import com.moblib.connection.mysqlConnection;
import com.moblib.utilities.ComboItem;
import com.moblib.utilities.ListViewItem;

import java.util.ArrayList;
import java.util.List;

public class DatabaseBookSearchingActivity extends AppCompatActivity {

    public void searchBooks()
    {
        try {
            mysqlConnection dl = new mysqlConnection();

            Spinner spnSearchBy = (Spinner)this.findViewById(R.id.spnSearchBy);
            TextView tbxSearchText = (TextView)this.findViewById(R.id.tbxSearchText);

            ComboItem selectedItem = (ComboItem) spnSearchBy.getSelectedItem();
            int i = (int)selectedItem.getItemValue();


            List<ListViewItem> bookList = dl.getBookList(i, tbxSearchText.getText().toString());

            if (bookList != null) {
                ListView lv = (ListView) findViewById(R.id.dataGrid);
                lv.setAdapter(new DatabaseBookAdapter(DatabaseBookSearchingActivity.this, bookList));

            } else {
                Toast.makeText(DatabaseBookSearchingActivity.this,"Please select search criteria...", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Toast.makeText(DatabaseBookSearchingActivity.this,"Database connection or network error", Toast.LENGTH_SHORT).show();
            Log.e("Mobile Library",ex.getMessage());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_book_searching_activity);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Spinner spnSearchBy = (Spinner)this.findViewById(R.id.spnSearchBy);

        List<ComboItem> searchByList = new ArrayList<ComboItem>();

        searchByList.add(new ComboItem("Select",0));
        searchByList.add(new ComboItem("Author",1));
        searchByList.add(new ComboItem("Category",2));
        searchByList.add(new ComboItem("Publisher",3));
        searchByList.add(new ComboItem("Title", 4));

        ArrayAdapter<ComboItem> searchByAdapter = new ArrayAdapter<ComboItem> (this, android.R.layout.simple_spinner_dropdown_item, searchByList);
        spnSearchBy.setAdapter(searchByAdapter);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    public void run() {
                        searchBooks();
                    }
                });
                }
        });

    }
}
