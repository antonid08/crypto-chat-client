package com.antonid.chatclient;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FirebaseChatService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

/*        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            String title = remoteMessage.getData().get("title");
            String message = remoteMessage.getData().get("text");
            String username = remoteMessage.getData().get("username");
            String uid = remoteMessage.getData().get("uid");
            String fcmToken = remoteMessage.getData().get("fcm_token");

            EventBus.getDefault().post(new PushNotificationEvent(title,
                    message,
                    username,
                    uid,
                    fcmToken));
        }*/
    }


}
