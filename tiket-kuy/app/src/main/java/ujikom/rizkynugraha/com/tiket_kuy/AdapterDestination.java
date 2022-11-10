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

public class AdapterDestination extends RecyclerView.Adapter<AdapterDestination.MyViewHolder> implements Filterable{

    List<Destination> destination, destinationFilter;
    private Context context;
    private AdapterDestination.RecyclerViewClickListener mListener;
    FilterDestination filter;

    public AdapterDestination(List<Destination> transportasi, Context context, AdapterDestination.RecyclerViewClickListener listener) {
        this.destination = transportasi;
        this.destinationFilter = transportasi;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public AdapterDestination.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_destination, parent, false);
        return new AdapterDestination.MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final AdapterDestination.MyViewHolder holder, int position) {

        holder.mName.setText(destination.get(position).getDestination());
        holder.mType.setText(destination.get(position).getIso());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);


    }

    @Override
    public int getItemCount() {
        return destination.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new FilterDestination((ArrayList<Destination>) destinationFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AdapterDestination.RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private ImageView mLove;
        private TextView mName, mType, mDate;
        private LinearLayout mRowContainer;

        public MyViewHolder(View itemView, AdapterDestination.RecyclerViewClickListener listener) {
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
