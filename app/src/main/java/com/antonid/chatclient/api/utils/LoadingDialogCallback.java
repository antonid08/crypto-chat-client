package com.antonid.chatclient.api.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Response;

public abstract class LoadingDialogCallback<T> extends HandleErrorsCallback<T> {

    private ProgressDialog loadingDialog;

    public LoadingDialogCallback(@NonNull Context context) {
        super(context);

        loadingDialog = new ProgressDialog(context);
        loadingDialog.setIndeterminate(true);
        loadingDialog.setCanceledOnTouchOutside(false);
        loadingDialog.setMessage("Loading...");
        loadingDialog.show();
    }

    @CallSuper
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        super.onFailure(call, t);

        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
