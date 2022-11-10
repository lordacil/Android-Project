package com.rizkynugraha.cari_kos;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForgotAccountActivity extends AppCompatActivity {

    TextView txtInfo;
    Button btn_lupa,btn_login;
    EditText username,password,confirm;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_account);
        getSupportActionBar().hide();

        txtInfo = findViewById(R.id.txtInfo);
        username = findViewById(R.id.txt_username);
        password = findViewById(R.id.txt_password);
        confirm = findViewById(R.id.txt_confirm_password);
        btn_lupa = findViewById(R.id.btn_lupa);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(ForgotAccountActivity.this,LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
        btn_lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);

                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://carikos.atwebpages.com/carikosdir/update_user.php").newBuilder();
                    urlBuilder.addQueryParameter("username", username.getText().toString().trim());
                    urlBuilder.addQueryParameter("password", username.getText().toString().trim());

                    String url = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Toast.makeText(getApplicationContext(),"Error!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onResponse(Call call,final Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        txtInfo.setText(response.body().string());
                                        readMode();
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    void readMode(){
        confirm.setText(null);
        username.setText(null);
        password.setText(null);
        confirm.setFocusableInTouchMode(false);
        username.setFocusableInTouchMode(false);
        password.setFocusableInTouchMode(false);
    }

}
