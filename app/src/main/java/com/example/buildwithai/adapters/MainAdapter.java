package com.example.buildwithai.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildwithai.ConversationActivity;
import com.example.buildwithai.MainActivity;
import com.example.buildwithai.MeetingActivity;
import com.example.buildwithai.MovieActivity;
import com.example.buildwithai.R;
import com.example.buildwithai.VideoActivity;
import com.example.buildwithai.models.CardItem;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    Context context;
    List<CardItem> list;

    public MainAdapter(Context context, List<CardItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardItem item = list.get(position);
        holder.imageView.setImageResource(item.getImage());
        holder.textView.setText(item.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = holder.itemView.getId();
                if(position == 0)
                {
                    context.startActivity(new Intent(context, ConversationActivity.class));
                }
                else if(position == 1)
                {
                    context.startActivity(new Intent(context, VideoActivity.class));
                }
                else if(position == 2)
                {
                    context.startActivity(new Intent(context, MovieActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.main_iamge);
            textView = itemView.findViewById(R.id.title);
        }
    }
}
