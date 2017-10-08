package com.antonid.chatclient.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthApi {

    @POST("/login")
    Call<ResponseBody> login(@Body RequestBody credentials);

    @POST("/set_firebase_token")
    Call<Void> setFirebaseToken(@Body String token);
}
