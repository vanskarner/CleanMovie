package com.vanskarner.localdata;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_detail")
class MovieEntity {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "encoded_image")
    public String encodedImage;
    @ColumnInfo(name = "encoded_background_image")
    public String encodedBackgroundImage;
    @ColumnInfo(name = "vote_count")
    public int voteCount;
    @ColumnInfo(name = "vote_average")
    public float voteAverage;
    @ColumnInfo(name = "release_date")
    public String releaseDate;
    @ColumnInfo(name = "overview")
    public String overview;

    public MovieEntity(int id, String title, String encodedImage,
                       String encodedBackgroundImage, int voteCount, float voteAverage,
                       String releaseDate, String overview) {
        this.id = id;
        this.title = title;
        this.encodedImage = encodedImage;
        this.encodedBackgroundImage = encodedBackgroundImage;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

}