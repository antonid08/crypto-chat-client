package com.antonid.chatclient.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.antonid.chatclient.R;
import com.antonid.chatclient.api.service.ApiProvider;
import com.antonid.chatclient.api.utils.HandleErrorsCallback;
import com.antonid.chatclient.gui.chat.ChatActivity;

import retrofit2.Call;
import retrofit2.Response;


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
            String interlocutorString = interlocutor.getText().toString();

            if (!interlocutorString.isEmpty()) {
                ApiProvider.getChatApi().isInterlocutorExists(interlocutorString).
                        enqueue(new IsInterlocutorExistsCallback(ChooseInterlocutorActivity.this));
            } else {
                Toast.makeText(ChooseInterlocutorActivity.this, R.string.interlocutor_empty_error,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class IsInterlocutorExistsCallback extends HandleErrorsCallback<Boolean> {

        IsInterlocutorExistsCallback(@NonNull Context context) {
            super(context);
        }

        @Override
        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
            if (response.body()) {
                ChatActivity.start(ChooseInterlocutorActivity.this, interlocutor.getText()
                        .toString());
            } else {
                Toast.makeText(ChooseInterlocutorActivity.this, R.string.no_interlocutor,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}
