package com.antonid.chatclient.api;

import com.antonid.chatclient.models.Encryption;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CryptoApi {

    @GET("/get_symmetric_session_key/{algorithm}")
    String getSymmetricSessionKey(@Path("algorithm") Encryption algorithm);

}
