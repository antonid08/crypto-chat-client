package com.antonid.chatclient;

import com.antonid.chatclient.api.service.ApiProvider;
import com.antonid.chatclient.api.utils.EmptyCallback;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class FirebaseInstanceIdServiceImpl extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(final String token) {
        ApiProvider.getAuthApi().setFirebaseToken(token).enqueue(
                new EmptyCallback<Void>(getBaseContext()));
    }
}
