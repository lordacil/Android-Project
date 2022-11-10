package com.rizkynugraha.cari_kos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView desc_kosan,harga_kosan,nama_kosan;
    public ImageView img_kosan;
    private ItemClickListener itemClickListener;
    public MyHolder(View itemView){
        super(itemView);

        desc_kosan = itemView.findViewById(R.id.deskripsi_kosan);
        harga_kosan = itemView.findViewById(R.id.harga_kosan);
        nama_kosan = itemView.findViewById(R.id.nama_kosan);
        img_kosan = itemView.findViewById(R.id.image_kosan);

    itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }

}
