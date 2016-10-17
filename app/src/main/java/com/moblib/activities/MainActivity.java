package com.moblib.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.moblib.R;
import com.moblib.activities.database.DatabaseBookSearchingActivity;
import com.moblib.activities.google.GoogleSearchActivity;
import com.moblib.activities.others.AboutActivity;

/**
 * Created by OG on 11.05.2016.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Button googleButton = (Button) findViewById(R.id.googleSender);
        googleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , GoogleSearchActivity.class);
                startActivity(intent);
            }
        });

        Button dbButton= (Button) findViewById(R.id.dbSender);
        dbButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , DatabaseBookSearchingActivity.class);
                startActivity(intent);
            }
        });
        Button aboutButton= (Button) findViewById(R.id.about);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AboutActivity.class);
                startActivity(intent);
            }
        });
        Button exitButton = (Button) findViewById(R.id.exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
            }
        });
    }
}
