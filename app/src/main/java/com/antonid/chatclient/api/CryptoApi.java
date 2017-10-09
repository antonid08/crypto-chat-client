package com.antonid.chatclient.api;

import com.antonid.chatclient.models.Encryption;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CryptoApi {

    @POST("/set_encryption")
    Call<Void> setEncryption(@Body Encryption encryption);

}
