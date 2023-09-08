package com.vanskarner.cleanmovie.ui.movie.upcomingDetail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.cleanmovie.databinding.UpcomingDetailFragmentBinding;
import com.vanskarner.cleanmovie.ui.errors.custom.ErrorDialog;
import com.vanskarner.cleanmovie.ui.movie.MovieDetailModel;
import com.vanskarner.cleanmovie.ui.movie.MovieViewMapper;
import com.vanskarner.movie.businesslogic.ds.MovieDetailDS;
import com.vanskarner.movie.presentation.ErrorView;
import com.vanskarner.movie.presentation.upcomingDetail.UpcomingDetailContract;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class UpcomingDetailFragment extends DaggerFragment implements UpcomingDetailContract.view {
    @Inject
    UpcomingDetailContract.presenter presenter;
    @Inject
    ErrorDialog errorDialog;
    UpcomingDetailFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = UpcomingDetailFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        configNavToolbar();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.initialAction(getMovieId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.asyncCancel();
        binding = null;
    }

    @Override
    public void setReadyViews(boolean readyViews) {
        binding.upcomingDetailToolbar.getMenu()
                .findItem(R.id.favoriteMenuItem)
                .setVisible(readyViews);
        binding.upcomingDetailProgress.setVisibility(readyViews ? View.GONE : View.VISIBLE);
        binding.viewDetailContent.setVisibility(readyViews ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setMarkedAsFavorite(boolean markedAsFavorite) {
        binding.upcomingDetailToolbar.getMenu()
                .findItem(R.id.favoriteMenuItem)
                .setIcon(markedAsFavorite ?
                        R.drawable.ic_favorite_24 : R.drawable.ic_favorite_border_24);
    }

    @Override
    public void showUpcomingDetail(MovieDetailDS movieDetailDS) {
        MovieDetailModel movieDetailModel = MovieViewMapper.convert(movieDetailDS);
        binding.setMovieDetail(movieDetailModel);
    }

    @Override
    public void showError(ErrorView<?> errorView) {
        errorDialog.setError(errorView, () -> {
            errorDialog.dismiss();
            requireActivity().onBackPressed();
        });
        errorDialog.setCancelable(false);
        errorDialog.show(getChildFragmentManager());
    }

    private void configNavToolbar() {
        Toolbar toolbar = binding.upcomingDetailToolbar;
        NavController navController = Navigation.findNavController(binding.getRoot());
        AppBarConfiguration barConfiguration = new AppBarConfiguration
                .Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(toolbar, navController, barConfiguration);
        toolbar.setOnMenuItemClickListener(this::onClickHeartMenuItem);
    }

    private boolean onClickHeartMenuItem(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.favoriteMenuItem) {
            MovieDetailModel movieDetailModel = binding.getMovieDetail();
            movieDetailModel.image = toBase64(binding.coverPageImage);
            movieDetailModel.backgroundImage = toBase64(binding.backgroundImage);
            MovieDetailDS movieDetailDS = MovieViewMapper.convert(movieDetailModel);
            presenter.actionFavoriteMovie(movieDetailDS);
        }
        return false;
    }

    private int getMovieId() {
        return UpcomingDetailFragmentArgs.fromBundle(getArguments()).getMovieId();
    }

    private String toBase64(ImageView imageView) {
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            return Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT);
        }
        return "";
    }

}