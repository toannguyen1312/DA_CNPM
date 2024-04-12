package com.example.dacnpm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends BaseAdapter {


    private Context context;

    private int layout;

    private List<News> listNews;

    public NewsAdapter(Context context, int layout, List<News> listNews) {
        this.context = context;
        this.layout = layout;
        this.listNews = listNews;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<News> getListNews() {
        return listNews;
    }

    public void setListNews(List<News> listNews) {
        this.listNews = listNews;
    }

    @Override
    public int getCount() {
        return listNews.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public class viewHolder {

        TextView textViewTitle, textViewSource, textViewDate, textView;

        ImageView imageView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder viewHolder;

        if(convertView == null) {

            viewHolder = new viewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            viewHolder.textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
            viewHolder.textViewSource = (TextView) convertView.findViewById(R.id.textViewSource);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.listImage);


            convertView.setTag(viewHolder);
        }else {
            viewHolder = (viewHolder) convertView.getTag();

        }

        News news = listNews.get(position);
        viewHolder.textViewTitle.setText(news.getTitle());
        viewHolder.textViewSource.setText(news.getCategory().getName());
        viewHolder.textViewDate.setText(news.getPublishDate());
        Picasso.get().load(news.getImgURL()).into(viewHolder.imageView);





        return convertView;
    }



}
