package com.antonid.chatclient.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("/login")
    Call<ResponseBody> login(@Body RequestBody credentials);

}
