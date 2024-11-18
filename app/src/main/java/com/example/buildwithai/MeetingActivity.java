package com.example.buildwithai;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.SpeechRecognizer;
import android.speech.RecognitionListener;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.buildwithai.databinding.ActivityMeetingBinding;

import java.util.ArrayList;

public class MeetingActivity extends AppCompatActivity {

    private ActivityMeetingBinding binding;
    private SpeechRecognizer speechRecognizer;
    private Intent recognizerIntent;
    private TextView resultTextView;
    private boolean isRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        resultTextView = binding.resultTextView;

        // Initialize SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                // Ready for speech recognition
            }

            @Override
            public void onBeginningOfSpeech() {
                // Speech started
            }

            @Override
            public void onRmsChanged(float rmsdB) {
                // Sound level changed
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
                // Data received
            }

            @Override
            public void onEndOfSpeech() {
                // Speech ended
            }

            @Override
            public void onError(int error) {
                // Handle error
            }

            @Override
            public void onResults(Bundle results) {
                // Handle recognition results
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (data != null && !data.isEmpty()) {
                    // Update the result TextView with the transcribed text
                    resultTextView.setText(data.get(0));
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                // Handle partial results
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
                // Handle events
            }
        });

        // Set up the RecognizerIntent
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");

        // Set up the buttons
        binding.startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        binding.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });
    }

    private void startRecording() {
        if (!isRecording) {
            // Start recording
            speechRecognizer.startListening(recognizerIntent);
            isRecording = true;
        }
    }

    private void stopRecording() {
        if (isRecording) {
            // Stop recording
            speechRecognizer.stopListening();
            isRecording = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}
