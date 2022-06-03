package com.example.flixster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie {
    String backdropPath;
    String poster_path;
    String title;
    String overview;
    public Movie() {}
    Double voteAverage;


    public Movie(JSONObject movie) throws JSONException {
        backdropPath = movie.getString("backdrop_path");
        poster_path = movie.getString("poster_path");
        title = movie.getString("title");
        overview =movie.getString("overview");
        voteAverage = movie.getDouble("vote_average");
    }
    public static List<Movie> fromJSONArray(JSONArray movieJSONArray) throws JSONException {
        List <Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJSONArray.length(); i++){
            movies.add(new Movie(movieJSONArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPoster_path() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", poster_path);

    }
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }
}
