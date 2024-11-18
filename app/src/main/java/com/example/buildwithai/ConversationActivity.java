package com.example.buildwithai;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.buildwithai.adapters.ChatAdapter;
import com.example.buildwithai.databinding.ActivityConversationBinding;
import com.example.buildwithai.models.ChatModel;

import java.util.ArrayList;
import java.util.List;

public class ConversationActivity extends AppCompatActivity {

    List<ChatModel> messageList;
    ChatAdapter chatAdapter;
    ActivityConversationBinding binding;
    GeminiHelper gm;
    private MediaRecorder mediaRecorder;
    private String audioFilePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConversationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        gm = new GeminiHelper();
        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(ConversationActivity.this, messageList);
        binding.mainRecycler.setAdapter(chatAdapter);
        binding.mainRecycler.setLayoutManager(new LinearLayoutManager(ConversationActivity.this));

        binding.sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.textEt.getText().toString().equals("")) {
                    String query = binding.textEt.getText().toString();
                    ChatModel newMessage = new ChatModel(query, ChatModel.SENT_BY_ME);
                    messageList.add(newMessage);
                    chatAdapter.notifyDataSetChanged();
                    binding.mainRecycler.smoothScrollToPosition(messageList.size());
                    binding.textEt.setText("");

                    // Call Gemini API with filtered query
                    gm.callGemini(filtered(query), new GeminiHelper.GeminiCallback() {
                        @Override
                        public void onSuccess(String result) {
                            String modResult = "Translation: " + result;
                            ChatModel trans = new ChatModel(modResult, ChatModel.SENT_BY_ME);

                            // Ensure UI update is done on the main thread
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    messageList.add(trans);
                                    chatAdapter.notifyDataSetChanged();
                                    binding.mainRecycler.smoothScrollToPosition(messageList.size());
                                }
                            });
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            // Handle failure if needed
                        }
                    });
                }
            }
        });


        binding.micBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,RecognizerIntent.EXTRA_LANGUAGE);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Start Speaking");
                startActivityForResult(intent,200);
            }
        });
    }

    private String filtered(String query) {
        return "\n" +
                "I am building a translator app where I got this text \"" + query + "\". " +
                "Translate it into English. Don't add anything else (not even a word or a character) " +
                "because I wanna use it as a result in my app.";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == RESULT_OK)
        {
            String message = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
            ChatModel mes = new ChatModel(message,ChatModel.SENT_BY_BOT);
            messageList.add(mes);
            gm.callGemini(filtered(message), new GeminiHelper.GeminiCallback() {
                @Override
                public void onSuccess(String result) {
                    String modResult = "Translation: " + result;
                    ChatModel trans = new ChatModel(modResult, ChatModel.SENT_BY_BOT);

                    // Ensure UI update is done on the main thread
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            messageList.add(trans);
                            chatAdapter.notifyDataSetChanged();
                            binding.mainRecycler.smoothScrollToPosition(messageList.size());
                        }
                    });
                }

                @Override
                public void onFailure(Throwable t) {
                    // Handle failure if needed
                }
            });
            chatAdapter.notifyDataSetChanged();
            binding.mainRecycler.smoothScrollToPosition(messageList.size());


        }
    }
}
