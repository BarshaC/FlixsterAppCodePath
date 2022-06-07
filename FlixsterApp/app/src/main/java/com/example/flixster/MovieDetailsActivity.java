package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.flixster.R;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;
import org.w3c.dom.Text;

public class MovieDetailsActivity extends AppCompatActivity {
    private static final String TAG = "MovieDetailsActivity";

    Movie movie;
    // view objects to present the single movie when the user clicks
    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    ImageView ivPoster2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        ivPoster2 = (ImageView) findViewById(R.id.ivPoster2);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        // retrieve, unwrap, assign field from onCreate
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d(TAG, String.format("Showing details for '%s'", movie.getTitle()));

        // setting title and overview for the movie
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        // Set the RatingBar value by dividing Movie.getVoteAverage by 2.0
        String imageUrl = movie.getPoster_path();

        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage / 2.0f);
        Glide
                .with(this)
                .load(imageUrl)
                .transform(new RoundedCorners(30))
                .fitCenter()
                .into(ivPoster2);

        ivPoster2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                i.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                MovieDetailsActivity.this.startActivity(i);
            }
        });

    }
}
