package com.example.buildwithai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.buildwithai.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity {

    ActivityVideoBinding binding;
    GeminiHelper gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gm = new GeminiHelper();
        binding.generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = binding.link.getText().toString();
                if(link.equals("") || link == null)
                {
                    binding.link.setError("Please enter a link");
                }
                gm.callGemini(filtered(link), new GeminiHelper.GeminiCallback() {
                    @Override
                    public void onSuccess(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.transcript.setText(result);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

            }
        });
    }

    private String filtered(String query) {
        return "I am building an app for people with hearing disabilities. I got this video link \"" + query + "\". " +
                "Describe the entire content of the video.";
    }
}