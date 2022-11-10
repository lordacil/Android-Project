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

public class AdapterProfileUser extends RecyclerView.Adapter<AdapterProfileUser.MyViewHolder> implements Filterable{

    List<user> users, userFilter;
    private Context context;
    private AdapterProfileUser.RecyclerViewClickListener mListener;
    FilterProfileUser filter;

    public AdapterProfileUser(List<user> users, Context context, AdapterProfileUser.RecyclerViewClickListener listener) {
        this.users = users;
        this.userFilter = users;
        this.context = context;
        this.mListener = listener;
    }

    @Override
    public AdapterProfileUser.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_profileuser, parent, false);
        return new AdapterProfileUser.MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final AdapterProfileUser.MyViewHolder holder, int position) {

        holder.mName.setText(users.get(position).getUsername());
        holder.mType.setText(users.get(position).getNama_penumpang());
        holder.mEmail.setText(users.get(position).getEmail());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(users.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new FilterProfileUser((ArrayList<user>) userFilter,this);

        }
        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AdapterProfileUser.RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private ImageView mLove;
        private TextView mName, mType,mEmail, mDate;
        private LinearLayout mRowContainer;

        public MyViewHolder(View itemView, AdapterProfileUser.RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.picture);
            mName = itemView.findViewById(R.id.name);
            mType = itemView.findViewById(R.id.type);
            mEmail =itemView.findViewById(R.id.email);
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
