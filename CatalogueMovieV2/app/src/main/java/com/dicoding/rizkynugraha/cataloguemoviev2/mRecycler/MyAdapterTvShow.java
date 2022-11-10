package com.dicoding.rizkynugraha.cataloguemoviev2.mRecycler;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.rizkynugraha.cataloguemoviev2.DetailActivityTvShow;
import com.dicoding.rizkynugraha.cataloguemoviev2.R;
import com.dicoding.rizkynugraha.cataloguemoviev2.model.TvShow;

import java.util.ArrayList;

public class MyAdapterTvShow extends RecyclerView.Adapter<MyAdapterTvShow.TVShowViewHolder> {

    private ArrayList<TvShow> tvData = new ArrayList<>();

    public void setTvData(ArrayList<TvShow> items) {
        tvData.clear();
        tvData.addAll(items);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyAdapterTvShow.TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View tvView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model,viewGroup,false);
        return new TVShowViewHolder(tvView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterTvShow.TVShowViewHolder tvShowViewHolder, int position) {
        tvShowViewHolder.bind(tvData.get(position));
    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPoster;
        TextView textViewName, textViewAirDate,
                textViewVoteAverage,
                textViewVoteCount;

        TVShowViewHolder(@NonNull View itemView){
            super(itemView);
            textViewName = itemView.findViewById(R.id.nameTxt);
            textViewAirDate = itemView.findViewById(R.id.txt_year);
            textViewVoteAverage = itemView.findViewById(R.id.txt_rate);
            textViewVoteCount = itemView.findViewById(R.id.txt_popular);
            imgPoster = itemView.findViewById(R.id.img_photo);

            itemView.setOnClickListener(this);
        }

        void bind(TvShow tvShow) {
            String vote_average = Double.toString(tvShow.getVote_average());
            String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPoster_path();


            textViewName.setText(tvShow.getName());
            textViewAirDate.setText(tvShow.getFirst_air_date());
            textViewVoteAverage.setText(vote_average);
            textViewVoteCount.setText(tvShow.getVote_count());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .into(imgPoster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TvShow tvShow = tvData.get(position);

            tvShow.setName(tvShow.getName());
            tvShow.setOverview(tvShow.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), DetailActivityTvShow.class);
            moveWithObjectIntent.putExtra(DetailActivityTvShow.EXTRA_TV_SHOW, tvShow);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }

}
