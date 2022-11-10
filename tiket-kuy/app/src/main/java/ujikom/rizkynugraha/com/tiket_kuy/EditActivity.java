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

public class EditActivity extends AppCompatActivity {

    private EditText mKode, mNama, mJumlah,mKet,mIdtype;
    private CircleImageView mPicture;
    private FloatingActionButton mFabChoosePic;

    private String kode, nama, jumlah,ket,Idtype, picture;
    private int id_transportasi;

    private Menu action;
    private Bitmap bitmap;

    private InterfaceApi apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mKode = findViewById(R.id.kode);
        mJumlah = findViewById(R.id.jumlahkursi);
        mNama = findViewById(R.id.namatransportasi);
        mKet = findViewById(R.id.ket);
        mIdtype = findViewById(R.id.idtrans);
        mPicture = findViewById(R.id.picture);
        mFabChoosePic = findViewById(R.id.fabChoosePic);

        mFabChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });

        Intent intent = getIntent();
        id_transportasi = intent.getIntExtra("id_transportasi", 0);
        kode = intent.getStringExtra("kode");
        jumlah = intent.getStringExtra("jumlah_kursi");
        nama = intent.getStringExtra("nama_type");
        ket = intent.getStringExtra("keterangan");
        Idtype = intent.getStringExtra("id_type_transportasi");
        picture = intent.getStringExtra("picture");

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {

        if (id_transportasi != 0) {

            readMode();
            getSupportActionBar().setTitle("Edit " + kode.toString());

            mKode.setText(kode);
            mJumlah.setText(jumlah);
            mNama.setText(nama);
            mKet.setText(ket);
            mIdtype.setText(Idtype);

            RequestOptions requestOptions = new RequestOptions();
            requestOptions.skipMemoryCache(true);
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
            requestOptions.placeholder(R.drawable.logo);
            requestOptions.error(R.drawable.logo);

            Glide.with(EditActivity.this)
                    .load(picture)
                    .apply(requestOptions)
                    .into(mPicture);

        } else {
            getSupportActionBar().setTitle("Add a Transportasi");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        action = menu;
        action.findItem(R.id.menu_save).setVisible(false);

        if (id_transportasi == 0){

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
                imm.showSoftInput(mKode, InputMethodManager.SHOW_IMPLICIT);

                action.findItem(R.id.menu_edit).setVisible(false);
                action.findItem(R.id.menu_delete).setVisible(false);
                action.findItem(R.id.menu_save).setVisible(true);

                return true;
            case R.id.menu_save:
                //Save

                if (id_transportasi == 0) {

                    if (TextUtils.isEmpty(mKode.getText().toString()) ||
                            TextUtils.isEmpty(mJumlah.getText().toString()) ||
                            TextUtils.isEmpty(mNama.getText().toString()) ||
                            TextUtils.isEmpty(mKet.getText().toString()) ||
                            TextUtils.isEmpty(mIdtype.getText().toString()) ){
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

                    updateData("update",id_transportasi);
                    action.findItem(R.id.menu_edit).setVisible(true);
                    action.findItem(R.id.menu_save).setVisible(false);
                    action.findItem(R.id.menu_delete).setVisible(true);

                    readMode();
                }

                return true;
            case R.id.menu_delete:

                AlertDialog.Builder dialog = new AlertDialog.Builder(EditActivity.this);
                dialog.setMessage("Delete this Trans?");
                dialog.setPositiveButton("Yes" ,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        deleteData("delete", id_transportasi);
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

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                mPicture.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void postData(String insert) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        readMode();

        String kode = mKode.getText().toString().trim();
        String jumlah = mJumlah.getText().toString().trim();
        String nama = mNama.getText().toString().trim();
        String ket = mKet.getText().toString().trim();
        String Idtype = mIdtype.getText().toString().trim();
        String picture = null;
        if (bitmap == null) {
            picture = "";
        } else {
            picture = getStringImage(bitmap);
        }

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Transportasi> call = apiInterface.insertTransportasi(kode, jumlah, nama,ket,Idtype, picture);

        call.enqueue(new Callback<Transportasi>() {
            @Override
            public void onResponse(Call<Transportasi> call, Response<Transportasi> response) {

                progressDialog.dismiss();

                Log.i(EditActivity.class.getSimpleName(), response.toString());

                String kodena = response.body().getKodena();
                String message = response.body().getMassage();

                if (kodena.equals("1")){
                    Toast.makeText(EditActivity.this, "Data berhasil ditambah", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Data berhasil ditambah", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Transportasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateData(final String key, final int id_transportasi) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        readMode();

        String kode = mKode.getText().toString().trim();
        String jumlah = mJumlah.getText().toString().trim();
        String nama = mNama.getText().toString().trim();
        String ket = mKet.getText().toString().trim();
        String Idtype = mIdtype.getText().toString().trim();
        String picture = null;
        if (bitmap == null) {
            picture = "";
        } else {
            picture = getStringImage(bitmap);
        }

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Transportasi> call = apiInterface.updateTransportasi(key,id_transportasi,kode, jumlah, nama, ket,Idtype, picture);

        call.enqueue(new Callback<Transportasi>() {
            @Override
            public void onResponse(Call<Transportasi> call, Response<Transportasi> response) {

                progressDialog.dismiss();

                Log.i(EditActivity.class.getSimpleName(), response.toString());

                String kodena = response.body().getKodena();
                String message = response.body().getMassage();

                if (kodena.equals("1")){
                    Toast.makeText(EditActivity.this, "Data berhasil diedit", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditActivity.this, "Data berhasil diedit", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Transportasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData(final String key, final int id) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Deleting...");
        progressDialog.show();

        readMode();

        apiInterface = ApiClient.getApiClient().create(InterfaceApi.class);

        Call<Transportasi> call = apiInterface.deletePet(id);

        call.enqueue(new Callback<Transportasi>() {
            @Override
            public void onResponse(Call<Transportasi> call, Response<Transportasi> response) {

                progressDialog.dismiss();

                Log.i(EditActivity.class.getSimpleName(), response.toString());

                String kodena = response.body().getKodena();
                String message = response.body().getMassage();

                if (kodena.equals("1")){
                    Toast.makeText(EditActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditActivity.this, "Data gagal dihapus", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Transportasi> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    void readMode(){

        mKode.setFocusableInTouchMode(false);
        mJumlah.setFocusableInTouchMode(false);
        mNama.setFocusableInTouchMode(false);
        mKet.setFocusableInTouchMode(false);
        mIdtype.setFocusableInTouchMode(false);
        mKode.setFocusable(false);
        mJumlah.setFocusable(false);
        mNama.setFocusable(false);
        mKet.setFocusable(false);
        mIdtype.setFocusable(false);

        mFabChoosePic.setVisibility(View.INVISIBLE);

    }

    private void editMode(){

        mKode.setFocusableInTouchMode(true);
        mJumlah.setFocusableInTouchMode(true);
        mNama.setFocusableInTouchMode(true);
        mKet.setFocusableInTouchMode(true);
        mIdtype.setFocusableInTouchMode(true);

        mFabChoosePic.setVisibility(View.VISIBLE);
    }

}
