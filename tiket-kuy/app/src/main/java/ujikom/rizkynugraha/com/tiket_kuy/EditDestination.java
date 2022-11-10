package ujikom.rizkynugraha.com.tiket_kuy;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDestination extends AppCompatActivity {

    private EditText mDestination, mIso;

    private String destination, iso;
    private int id;

    private Menu action;
    private Bitmap bitmap;

    private InterfaceApi apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_destination);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDestination = findViewById(R.id.txt_destination);
        mIso = findViewById(R.id.txt_iso);


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        destination = intent.getStringExtra("destination");
        iso = intent.getStringExtra("iso");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            readMode();
            getSupportActionBar().setTitle("Edit " + destination.toString());

            mDestination.setText(destination);
            mIso.setText(iso);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.logo);
            requestOptions.error(R.drawable.logo);


        } else {
            getSupportActionBar().setTitle("Add a Destination");
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
                imm.showSoftInput(mDestination, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;
            case R.id.menu_save:
                //Save

                if (id == 0) {

                    if (TextUtils.isEmpty(mDestination.getText().toString()) ||
                            TextUtils.isEmpty(mIso.getText().toString())) {
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

                AlertDialog.Builder dialog = new AlertDialog.Builder(EditDestination.this);
                dialog.setMessage("Delete this pet?");
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

        String destination = mDestination.getText().toString().trim();
        String iso = mIso.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Destination> call = apiInterface.insertDestination(key, destination, iso);

        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {

                progressDialog.dismiss();

                Log.i(EditDestination.class.getSimpleName(), response.toString());

                String kode = response.body().getKode();
                String message = response.body().getMassage();

                if (kode.equals("1")){
                    Toast.makeText(EditDestination.this, "Tambah data berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditDestination.this, "Data Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditDestination.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String destination = mDestination.getText().toString().trim();
        String iso = mIso.getText().toString().trim();

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Destination> call = apiInterface.updateDestination(key, id,destination,iso);

        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {

                progressDialog.dismiss();

                Log.i(EditDestination.class.getSimpleName(), response.toString());

                String kode = response.body().getKode();
                String message = response.body().getMassage();

                if (kode.equals("1")){
                    Toast.makeText(EditDestination.this, "Data berhasil diedit", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditDestination.this, "Data gagal diedit", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditDestination.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(String delete, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();
        readMode();

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Destination> call = apiInterface.deleteDestination(id);

        call.enqueue(new Callback<Destination>() {
            @Override
            public void onResponse(Call<Destination> call, Response<Destination> response) {

                progressDialog.dismiss();

                Log.i(EditActivity.class.getSimpleName(), response.toString());

                String kode = response.body().getKode();
                String message = response.body().getMassage();

                if (kode.equals("1")){
                    Toast.makeText(EditDestination.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditDestination.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Destination> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditDestination.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    void readMode(){

        mDestination.setFocusableInTouchMode(false);
        mIso.setFocusableInTouchMode(false);



    }

    private void editMode(){

        mDestination.setFocusableInTouchMode(true);
        mIso.setFocusableInTouchMode(true);

    }

}
