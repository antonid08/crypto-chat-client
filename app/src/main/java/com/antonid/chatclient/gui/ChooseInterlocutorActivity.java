package com.antonid.chatclient.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.antonid.chatclient.R;
import com.antonid.chatclient.gui.chat.ChatActivity;


public class ChooseInterlocutorActivity extends AppCompatActivity {

    private EditText interlocutor;

    public static void start(Context context) {
        Intent chat = new Intent(context, ChooseInterlocutorActivity.class);
        context.startActivity(chat);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_interlocutor_activity);

        interlocutor = (EditText) findViewById(R.id.interlocutor);

        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new OkOnClickListener());
    }

    private class OkOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ChatActivity.start(ChooseInterlocutorActivity.this, interlocutor.getText().toString());
        }
    }

}
