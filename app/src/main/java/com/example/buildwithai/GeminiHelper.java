package com.example.buildwithai;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GeminiHelper {
    private final String apiKey = "AIzaSyD-mWV8ipg5hbKHOCN1-EtHbA5qiWdBfH4";

    public GeminiHelper() {
    }

    public void callGemini(String prompt, final GeminiCallback callback) {
        GenerativeModel gm = new GenerativeModel("gemini-1.5-pro", apiKey);
        GenerativeModelFutures model = GenerativeModelFutures.from(gm);

        Content content = new Content.Builder().addText(prompt).build();

        Executor executor = Executors.newSingleThreadExecutor();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);
        Futures.addCallback(
                response,
                new FutureCallback<GenerateContentResponse>() {
                    @Override
                    public void onSuccess(GenerateContentResponse result) {
                        String resultText = result.getText();
                        callback.onSuccess(resultText); // Pass result to the callback
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        callback.onFailure(t); // Handle failure via callback
                    }
                },
                executor);
    }

    public interface GeminiCallback {
        void onSuccess(String result);
        void onFailure(Throwable t);
    }
}
