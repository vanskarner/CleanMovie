package com.vanskarner.cleanmovie.ui.movie.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.cleanmovie.databinding.FavoritesFragmentBinding;
import com.vanskarner.cleanmovie.ui.errors.custom.ErrorDialog;
import com.vanskarner.cleanmovie.ui.errors.ErrorView;
import com.vanskarner.cleanmovie.ui.movie.MovieDetailModel;
import com.vanskarner.cleanmovie.ui.movie.MovieBasicModel;
import com.vanskarner.singleadapter.SingleAdapter;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FavoritesFragment extends DaggerFragment implements FavoritesContract.view {
    @Inject
    FavoritesContract.presenter presenter;
    @Inject
    @FavoriteQualifiers.FavoriteAdapter
    SingleAdapter singleAdapter;
    @Inject
    FavoritesBindAdapter favoritesBindAdapter;
    @Inject
    DeleteFavoritesDialog deleteFavoritesDialog;
    @Inject
    FavoritesDetailDialog detailDialog;
    @Inject
    ErrorDialog errorDialog;
    private FavoritesFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FavoritesFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.favoritesToolbar.setOnMenuItemClickListener(this::onClickTrashMenuItem);
        binding.favoritesRecycler.setAdapter(singleAdapter);
        favoritesBindAdapter.setOnClickItem(this::onClickFavoriteItem);
        singleAdapter.add(favoritesBindAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getFavorites();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.asyncCancel();
    }

    @Override
    public void showFavorites(List<MovieBasicModel> list) {
        singleAdapter.set(list);
    }

    @Override
    public void showFavoriteDetail(MovieDetailModel model) {
        detailDialog.setModel(model);
        detailDialog.show(getChildFragmentManager());
    }

    @Override
    public void setNotFavorites(boolean visible) {
        binding.noFavoritesView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showError(ErrorView<?> errorView) {
        errorDialog.setError(errorView, () -> errorDialog.dismiss());
        errorDialog.show(getChildFragmentManager());
    }

    @Override
    public void showRemovedItems(int quantity) {
        String message = String.format(getString(R.string.msg_deleted_items), quantity);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private boolean onClickTrashMenuItem(MenuItem item) {
        if (item.getItemId() == R.id.deleteMenuItem) {
            deleteFavoritesDialog.setOnAccept((dialogInterface, i) -> {
                dialogInterface.dismiss();
                presenter.deleteFavorites();
            });
            deleteFavoritesDialog.show(getChildFragmentManager());
        }
        return false;
    }

    private void onClickFavoriteItem(View itemView) {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) itemView.getTag();
        MovieBasicModel item = singleAdapter.getItem(viewHolder.getAdapterPosition());
        presenter.getFavoriteDetail(item.id);
    }

}
