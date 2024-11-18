package com.example.buildwithai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.buildwithai.adapters.MainAdapter;
import com.example.buildwithai.databinding.ActivityMainBinding;
import com.example.buildwithai.models.CardItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<CardItem>cards;
    MainAdapter adapter;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cards = new ArrayList<>();
        cards.add(new CardItem(R.drawable.chat_icon,"Chat"));
        cards.add(new CardItem(R.drawable.meeting_icon,"Attend a meeting"));
        cards.add(new CardItem(R.drawable.video_icon,"Transcribe a video"));

        adapter = new MainAdapter(MainActivity.this,cards);

        binding.mainRecycler.setAdapter(adapter);

        binding.mainRecycler.setLayoutManager(new LinearLayoutManager(this));

    }
}