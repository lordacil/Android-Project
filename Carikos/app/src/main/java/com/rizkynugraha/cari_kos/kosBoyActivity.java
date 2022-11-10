package com.rizkynugraha.cari_kos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rizkynugraha.cari_kos.models.Boy;

public class kosBoyActivity extends AppCompatActivity {

    TextView txt_desc,txt_harga,txt_nama,txt_pemilik,txt_telepon;
    ImageView img_photo,img_cover;
    Button btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kos_boy);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        Boy kosanPutra = intent.getParcelableExtra("DATA_MOVIE");

        btnPesan = findViewById(R.id.btn_pesan);
        txt_desc = findViewById(R.id.txt_desc);
        txt_harga = findViewById(R.id.txt_harga);
        txt_nama = findViewById(R.id.txt_nama);
        txt_pemilik = findViewById(R.id.txt_pemilik);
        txt_telepon = findViewById(R.id.txt_telepon);
        img_photo = findViewById(R.id.img_kosan);
        img_cover = findViewById(R.id.cover);

        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(kosBoyActivity.this, DonationActivity.class);
                startActivity(intents);
            }
        });

        String desc = kosanPutra.getDesc_kos();
        String harga = kosanPutra.getPrice_kos();
        String nama = kosanPutra.getName_kos();
        String pemilik = kosanPutra.getOwner_kos();
        String telepon = kosanPutra.getNo_kos();
        txt_desc.setText(desc);
        txt_harga.setText(harga);
        txt_nama.setText(nama);
        txt_pemilik.setText(pemilik);
        txt_telepon.setText(telepon);
        Glide.with(this)
                .load(kosanPutra.getImg_kos())
                .apply(new RequestOptions())
                .into(img_photo);
        Glide.with(this)
                .load(kosanPutra.getCover_kos())
                .apply(new RequestOptions())
                .into(img_cover);
    }
}