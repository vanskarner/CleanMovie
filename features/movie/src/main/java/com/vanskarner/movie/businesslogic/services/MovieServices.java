package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.core.concurrent.FutureResult;
import com.vanskarner.core.sync.Result;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.businesslogic.ds.MoviesDS;
import com.vanskarner.movie.businesslogic.ds.MoviesFilterDS;

public interface MovieServices {

    FutureResult<Boolean> toggleFavorite(MovieDetailDS detailDS);

    FutureResult<Boolean> checkFavorite(int movieId);

    FutureResult<Integer> deleteAllFavorite();

    Result<MoviesFilterDS> filterUpcoming(MoviesFilterDS moviesFilterDS);

    FutureResult<MovieDetailDS> findFavorite(int movieId);

    FutureResult<MovieDetailDS> findUpcoming(int movieId);

    FutureResult<MoviesDS> showFavorite();

    FutureResult<MoviesDS> showUpcoming(int page);

    void clear();

}