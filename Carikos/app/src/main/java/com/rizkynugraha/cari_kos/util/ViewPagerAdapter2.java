package com.rizkynugraha.cari_kos.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rizkynugraha.cari_kos.R;
import com.rizkynugraha.cari_kos.models.Model2;

import java.util.List;

public class ViewPagerAdapter2 extends PagerAdapter {

    private List<Model2> models2;
    private LayoutInflater layoutInflater;
    private Context context;

    public ViewPagerAdapter2(List<Model2> models2, Context context) {
        this.models2 = models2;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models2.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item2, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);

        imageView.setImageResource(models2.get(position).getImage());
        title.setText(models2.get(position).getTitle());
        desc.setText(models2.get(position).getDesc());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
