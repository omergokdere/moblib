package com.moblib.adapters.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moblib.R;
import com.moblib.activities.database.DatabaseBookDetailActivity;
import com.moblib.activities.database.DatabaseBookSearchingActivity;
import com.moblib.connection.mysqlConnection;
import com.moblib.utilities.ListViewItem;

import java.util.List;

/**
 * Created by omer.gokdere on 28.11.2015.
 */
public class DatabaseBookAdapter extends BaseAdapter {
    List<ListViewItem> dataItems = null;
    Context context;
    private static LayoutInflater inflater=null;

    public DatabaseBookAdapter(DatabaseBookSearchingActivity databaseBookSearchingActivity, List<ListViewItem> dataItems) {
        this.dataItems = dataItems;
        context= databaseBookSearchingActivity;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataItems.size();
    }

    @Override
    public Object getItem(int position) {
        return dataItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        final int TextViewWidth = parent.getWidth();
        View rowView = inflater.inflate(R.layout.book_result_lv, null);
        ImageView img =(ImageView) rowView.findViewById(R.id.bookImage);
        final TextView tvColumn1=(TextView)             rowView.findViewById(R.id.tvColumn1);
        final TextView tvColumn2=(TextView)             rowView.findViewById(R.id.tvColumn2);
        final TextView tvTitle=(TextView)               rowView.findViewById(R.id.tvTitle);
        final TextView tvAuthor=(TextView)              rowView.findViewById(R.id.tvAuthor);
        final TextView tvDescriptionItem=(TextView)     rowView.findViewById(R.id.tvDescription);
        final TextView tvCategoryItem=(TextView)        rowView.findViewById(R.id.tvCategory);
        final TextView tvPublisherItem=(TextView)       rowView.findViewById(R.id.tvPublisher);
        final TextView tvLanguageItem=(TextView)        rowView.findViewById(R.id.tvLanguage);
        final TextView tvOtherAuthorItem=(TextView)     rowView.findViewById(R.id.tvOtherAuthor);
        final TextView tvISBNitem=(TextView)            rowView.findViewById(R.id.tvISBN);


        tvColumn1.setWidth(TextViewWidth);
        tvColumn2.setWidth(TextViewWidth);
        tvDescriptionItem.setWidth(TextViewWidth);
        tvCategoryItem.setWidth(TextViewWidth);
        tvPublisherItem.setWidth(TextViewWidth);
        tvOtherAuthorItem.setWidth(TextViewWidth);
        tvLanguageItem.setWidth(TextViewWidth);
        tvISBNitem.setWidth(TextViewWidth);

        ListViewItem item = dataItems.get(position);

        tvColumn1.setText(item.getColumn1());
        tvColumn2.setText(item.getColumn2());
        tvTitle.setText(item.getTitle());
        tvAuthor.setText(item.getAuthor());
        tvDescriptionItem.setText(item.getDescription());
        tvCategoryItem.setText(item.getCategory());
        tvPublisherItem.setText(item.getPublisher());
        tvLanguageItem.setText(item.getLanguage());
        tvOtherAuthorItem.setText(item.getOtherAuthor());
        tvISBNitem.setText(item.getIsbn());



        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListViewItem item = dataItems.get(position);
                mysqlConnection dl = new mysqlConnection();

                Intent intent = new Intent(context , DatabaseBookDetailActivity.class);
                intent.putExtra("tvTitle",tvTitle.getText().toString());
                intent.putExtra("tvAuthor",tvAuthor.getText().toString());
                intent.putExtra("tvDescriptionItem",tvDescriptionItem.getText().toString());
                intent.putExtra("tvCategoryItem",tvCategoryItem.getText().toString());
                intent.putExtra("tvPublisherItem",tvPublisherItem.getText().toString());
                intent.putExtra("tvLanguageItem",tvLanguageItem.getText().toString());
                intent.putExtra("tvOtherAuthorItem",tvOtherAuthorItem.getText().toString());
                intent.putExtra("tvISBNitem",tvISBNitem.getText().toString());
                context.startActivity(intent);

            }
        });
            return rowView;
    }

}