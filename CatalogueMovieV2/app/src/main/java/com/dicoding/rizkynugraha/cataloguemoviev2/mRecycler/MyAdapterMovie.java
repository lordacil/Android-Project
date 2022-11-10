package com.dicoding.rizkynugraha.cataloguemoviev2.mRecycler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.rizkynugraha.cataloguemoviev2.DetailActivityMovie;
import com.dicoding.rizkynugraha.cataloguemoviev2.model.Movies;
import com.dicoding.rizkynugraha.cataloguemoviev2.R;
import com.mlsdev.animatedrv.AnimatedRecyclerView;

import java.util.ArrayList;

public class MyAdapterMovie extends RecyclerView.Adapter<MyAdapterMovie.MovieViewHolder> {

    private ArrayList<Movies> mData = new ArrayList<>();

    public void setData(ArrayList<Movies> items){
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
        ImageView imgPhoto;
        TextView textViewTitle,textViewDateReleased,textViewVoteAverage,textViewVoteCount;

        MovieViewHolder(@NonNull View itemView){
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.nameTxt);
            textViewDateReleased = itemView.findViewById(R.id.txt_year);
            textViewVoteAverage = itemView.findViewById(R.id.txt_rate);
            textViewVoteCount = itemView.findViewById(R.id.txt_popular);
            imgPhoto = itemView.findViewById(R.id.img_photo);

            itemView.setOnClickListener(this);
        }

        void bind(Movies movies){
            String vote_average = Double.toString(movies.getVote_average());
            String url_image = "https://image.tmdb.org/t/p/w185"+movies.getPhoto();

            textViewTitle.setText(movies.getTitle());
            textViewDateReleased.setText(movies.getRelease_date());
            textViewVoteAverage.setText(vote_average);
            textViewVoteCount.setText(movies.getVote_count());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.birumuda)
                    .dontAnimate()
                    .into(imgPhoto);


        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movies movies = mData.get(position);

            movies.setTitle(movies.getTitle());
            movies.setOverview(movies.getOverview());

            Intent moveWithObjectIntent = new Intent (itemView.getContext(), DetailActivityMovie.class);
            moveWithObjectIntent.putExtra(DetailActivityMovie.EXTRA_MOVIE,movies);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }

}
