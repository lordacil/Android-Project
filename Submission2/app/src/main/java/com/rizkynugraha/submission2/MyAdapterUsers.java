package com.rizkynugraha.submission2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rizkynugraha.submission2.Model.User;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapterUsers extends RecyclerView.Adapter<MyAdapterUsers.MovieViewHolder>{
    private ArrayList<User> mData = new ArrayList<>();

    public void setData(ArrayList<User> items){
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model,viewGroup,false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgAvatar;
        TextView textLogin,textName,textId,textLocation;

        MovieViewHolder(@NonNull View itemView){
            super(itemView);
            textLogin = itemView.findViewById(R.id.nameTxt);
            textName = itemView.findViewById(R.id.txt_rate);
            textLocation = itemView.findViewById(R.id.txt_popular);
            imgAvatar = itemView.findViewById(R.id.img_photo);
            textId = itemView.findViewById(R.id.txt_year);

            itemView.setOnClickListener(this);
        }

        void bind(User users){
            String url_image = "https://avatars1.githubusercontent.com/u/"+users.getId();
            textName.setText(users.getName());
            textLogin.setText(users.getLogin());
            textLocation.setText(users.getLocation());
            textId.setText(users.getId());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.birumuda)
                    .dontAnimate()
                    .into(imgAvatar);
        }


        @Override
        public void onClick(View v) {
//            int position = getAdapterPosition();
//            Users movies = mData.get(position);
//
//            movies.setTitle(movies.getTitle());
//            movies.setOverview(movies.getOverview());
//
//            Intent moveWithObjectIntent = new Intent (itemView.getContext(), DetailActivityMovie.class);
//            moveWithObjectIntent.putExtra(DetailActivityMovie.EXTRA_MOVIE,movies);
//            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }
}
