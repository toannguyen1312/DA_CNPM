package com.example.dacnpm;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsService {
    private Context context;
    public NewsService(Context context ) {
        this.context = context;
    }
    public ArrayList<News> getAll() {
        ArrayList<News> listAdapter = new ArrayList<>();
        String url = "http://binhan.io.vn/api/cnpm2/getNews.php";
        RequestQueue requestQueue = Volley.newRequestQueue(context);
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
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(context, "Error fetching news", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArray);
        return  listAdapter;
    }
}
