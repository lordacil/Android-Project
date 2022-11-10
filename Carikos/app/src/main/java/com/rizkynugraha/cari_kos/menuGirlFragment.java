package com.rizkynugraha.cari_kos;

import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rizkynugraha.cari_kos.models.Boy;
import com.rizkynugraha.cari_kos.models.Girl;
import com.rizkynugraha.cari_kos.util.AdapterBoy;
import com.rizkynugraha.cari_kos.util.AdapterGirl;

import java.util.ArrayList;
import java.util.Objects;


public class menuGirlFragment extends Fragment {

    private String[] dataDesc,dataPrice,dataName,dataPhoto,dataCover,
            dataOwner,dataNo;
    ArrayList<Girl> list = new ArrayList<>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu_girl, container, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.girlRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        prepare();
        addItem();
        AdapterGirl adapter = new AdapterGirl(Objects.requireNonNull(this.getActivity()), list);
        recyclerView.setAdapter(adapter);
        return rootView;

    }

    private void addItem() {
        list = new ArrayList<>();

        for (int i = 0; i < dataDesc.length; i++) {
            Girl boy = new Girl(Parcel.obtain());
            boy.setImg_kos(dataPhoto[i]);
            boy.setDesc_kos(dataDesc[i]);
            boy.setPrice_kos(dataPrice[i]);
            boy.setName_kos(dataName[i]);
            boy.setCover_kos(dataCover[i]);
            boy.setOwner_kos(dataOwner[i]);
            boy.setNo_kos(dataNo[i]);
            list.add(boy);
        }

        AdapterGirl.setKos(list);
    }

    public void prepare(){
        dataDesc = getResources().getStringArray(R.array.desc_kosan_putri);
        dataPrice = getResources().getStringArray(R.array.biaya_kosan_putri);
        dataName = getResources().getStringArray(R.array.nama_kosan_putri);
        dataPhoto = getResources().getStringArray(R.array.gambar_kosan_putri);
        dataCover = getResources().getStringArray(R.array.cover_kosan_putri);
        dataOwner = getResources().getStringArray(R.array.pemilik_kosan_putri);
        dataNo = getResources().getStringArray(R.array.telepon_kosan_putri);
    }

}