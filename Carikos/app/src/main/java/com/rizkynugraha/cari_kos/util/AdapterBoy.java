package com.rizkynugraha.cari_kos.util;

import android.annotation.SuppressLint;
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
import com.rizkynugraha.cari_kos.models.Boy;

import java.util.ArrayList;

public class AdapterBoy extends RecyclerView.Adapter<MyHolder> {
    private ArrayList<Boy> list;
    private Context context;

    public static ArrayList<Boy> setKos(ArrayList<Boy> kosanPutra){
        return kosanPutra;
    }

    public AdapterBoy(@NonNull Context context, ArrayList<Boy> list){
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
        final Boy boy = list.get(position);
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
        Intent intent=new Intent(context, kosBoyActivity.class);
        intent.putExtra("DATA_MOVIE",list.get(position));
        context.startActivity(intent);
    }

}
