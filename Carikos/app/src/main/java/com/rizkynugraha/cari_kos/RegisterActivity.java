package com.rizkynugraha.cari_kos;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rizkynugraha.cari_kos.app.AppController;
import com.rizkynugraha.cari_kos.util.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button btn_login,btn_register;
    Spinner txt_jenkel;
    Calendar mCurrentDate;
    int day,month,year;
    EditText txt_username,txt_password,txt_confirm_password,txt_email,txt_namauser,txt_alamatuser,txt_tglahir,txt_telepon;
    Intent intent;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "register.php";
    private String url1 = Server.URL1 + "register1.php";
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    String[] menu = {"pilih","User"};
    Spinner s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {

            } else {
                Toast.makeText(getApplicationContext(), "Tidak ada koneksi Internet", Toast.LENGTH_LONG).show();
            }
        }
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);
        txt_username = findViewById(R.id.txt_username);
        txt_password = findViewById(R.id.txt_password);
        txt_confirm_password = findViewById(R.id.txt_confirm_password);
        txt_email = findViewById(R.id.txt_email);
        txt_namauser = findViewById(R.id.txt_namauser);
        txt_alamatuser = findViewById(R.id.txt_alamatuser);
        txt_tglahir = findViewById(R.id.txt_tglahir);
        txt_jenkel = findViewById(R.id.txt_jenkel);
        txt_telepon = findViewById(R.id.txt_telepon);

        mCurrentDate = Calendar.getInstance();

        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        month = month+1;
        txt_tglahir.setText(year+"-"+month+"-"+day);
        txt_tglahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        txt_tglahir.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
        s1 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,menu);
        s1.setAdapter(adapter);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = s1.getSelectedItemPosition();
                if (menu[index] == "User"){
                    String username = txt_username.getText().toString();
                    String password = txt_password.getText().toString();
                    String confirm_password = txt_confirm_password.getText().toString();
                    String email = txt_email.getText().toString();
                    String namauser = txt_namauser.getText().toString();
                    String alamatuser = txt_alamatuser.getText().toString();
                    String tglahir = txt_tglahir.getText().toString();
                    String jenkel = txt_jenkel.getSelectedItem().toString();
                    String telepon = txt_telepon.getText().toString();

                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()){
                        checkRegisterClient(username, password, confirm_password,email,namauser,alamatuser,tglahir,jenkel,telepon);
                    }else{
                        Toast.makeText(getApplicationContext(), "Tidak ada koneksi Internet", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getBaseContext(), "Maaf, anda belum memilih User..!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkRegisterClient(final String username, final String password, final String confirm_password, final String email, final String namauser, final String alamatuser, final String tglahir, final String jenkel, final String telepon){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Register.....");
        showDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response " + response.toString());
                hideDialog();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getInt(TAG_SUCCESS);

                    if (success == 1) {

                        Log.e("Registrasi Berhasil!", jsonObject.toString());

                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txt_username.setText("");
                        txt_password.setText("");
                        txt_confirm_password.setText("");
                        txt_email.setText("");
                        txt_namauser.setText("");
                        txt_alamatuser.setText("");
                        txt_tglahir.setText("");
                        txt_jenkel.getSelectedItem();
                        txt_telepon.setText("");

                    } else {
                        Toast.makeText(getApplicationContext(), jsonObject.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                params.put("confirm_password", confirm_password);
                params.put("email", email);
                params.put("nama_user", namauser);
                params.put("alamat_user", alamatuser);
                params.put("tanggal_lahir", tglahir);
                params.put("jenis_kelamin", jenkel);
                params.put("telepon", telepon);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
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

    @Override
    public void onBackPressed(){
        intent = new Intent(RegisterActivity.this, LoginActivity.class);
        finish();
        startActivity(intent);
    }

}
