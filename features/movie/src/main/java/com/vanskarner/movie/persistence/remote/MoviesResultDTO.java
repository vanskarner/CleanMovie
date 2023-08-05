package com.vanskarner.movie.persistence.remote;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

class MoviesResultDTO implements Serializable {

    @SerializedName("results")
    List<MovieDTO> results;

    public MoviesResultDTO(List<MovieDTO> results) {
        this.results = results;
    }

}