package ujikom.rizkynugraha.com.tiket_kuy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterBandara extends RecyclerView.Adapter<AdapterBandara.MyViewHolder> implements Filterable{

    List<Bandara> bandara, bandaraFilter;
    private Context context;
    private AdapterBandara.RecyclerViewClickListener mListener;
    FilterBandara filter;

    public AdapterBandara(List<Bandara> bandara, Context context, AdapterBandara.RecyclerViewClickListener listener) {
        this.bandara = bandara;
        this.bandaraFilter = bandara;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public AdapterBandara.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bandara, parent, false);
        return new AdapterBandara.MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final AdapterBandara.MyViewHolder holder, int position) {


        holder.mName.setText(bandara.get(position).getNameBandara() + " / "
                + bandara.get(position).getId_destination());
        holder.mType.setText(bandara.get(position).getIso());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);


    }

    @Override
    public int getItemCount() {
        return bandara.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new FilterBandara((ArrayList<Bandara>) bandaraFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AdapterBandara.RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private ImageView mLove;
        private TextView mName, mType, mDate;
        private LinearLayout mRowContainer;

        public MyViewHolder(View itemView, AdapterBandara.RecyclerViewClickListener listener) {
            super(itemView);

            mName = itemView.findViewById(R.id.txt_bandara);
            mType = itemView.findViewById(R.id.txt_type);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
    }
    public interface ButtonClickListener{
        void onClick(View view, int position);
    }

}
