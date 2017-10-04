package com.antonid.chatclient.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.antonid.chatclient.R;
import com.antonid.chatclient.SettingsServiceProvider;
import com.antonid.chatclient.api.service.ApiProvider;
import com.antonid.chatclient.api.utils.HandleErrorsCallback;
import com.antonid.chatclient.models.Encryption;
import com.antonid.chatclient.models.Settings;
import com.antonid.chatclient.models.User;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class AuthActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_activity);

        if (SettingsServiceProvider.getSettingsService(AuthActivity.this).load().getLoggedUser() != null) {
            ChooseInterlocutorActivity.start(AuthActivity.this);
            finish();
        }

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new OnLoginClick());
    }

    private class OnLoginClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String credentials = "username=" + username.getText().toString() + "&password=" +
                    password.getText().toString();

            RequestBody body = RequestBody
                    .create(MediaType.parse("application/x-www-form-urlencoded"), credentials);

            ApiProvider.getAuthApi().login(body).enqueue(new LoginCallback(AuthActivity.this));
        }
    }

    private class LoginCallback extends HandleErrorsCallback<ResponseBody> {

        LoginCallback(@NonNull Context context) {
            super(context);
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            int statusCode = response.code();

            if (statusCode == 200) {
                Settings settings = new Settings(new User(username.getText().toString(), Encryption.CAESAR));
                SettingsServiceProvider.getSettingsService(AuthActivity.this).save(settings);

                ChooseInterlocutorActivity.start(AuthActivity.this);
                finish();
            }
        }
    }
}
