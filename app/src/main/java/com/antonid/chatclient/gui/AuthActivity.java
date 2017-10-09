package com.antonid.chatclient.gui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.antonid.chatclient.R;
import com.antonid.chatclient.SettingsServiceProvider;
import com.antonid.chatclient.api.service.ApiProvider;
import com.antonid.chatclient.api.utils.EmptyCallback;
import com.antonid.chatclient.api.utils.LoadingDialogCallback;
import com.antonid.chatclient.models.Encryption;
import com.antonid.chatclient.models.Settings;
import com.antonid.chatclient.models.User;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Random;

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

/*        if (SettingsServiceProvider.getSettingsService(AuthActivity.this).load().getLoggedUser() != null) {
            ChooseInterlocutorActivity.start(AuthActivity.this);
            finish();
        }*/

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

            ApiProvider.getAuthApi().login(body)
                    .enqueue(new LoginCallback(username.getText().toString(), AuthActivity.this));
        }
    }

    private class LoginCallback extends LoadingDialogCallback<ResponseBody> {

        private String username;

        LoginCallback(String username, @NonNull Context context) {
            super(context);

            this.username = username;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            super.onResponse(call, response);

            int statusCode = response.code();
            if (statusCode == 200) {
                setFirebaseToken(FirebaseInstanceId.getInstance().getToken());

                setLoggedUser(username);

                ChooseInterlocutorActivity.start(AuthActivity.this);
                finish();
            } else {
                Toast.makeText(AuthActivity.this, "Bad credentials.", Toast.LENGTH_SHORT).show();
            }
        }

        private void setFirebaseToken(String token) {
            ApiProvider.getAuthApi().setFirebaseToken(token)
                    .enqueue(new EmptyCallback<Void>(AuthActivity.this));
        }

        private void setLoggedUser(String username) {
            //fixme пока что устанавливаем цезаря при логине
            String caesarKey = String.valueOf(new Random().nextInt(100000));
            Encryption encryption = new Encryption(Encryption.Type.CAESAR,
                    new Encryption.Key(caesarKey));

            ApiProvider.getCryptoApi().setEncryption(encryption)
                    .enqueue(new EmptyCallback<Void>(AuthActivity.this));

            Settings settings = Settings.getDefault();
            User user = new User(username, encryption);
            settings.setLoggedUser(user);
            SettingsServiceProvider.getSettingsService(AuthActivity.this).save(settings);
        }

    }
}
