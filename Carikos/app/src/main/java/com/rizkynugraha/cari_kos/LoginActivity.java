package com.rizkynugraha.cari_kos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rizkynugraha.cari_kos.util.Server;
import com.rizkynugraha.cari_kos.app.AppController;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    Button btn_login,btn_lupa,txt_register;
    EditText txt_username,txt_password;
    Intent intent;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "login.php";
    private String url1 = Server.URL1 + "login1.php";

    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id_user";
    public final static String TAG_USERNAME1 = "username";
    public final static String TAG_ID1 = "id_admin";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedPreferences;
    Boolean session = false;
    Boolean session1 = false;

    String id;
    String username;
    String id1;
    String username1;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";
    String[] menu = {"pilih", "Admin", "User"};
    Spinner s11;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()){
            }else {
                Toast.makeText(getApplicationContext(), "Tidak ada koneksi Internet!",
                        Toast.LENGTH_LONG).show();
            }

        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        btn_login = findViewById(R.id.btn_login);
        btn_lupa = findViewById(R.id.lupaakun);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        txt_register = findViewById(R.id.register_caption);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                finish();
                startActivity(intent);
            }
        });
        btn_lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, ForgotAccountActivity.class);
                finish();
                startActivity(intent);
            }
        });


        // cek session Login jika TRUE maka langsung buka MenuActivity
        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        id = sharedPreferences.getString(TAG_ID, null);
        username = sharedPreferences.getString(TAG_USERNAME, null);
        id1 = sharedPreferences.getString(TAG_ID1, null);
        username1 = sharedPreferences.getString(TAG_USERNAME1, null);

        s11 = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu);
        s11.setAdapter(adapter);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animAlpha);
                int index = s11.getSelectedItemPosition();
                if (menu[index] == "Admin"){
                    String username = txt_username.getText().toString();
                    String password = txt_password.getText().toString();

                    // cek kolom kosong
                    if (username.trim().length() > 0 && password.trim().length() > 0){
                        if (conMgr.getActiveNetworkInfo() != null
                                && conMgr.getActiveNetworkInfo().isAvailable()
                                && conMgr.getActiveNetworkInfo().isConnected()){
                            checkLoginAdmin(username,password);
                        }else {
                            Toast.makeText(getApplicationContext(), "Tidak ada koneksi Internet",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                    }
                }else if (menu[index] == "User"){
                    String username = txt_username.getText().toString();
                    String password = txt_password.getText().toString();

                    // mengecek kolom yg kosong
                    if (username.trim().length() > 0 && password.trim().length() > 0){
                        if (conMgr.getActiveNetworkInfo() != null
                                && conMgr.getActiveNetworkInfo().isAvailable()
                                && conMgr.getActiveNetworkInfo().isConnected()){
                            checkLoginUser(username,password);
                        }else {
                            Toast.makeText(getApplicationContext(), "Tidak ada koneksi Internet", Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Maaf, anda belum memilih Role..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkLoginAdmin(final String username, final String password) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String username = jObj.getString(TAG_USERNAME1);
                        String id_admin = jObj.getString(TAG_ID1);

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID1, id_admin);
                        editor.putString(TAG_USERNAME1, username);
                        editor.commit();

                        // Memanggil main activity
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        intent.putExtra(TAG_ID1, id_admin);
                        intent.putExtra(TAG_USERNAME1, username);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void checkLoginUser(final String username, final String password) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Logging in ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Login Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {
                        String username = jObj.getString(TAG_USERNAME);
                        String id_user = jObj.getString(TAG_ID);

                        Log.e("Successfully Login!", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        // menyimpan login ke session
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(session_status, true);
                        editor.putString(TAG_ID, id_user);
                        editor.putString(TAG_USERNAME, username);
                        editor.commit();


                        // Memanggil main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra(TAG_ID, id_user);
                        intent.putExtra(TAG_USERNAME, username);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    private void showDialog(){
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    private void hideDialog(){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
