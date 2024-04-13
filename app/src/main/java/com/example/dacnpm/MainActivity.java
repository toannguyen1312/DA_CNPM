package com.example.dacnpm;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        fetchAPIListItem();
    }
    public void fetchAPIListItem() {
        NewsService newsFetcher = new NewsService(this);
        NewsAdapter newsAdapter = new NewsAdapter(this, R.layout.layout_item_tintuc, newsFetcher.getAll());
        ArrayList<News> listNews = newsFetcher.getAll();
        listView.setAdapter(newsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, ActivityNews.class);
                intent.putExtra("url", listNews.get(position).getSourceURL());
                startActivity(intent);
            }
        });
    }
}

