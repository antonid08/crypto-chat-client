package com.antonid.chatclient.gui.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.antonid.chatclient.Message;
import com.antonid.chatclient.R;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatActivity extends AppCompatActivity {

    private static final String USER_NAME_EXTRA = "USER_NAME_EXTRA";

    private WebSocket chatSocket;
    private OkHttpClient client;


    private String username;

    private EditText message;
    private Button send;

    public static void start(Context context, String username) {
        Intent chat = new Intent(context, ChatActivity.class);
        chat.putExtra(USER_NAME_EXTRA, username);
        context.startActivity(chat);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        message = (EditText) findViewById(R.id.message);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new SendOnClickListener());

        username = unpackUsername(getIntent());

        openSocketConnection();
    }

    private void openSocketConnection() {
        client = new OkHttpClient();

        Request request = new Request.Builder().url("ws://").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();

        chatSocket = client.newWebSocket(request, listener);
    }

    private String unpackUsername(Intent intent) {
        return intent.getStringExtra(USER_NAME_EXTRA);
    }

    private void sendMessage(Message message) {
        chatSocket.send(new Gson().toJson(message));
    }

    private class SendOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            sendMessage(new Message(username, message.getText().toString()));
        }
    }

    private final class EchoWebSocketListener extends WebSocketListener {

        @Override
        public void onMessage(WebSocket webSocket, String text) {

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        }
    }

}
