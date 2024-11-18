package com.example.buildwithai;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;

import com.example.buildwithai.databinding.ActivityMovieBinding;

import java.util.ArrayList;

public class MovieActivity extends AppCompatActivity {

    ActivityMovieBinding binding;
    GeminiHelper gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gm = new GeminiHelper();
        // Start button click listener
        binding.startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent for speech recognition
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US"); // Set the language
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Start Speaking");
                startActivityForResult(intent, 200); // Start activity for speech recognition
            }
        });

        binding.aiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.pdSum.setVisibility(View.VISIBLE);
                String sub = binding.subTv.getText().toString();
                if(!(sub.equals("") || sub==null)) gm.callGemini(getPrompt(sub), new GeminiHelper.GeminiCallback() {
                    @Override
                    public void onSuccess(String result) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.pdSum.setVisibility(View.INVISIBLE);
                                binding.resultTv.setText(result);
                                binding.resultTv.setVisibility(View.VISIBLE);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        binding.pdSum.setVisibility(View.INVISIBLE);
                        binding.resultTv.setError("Sorry there was an error. Try again please.");
                    }
                });
            }
        });
    }

    private String getPrompt(String sub) {
        sub = sub.concat("Summarize this text.");
        return sub;
    }

    // Handle speech recognition result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            // Get the recognized speech
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (results != null && !results.isEmpty()) {
                String message = results.get(0);
                String current = binding.subTv.getText().toString();
                current = current.concat("\n"+message);
                binding.subTv.setText(current); // Display the recognized text
            }
        }
    }
}
