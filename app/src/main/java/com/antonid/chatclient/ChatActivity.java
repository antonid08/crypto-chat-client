package com.antonid.chatclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class ChatActivity extends AppCompatActivity {

    private static final String INTERLOCUTOR_EXTRA = "INTERLOCUTOR_EXTRA";

    private String interlocutor;

    public static void start(Context context, String interlocutor) {
        Intent chat = new Intent(context, ChatActivity.class);
        chat.putExtra(INTERLOCUTOR_EXTRA, interlocutor);
        context.startActivity(chat);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        interlocutor = unpackInterlocutor(getIntent());


    }

    private String unpackInterlocutor(Intent intent) {
        return intent.getStringExtra(INTERLOCUTOR_EXTRA);
    }

}
