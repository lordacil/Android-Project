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

public class Adapters extends RecyclerView.Adapter<Adapters.MyViewHolder> implements Filterable{

    List<Transportasi> transportasi, transportasiFilter;
    private Context context;
    private Adapters.RecyclerViewClickListener mListener;
    KustonFilter filter;

    public Adapters(List<Transportasi> transportasi, Context context, Adapters.RecyclerViewClickListener listener) {
        this.transportasi = transportasi;
        this.transportasiFilter = transportasi;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public Adapters.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_trans, parent, false);
        return new Adapters.MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final Adapters.MyViewHolder holder, int position) {

        holder.mName.setText(transportasi.get(position).getKode());
        holder.mType.setText(transportasi.get(position).getJumlah_kursi() + " / "
                + transportasi.get(position).getNama_type());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(transportasi.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture);

    }

    @Override
    public int getItemCount() {
        return transportasi.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new KustonFilter((ArrayList<Transportasi>) transportasiFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Adapters.RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private ImageView mLove;
        private TextView mName, mType, mDate;
        private LinearLayout mRowContainer;

        public MyViewHolder(View itemView, Adapters.RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.picture);
            mName = itemView.findViewById(R.id.name);
            mType = itemView.findViewById(R.id.type);
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
}
