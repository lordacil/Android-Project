package ujikom.rizkynugraha.com.tiket_kuy;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import ujikom.rizkynugraha.com.tiket_kuy.app.AppController;
import ujikom.rizkynugraha.com.tiket_kuy.util.Server;
import android.widget.ArrayAdapter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_register, btn_login;
    Spinner txt_jenkel;
    Calendar mCurrentDate;
    int day, month, year;
    EditText txt_username, txt_password, txt_confirm_password,txt_email,txt_namapenumpang,txt_alamatpenumpang,txt_tglahir,txt_telepon;
    Intent intent;

    int success;
    ConnectivityManager conMgr;

    private String url = Server.URL + "register.php";
    private String url1 = Server.URL1 + "register1.php";
    private String url2 = Server.URL2 + "register2.php";
    private static final String TAG = Register.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";
    String[] menu = {

            "pilih",
            "Penumpang"
    };


    Spinner s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        txt_username = (EditText) findViewById(R.id.txt_username);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_confirm_password = (EditText) findViewById(R.id.txt_confirm_password);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_namapenumpang = (EditText) findViewById(R.id.txt_namapenumpang);
        txt_alamatpenumpang = (EditText) findViewById(R.id.txt_alamatpenumpang);
        txt_tglahir = (EditText) findViewById(R.id.txt_tglahir);
        txt_jenkel = (Spinner) findViewById(R.id.txt_jenkel);
        txt_telepon = (EditText) findViewById(R.id.txt_telepon);

        mCurrentDate = Calendar.getInstance();

        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        month = month+1;

        txt_tglahir.setText(year+"-"+month+"-"+day);
        txt_tglahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Register.this, new DatePickerDialog.OnDateSetListener() {
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
                // TODO Auto-generated method stub
                intent = new Intent(Register.this, Login.class);
                finish();
                startActivity(intent);
            }
        });
        s1 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu);
        s1.setAdapter(adapter);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = s1.getSelectedItemPosition();
                if (menu[index] == "Penumpang") {
                    // TODO Auto-generated method stub
                    String username = txt_username.getText().toString();
                    String password = txt_password.getText().toString();
                    String confirm_password = txt_confirm_password.getText().toString();
                    String email = txt_email.getText().toString();
                    String namapenumpang = txt_namapenumpang.getText().toString();
                    String alamatpenumpang = txt_alamatpenumpang.getText().toString();
                    String tglahir = txt_tglahir.getText().toString();
                    String jenkel = txt_jenkel.getSelectedItem().toString();
                    String telepon = txt_telepon.getText().toString();

                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkRegisterClient(username, password, confirm_password,email,namapenumpang,alamatpenumpang,tglahir,jenkel,telepon);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(),
                            "Maaf, anda belum memilih User..!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void checkRegisterClient(final String username, final String password, final String confirm_password, final String email, final String namapenumpang, final String alamatpenumpang, final String tglahir, final String jenkel, final String telepon){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Check for error node in json
                    if (success == 1) {

                        Log.e("Successfully Register!", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txt_username.setText("");
                        txt_password.setText("");
                        txt_confirm_password.setText("");
                        txt_email.setText("");
                        txt_namapenumpang.setText("");
                        txt_alamatpenumpang.setText("");
                        txt_tglahir.setText("");
                        txt_jenkel.getSelectedItem();
                        txt_telepon.setText("");

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
                params.put("confirm_password", confirm_password);
                params.put("email", email);
                params.put("nama_penumpang", namapenumpang);
                params.put("alamat_penumpang", alamatpenumpang);
                params.put("tanggal_lahir", tglahir);
                params.put("jenis_kelamin", jenkel);
                params.put("telepon", telepon);

                return params;
            }

        };




        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


        private void showDialog () {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hideDialog () {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }

        @Override
        public void onBackPressed () {
            intent = new Intent(Register.this, Login.class);
            finish();
            startActivity(intent);

        }
}
