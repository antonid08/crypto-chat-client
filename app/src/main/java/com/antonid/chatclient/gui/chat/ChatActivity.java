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

import org.java_websocket.WebSocket;

import io.reactivex.functions.Consumer;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.client.StompClient;


public class ChatActivity extends AppCompatActivity {

    private static final String INTERLOCUTOR_EXTRA = "INTERLOCUTOR_EXTRA";

    private String interlocutor;

    private EditText message;
    private Button send;

    StompClient chatClient;


    public static void start(Context context, String interlocutor) {
        Intent chat = new Intent(context, ChatActivity.class);
        chat.putExtra(INTERLOCUTOR_EXTRA, interlocutor);
        context.startActivity(chat);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);

        message = (EditText) findViewById(R.id.message);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new SendOnClickListener());

        interlocutor = unpackInterlocutor(getIntent());

        chatClient = createChatClient();
        int i = 0;
    }

    private StompClient createChatClient() {
        chatClient = Stomp.over(WebSocket.class, "ws://192.168.0.105:8080/message/websocket");
        chatClient.connect();

        return chatClient;
    }
/*
    private WebSocket createSocket() {
        WebSocket socket;

        try {
            socket = new WebSocketFactory().createSocket("ws://192.168.0.105:8080/message/websocket");
        } catch (IOException e) {
            Toast.makeText(this, "Cant create socket.", Toast.LENGTH_SHORT).show();
            return null;
        }


        socket.connectAsynchronously();

        return socket;
    }*/

    private String unpackInterlocutor(Intent intent) {
        return intent.getStringExtra(INTERLOCUTOR_EXTRA);
    }

    private void sendMessage(Message message) {
        chatClient.send("/message/to/" + interlocutor, new Gson().toJson(message)).subscribe(new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) throws Exception {
                int i = 0;
            }
        });

//        chatSocket.sendText(new Gson().toJson(message));
//        chatSocket.send(new Gson().toJson(message));
    }

    private class SendOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            sendMessage(new Message("usename", message.getText().toString()));
        }
    }
/*

    private final class ChatSocketListener extends WebSocketAdapter {
        @Override
        public void onConnected(WebSocket websocket, Map<String, List<String>> headers) throws Exception {
            super.onConnected(websocket, headers);
        }

        @Override
        public void onConnectError(WebSocket websocket, WebSocketException exception) throws Exception {
            super.onConnectError(websocket, exception);
        }

        @Override
        public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
            super.onBinaryMessage(websocket, binary);
        }

        @Override
        public void onTextMessageError(WebSocket websocket, WebSocketException cause, byte[] data) throws Exception {
            super.onTextMessageError(websocket, cause, data);
        }

        @Override
        public void onTextMessage(WebSocket websocket, String text) throws Exception {
            super.onTextMessage(websocket, text);
        }
    }
*/

}
