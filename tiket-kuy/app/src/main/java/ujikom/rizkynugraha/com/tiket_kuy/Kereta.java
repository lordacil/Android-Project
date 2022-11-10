package ujikom.rizkynugraha.com.tiket_kuy;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Kereta extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Calendar mCurrentDate;
    int day, month, year;
    Spinner dari;
    Spinner ke;
    TextView result;
    TextView user_nama;
    EditText destination,et_date;
    Button search;
    String id,username;
    private ProgressDialog pd;

    SharedPreferences sharedpreferences;
    public static final String TAG_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kereta);
        getSupportActionBar().hide();
        dari = (Spinner) findViewById(R.id.dari);
        ke = (Spinner) findViewById(R.id.tujuan);
        et_date = (EditText) findViewById(R.id.date);
        search = (Button) findViewById(R.id.search_buses);

        mCurrentDate = Calendar.getInstance();

        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        month = month+1;
        et_date.setText(year+"-"+month+"-"+day);

//        pd = new ProgressDialog(Kereta.this);
//        pd.setMessage("Loading..");
//        pd.setCancelable(false);
//        pd.setCanceledOnTouchOutside(false);
        adapter = new ArrayAdapter<String>(this, R.layout.daftar_desti, R.id.txt, listItems);
        dari.setAdapter(adapter);
        ke.setAdapter(adapter);
        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Kereta.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear+1;
                        et_date.setText(year+"-"+monthOfYear+"-"+dayOfMonth);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
     
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Kereta.this, Kereta2.class);

                startActivity(intent);
               // getSqlDetails();
            }
        });

    }


    public void onStart() {
        super.onStart();
        BackTask bt = new BackTask();
        bt.execute();
    }

    private class BackTask extends AsyncTask<Void, Void, Void> {
        ArrayList<String> list;
        protected void onPreExecute(){
            super.onPreExecute();
            list=new ArrayList<>();
        }
        protected Void doInBackground(Void... params) {
            InputStream is = null;
            String result = "";
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://forstone.web.id/tiket-kuy/cari_desti.php");
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();
                // Get our response as a String.
                is = entity.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //convert response to string
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    result += line;
                }
                is.close();
                //result=sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // parse json data
            try {
                JSONArray jArray = new JSONArray(result);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    // add interviewee name to arraylist
                    list.add(jsonObject.getString("destination"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result){
            listItems.addAll(list);
            adapter.notifyDataSetChanged();
        }

//        private void getSqlDetails() {
//
//            String url = "http://forstone.web.id/tiket-kuy/cari_desti.php?destination=" + id;
//            pd.show();
//            StringRequest stringRequest = new StringRequest(Request.Method.GET,
//                    url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            pd.hide();
//
//                            try {
//                                JSONArray jsonArray = new JSONArray(response);
//
//                                for (int i = 0; i < jsonArray.length(); i++) {
//
//                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                                    String id = jsonObject.getString("id");
//                                    String destination = jsonObject.getString("destination").trim();
//                                    String iso = jsonObject.getString("iso").trim();
//                                    // String picture = jsonObject.getString("picture").trim();
//                                    result.setText(" ID -" + id + "\n Destination -" + destination + "\n ISO -" + iso);
//
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            if (error != null) {
//                                Toast.makeText(getApplicationContext(), "Hmmmm ada yg salah nich", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    }
//            );
//
//            MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
//        }


    }
}


