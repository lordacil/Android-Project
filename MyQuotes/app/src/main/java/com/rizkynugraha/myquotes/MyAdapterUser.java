package com.rizkynugraha.myquotes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapterUser extends RecyclerView.Adapter<MyAdapterUser.MovieViewHolder>{
    private ArrayList<Users> mData = new ArrayList<>();

    public void setData(ArrayList<Users> items){
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
        TextView textLogin,textType,textId;

        MovieViewHolder(@NonNull View itemView){
            super(itemView);
            textLogin = itemView.findViewById(R.id.nameTxt);
            textType = itemView.findViewById(R.id.txt_rate);
            imgAvatar = itemView.findViewById(R.id.img_photo);
            textId = itemView.findViewById(R.id.txt_popular);

            itemView.setOnClickListener(this);
        }

        void bind(Users users){
            String url_image = "https://avatars1.githubusercontent.com/u/"+users.getId();

            textLogin.setText(users.getLogin());
            textType.setText(users.getType());

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
