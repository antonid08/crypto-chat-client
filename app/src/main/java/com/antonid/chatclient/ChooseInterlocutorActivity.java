package com.antonid.chatclient;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseInterlocutorActivity extends AppCompatActivity {

    private EditText interlocutor;

    public static void start(Context context) {
        Intent chooseInterlocutor = new Intent(context, ChooseInterlocutorActivity.class);
        context.startActivity(chooseInterlocutor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_interlocutor_activity);

        interlocutor = (EditText) findViewById(R.id.interlocutor);

        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatActivity.start(ChooseInterlocutorActivity.this, interlocutor.getText().toString());
            }
        });
    }

}
