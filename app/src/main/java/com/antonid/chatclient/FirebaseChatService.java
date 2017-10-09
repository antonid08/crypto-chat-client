package com.antonid.chatclient;

import com.antonid.chatclient.gui.chat.MessageEvent;
import com.antonid.chatclient.models.Message;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;


public class FirebaseChatService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            Message message = new Gson().fromJson(remoteMessage.getData().toString(), Message.class);
            EventBus.getDefault().post(new MessageEvent(message));
        }
    }

}
