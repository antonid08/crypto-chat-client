package com.antonid.chatclient.api.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Response;


public class EmptyCallback<T> extends HandleErrorsCallback<T> {

    public EmptyCallback(@NonNull Context context) {
        super(context);
    }

    @Override
    public void onResponse(Call call, Response response) {
        //nothing
    }
}
