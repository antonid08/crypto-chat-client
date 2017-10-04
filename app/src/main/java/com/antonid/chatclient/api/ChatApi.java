package com.antonid.chatclient.api;


import com.antonid.chatclient.models.Message;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChatApi {
    @GET("/is_interlocutor_exists/{username}")
    Call<Boolean> isInterlocutorExists(@Path("username") String interlocutor);

    @POST("/message/to/{username}")
    Call<Void> sendMessage(@Path("username") String receiver, @Body Message message);

}
