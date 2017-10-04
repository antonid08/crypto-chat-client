package com.antonid.chatclient.gui.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.antonid.chatclient.R;
import com.antonid.chatclient.SettingsServiceProvider;
import com.antonid.chatclient.api.service.ApiProvider;
import com.antonid.chatclient.api.utils.HandleErrorsCallback;
import com.antonid.chatclient.crypto.CaesarEncrypter;
import com.antonid.chatclient.models.Encryption;
import com.antonid.chatclient.models.Message;
import com.antonid.chatclient.models.Settings;
import com.antonid.chatclient.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;


public class ChatActivity extends AppCompatActivity {

    private static final String INTERLOCUTOR_EXTRA = "INTERLOCUTOR_EXTRA";

    private String interlocutor;

    private RecyclerView messages;
    private MessagesAdapter messagesAdapter;

    private EditText message;
    private Button send;

    private Settings settings = SettingsServiceProvider.getSettingsService(this).load();
    private User loggedUser = settings.getLoggedUser();

    public static void start(Context context, String interlocutor) {
        Intent chat = new Intent(context, ChatActivity.class);
        chat.putExtra(INTERLOCUTOR_EXTRA, interlocutor);
        context.startActivity(chat);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        messages = (RecyclerView) findViewById(R.id.messages);
        messages.setLayoutManager(new LinearLayoutManager(this));
        messages.setAdapter(messagesAdapter = new MessagesAdapter(loggedUser, new ArrayList<Message>()));

        message = (EditText) findViewById(R.id.message);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new SendOnClickListener());

        interlocutor = unpackInterlocutor(getIntent());
    }

    private String unpackInterlocutor(Intent intent) {
        return intent.getStringExtra(INTERLOCUTOR_EXTRA);
    }

    private void sendMessage(Message message) {
        if (loggedUser.getEncryption() == Encryption.CAESAR) { //todo symmetric qualifier
            String encrypted = new CaesarEncrypter().encrypt(message.getText(), 1);
            Message encryptedMessage = new Message(loggedUser, encrypted);
            ApiProvider.getChatApi().sendMessage(interlocutor, encryptedMessage)
                    .enqueue(new SendMessageCallback(this, encryptedMessage));
        }
    }

    private class SendOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            sendMessage(new Message(loggedUser, message.getText().toString()));
        }
    }

    private class SendMessageCallback extends HandleErrorsCallback<Void> {

        private Message sentMessage;

        SendMessageCallback(@NonNull Context context, Message sentMessage) {
            super(context);
            this.sentMessage = sentMessage;
        }

        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            messagesAdapter.add(sentMessage);
        }
    }
}
