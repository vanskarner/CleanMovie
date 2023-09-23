package com.vanskarner.movie.businesslogic;

class MovieBO {
    private final int voteCount;
    private final float voteAverage;

    public MovieBO(int voteCount, float voteAverage) {
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
    }

    public boolean isRecommended() {
        return voteCount >= 75 && voteAverage >= 7.5;
    }

}