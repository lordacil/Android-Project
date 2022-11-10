package ujikom.rizkynugraha.com.tiket_kuy;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditBandara extends AppCompatActivity {

    private EditText mBandara, mIso;
    private Spinner mIdDesti;
    private String bandara, iso,idDesti;
    private int id;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private Menu action;
    private Bitmap bitmap;

    private InterfaceApi apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bandara);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mIdDesti = findViewById(R.id.txt_id_destination);
        mBandara = findViewById(R.id.txt_bandara);
        mIso = findViewById(R.id.txt_iso);

        adapter = new ArrayAdapter<String>(this, R.layout.daftar_desti, R.id.txt, listItems);
        mIdDesti.setAdapter(adapter);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        idDesti = intent.getStringExtra("id_destination");
        bandara = intent.getStringExtra("name");
        iso = intent.getStringExtra("iso");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            readMode();
            getSupportActionBar().setTitle("Edit " + idDesti.toString());

            mIdDesti.setPrompt(idDesti);
            mBandara.setText(bandara);
            mIso.setText(iso);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.logo);
            requestOptions.error(R.drawable.logo);


        } else {
            getSupportActionBar().setTitle("Add a bandara");
        }
    }

    public void onStart() {
        super.onStart();
        EditBandara.BackTask bt = new EditBandara.BackTask();
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
                HttpPost httppost = new HttpPost("http://forstone.web.id/tiket-kuy/cari_desti_id.php");
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


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id == 0){

            action.findItem(R.id.menu_edit).setVisible(false);
            action.findItem(R.id.menu_delete).setVisible(false);
            action.findItem(R.id.menu_save).setVisible(true);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();

                return true;
            case R.id.menu_edit:
                //Edit

                editMode();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mBandara, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;
            case R.id.menu_save:
                //Save

                if (id == 0) {

                    if (TextUtils.isEmpty(mIdDesti.getSelectedItem().toString()) ||
                            TextUtils.isEmpty(mBandara.getText().toString()) || TextUtils.isEmpty(mIso.getText().toString())) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                        alertDialog.setMessage("Please complete the field!");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }

                    else {

                        postData("insert");
                        action.findItem(R.id.menu_edit).setVisible(true);
                        action.findItem(R.id.menu_save).setVisible(false);
                        action.findItem(R.id.menu_delete).setVisible(true);

                        readMode();

                    }

                } else {

                    updateData("update", id);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;
            case R.id.menu_delete:

                AlertDialog.Builder dialog = new AlertDialog.Builder(EditBandara.this);
                dialog.setMessage("Delete this bandara?");
                dialog.setPositiveButton("Yes" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id);
                    }
                });
                dialog.setNegativeButton("Cencel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void postData(final String key) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        readMode();

        String idDesti = mIdDesti.getSelectedItem().toString().trim();
        String destination = mBandara.getText().toString().trim();
        String iso = mIso.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Bandara> call = apiInterface.insertBandara(key, idDesti,destination, iso);

        call.enqueue(new Callback<Bandara>() {
            @Override
            public void onResponse(Call<Bandara> call, Response<Bandara> response) {

                progressDialog.dismiss();

                Log.i(EditBandara.class.getSimpleName(), response.toString());

                String kode = response.body().getKode();
                String message = response.body().getMassage();

                if (kode.equals("1")){
                    Toast.makeText(EditBandara.this, "Tambah data berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditBandara.this, "Data Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Bandara> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditBandara.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String idDesti = mIdDesti.getSelectedItem().toString().trim();
        String bandara = mBandara.getText().toString().trim();
        String iso = mIso.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Bandara> call = apiInterface.updateBandara(key, id,idDesti,bandara,iso);

        call.enqueue(new Callback<Bandara>() {
            @Override
            public void onResponse(Call<Bandara> call, Response<Bandara> response) {

                progressDialog.dismiss();

                Log.i(EditBandara.class.getSimpleName(), response.toString());

                String kode = response.body().getKode();
                String message = response.body().getMassage();

                if (kode.equals("1")){
                    Toast.makeText(EditBandara.this, "Data berhasil diedit", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditBandara.this, "Data gagal diedit", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Bandara> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditBandara.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(String delete, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();
        readMode();

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Bandara> call = apiInterface.deleteBandara(id);

        call.enqueue(new Callback<Bandara>() {
            @Override
            public void onResponse(Call<Bandara> call, Response<Bandara> response) {

                progressDialog.dismiss();

                Log.i(EditBandara.class.getSimpleName(), response.toString());

                String kode = response.body().getKode();
                String message = response.body().getMassage();

                if (kode.equals("1")){
                    Toast.makeText(EditBandara.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditBandara.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Bandara> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditBandara.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    void readMode(){

        mIdDesti.setFocusableInTouchMode(false);
        mBandara.setFocusableInTouchMode(false);
        mIso.setFocusableInTouchMode(false);



    }

    private void editMode(){

        mIdDesti.setFocusableInTouchMode(true);
        mBandara.setFocusableInTouchMode(true);
        mIso.setFocusableInTouchMode(true);

    }

}