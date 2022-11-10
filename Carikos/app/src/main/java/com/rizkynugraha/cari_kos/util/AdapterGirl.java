package com.rizkynugraha.cari_kos.util;

import android.annotation.SuppressLint;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.rizkynugraha.cari_kos.ItemClickListener;
import com.rizkynugraha.cari_kos.MyHolder;
import com.rizkynugraha.cari_kos.R;
import com.rizkynugraha.cari_kos.kosBoyActivity;
import com.rizkynugraha.cari_kos.kosGirlActivity;
import com.rizkynugraha.cari_kos.models.Boy;
import com.rizkynugraha.cari_kos.models.Girl;

import java.util.ArrayList;

public class AdapterGirl extends RecyclerView.Adapter<MyHolder> {

    private ArrayList<Girl> list;
    private Context context;

    public static ArrayList<Girl> setKos(ArrayList<Girl> kosanPutri){
        return kosanPutri;
    }

    public AdapterGirl(@NonNull Context context, ArrayList<Girl> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, @SuppressLint("RecyclerView") final int position){
        final Girl boy = list.get(position);
        Glide.with(holder.itemView.getContext())
                .load(boy.getImg_kos())
                .into(holder.img_kosan);
        holder.desc_kosan.setText(boy.getDesc_kos());
        holder.harga_kosan.setText(boy.getPrice_kos());
        holder.nama_kosan.setText(boy.getName_kos());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void openDetailActivity(int position){
        Intent intent=new Intent(context, kosGirlActivity.class);
        intent.putExtra("DATA_MOVIE",list.get(position));
        context.startActivity(intent);
    }
}
