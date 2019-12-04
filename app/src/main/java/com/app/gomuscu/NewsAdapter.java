package com.app.gomuscu;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.gomuscu.entity.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> data;
    private GoMuscuViewModel viewModel;

    public NewsAdapter(List<News> newsList, GoMuscuViewModel viewModel) {
        this.data = newsList;
        this.viewModel = viewModel;
    }

    public void setData(List<News> newsList) {
        this.data = newsList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_row, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(this.data.get(position).getTitre());
        holder.setUrl(this.data.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        String url;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.textView = view.findViewById(R.id.tv_news_row);
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public void onClick(View v) {
            if(this.url != null) {
                Uri uri = Uri.parse(this.url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                v.getContext().startActivity(intent);
            }
        }
    }
}
