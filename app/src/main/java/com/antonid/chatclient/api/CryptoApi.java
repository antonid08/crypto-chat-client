package com.antonid.chatclient.api;

import com.antonid.chatclient.models.Encryption;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CryptoApi {

    @POST("/set_symmetric_key")
    String setSymmetricKey(@Body Encryption encryption, @Body Object key);

}
