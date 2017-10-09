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
import com.antonid.chatclient.api.utils.LoadingDialogCallback;
import com.antonid.chatclient.crypto.Cipher;
import com.antonid.chatclient.crypto.CipherProvider;
import com.antonid.chatclient.models.Message;
import com.antonid.chatclient.models.Settings;
import com.antonid.chatclient.models.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;


public class ChatActivity extends AppCompatActivity {

    private static final String INTERLOCUTOR_EXTRA = "INTERLOCUTOR_EXTRA";

    private static String unpackInterlocutor(Intent intent) {
        return intent.getStringExtra(INTERLOCUTOR_EXTRA);
    }

    private String interlocutor;

    private MessagesAdapter messagesAdapter;

    private EditText message;

    private User loggedUser;

    public static void start(Context context, String interlocutor) {
        Intent chat = new Intent(context, ChatActivity.class);
        chat.putExtra(INTERLOCUTOR_EXTRA, interlocutor);
        context.startActivity(chat);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        Settings settings = SettingsServiceProvider.getSettingsService(this).load();
        loggedUser = settings.getLoggedUser();

        RecyclerView messages = (RecyclerView) findViewById(R.id.messages);
        messages.setLayoutManager(new LinearLayoutManager(this));
        messages.setAdapter(messagesAdapter = new MessagesAdapter(loggedUser, new ArrayList<Message>()));

        message = (EditText) findViewById(R.id.message);

        Button send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new SendOnClickListener());

        interlocutor = unpackInterlocutor(getIntent());
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    private void sendMessage(Message message) {
        Cipher cipher = new CipherProvider().getCipher(loggedUser.getEncryption().getType());

        String encrypted = cipher.encrypt(message.getText(),
                loggedUser.getEncryption().getKey().getPublicKey());

        Message encryptedMessage = new Message(loggedUser.getUsername(), encrypted);
        ApiProvider.getChatApi().sendMessage(interlocutor, encryptedMessage)
                .enqueue(new SendMessageCallback(this, message));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMessage(MessageEvent messageEvent) {
        Cipher cipher = new CipherProvider().getCipher(loggedUser.getEncryption().getType());
        String decryptedString = cipher.decrypt(messageEvent.getMessage().getText(),
                loggedUser.getEncryption().getKey().getPrivateKey());

        Message decryptedMessage = new Message(messageEvent.getMessage().getSenderUsername(), decryptedString);
        messagesAdapter.add(decryptedMessage);
    }

    private class SendOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            sendMessage(new Message(loggedUser.getUsername(), message.getText().toString()));
        }
    }

    private class SendMessageCallback extends LoadingDialogCallback<Void> {

        private Message sentMessage;

        SendMessageCallback(@NonNull Context context, Message sentMessage) {
            super(context);
            this.sentMessage = sentMessage;
        }

        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            super.onResponse(call, response);
            messagesAdapter.add(sentMessage);
        }
    }
}
