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

        binding.summarize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.pdSummary.setVisibility(View.VISIBLE);
                String script = binding.transcript.getText().toString();
                script = script.concat("summarize this text");
                gm.callGemini(script, new GeminiHelper.GeminiCallback() {
                    @Override
                    public void onSuccess(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.pdSummary.setVisibility(View.INVISIBLE);
                                binding.resSummary.setVisibility(View.VISIBLE);
                                binding.resSummary.setText(result);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            }
        });
        binding.generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.pd.setVisibility(View.VISIBLE);
                String link = binding.link.getText().toString();

                if (link == null || link.isEmpty()) {
                    binding.link.setError("Please enter a link");
                    binding.pd.setVisibility(View.INVISIBLE);
                    return;
                }

                gm.callGemini(filtered(link), new GeminiHelper.GeminiCallback() {
                    @Override
                    public void onSuccess(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.pd.setVisibility(View.INVISIBLE);
                                binding.transcript.setText(result);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.pd.setVisibility(View.INVISIBLE);
                                binding.transcript.setText("Failed to process the video. Please try again.");
                            }
                        });
                    }
                });
            }
        });
    }

    private String filtered(String query) {return "I am building an app for people with hearing disabilities. I got this video link \"" + query + "\". " +
                "Describe the entire content of the video.";
    }

}