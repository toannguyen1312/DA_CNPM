package com.example.dacnpm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView listView;
    private NewsAdapter newsAdapter;
    private ArrayList<News> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new ArrayList<>(); // Initialize here

        fetchData();
    }

    private void fetchData() {
        String url = "http://binhan.io.vn/api/cnpm2/getNews.php";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArray = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            try {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String title = object.getString("title");
                                JSONObject jsonObjectCategory = object.getJSONObject("category");
                                int categoryID = jsonObjectCategory.getInt("categoryID");
                                String name = jsonObjectCategory.getString("name");
                                String date = object.getString("publishDate");
                                String description = object.getString("description");
                                String imgURL = object.getString("imgURL");
                                String sourceURL = object.getString("sourceURL");

                                String dateString = date.substring(0, 10);
                                String day = dateString.substring(8, 10);
                                String month = dateString.substring(5, 7);
                                String year = dateString.substring(0, 4);
                                String formattedDate = day + "/" + month + "/" + year;

                                Category category = new Category(categoryID, name);
                                News news = new News(title, category, formattedDate, description, imgURL, sourceURL);

                                listAdapter.add(news);
                            } catch (JSONException e) {
                                Toast.makeText(MainActivity.this, "Error parsing data", Toast.LENGTH_SHORT).show();
                            }
                        }

                        newsAdapter = new NewsAdapter(MainActivity.this, R.layout.layout_item_tintuc, listAdapter);
                        listView.setAdapter(newsAdapter);


                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(listAdapter.get(position).getSourceURL()));
                                startActivity(intent);

                            }
                        });
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MainActivity.this, "Error fetching news", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArray);
    }
}