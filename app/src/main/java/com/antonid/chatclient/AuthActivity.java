package com.antonid.chatclient;

import com.antonid.chatclient.api.service.ApiProvider;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new OnLoginClick());
    }

    private class OnLoginClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String credentials = "username=" + username.getText().toString() + "&password=" + password.getText().toString();

            RequestBody body = RequestBody
                .create(MediaType.parse("application/x-www-form-urlencoded"), credentials);

            ApiProvider.getChatApi().login(body).enqueue(new LoginCallback());
        }
    }

    private class LoginCallback implements Callback<ResponseBody> {

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            int statusCode = response.code();

            if (statusCode == 200) {
                ChooseInterlocutorActivity.start(AuthActivity.this);
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
        }
    }
}
