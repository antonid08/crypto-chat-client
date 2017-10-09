package com.antonid.chatclient.api.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.antonid.chatclient.R;

import retrofit2.Call;
import retrofit2.Callback;


public abstract class HandleErrorsCallback<T> implements Callback<T> {

    private Context context;

    public HandleErrorsCallback(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must be not null!");
        }

        this.context = context;
    }

    @CallSuper
    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Toast.makeText(context, R.string.api_error, Toast.LENGTH_SHORT).show();
    }

}
