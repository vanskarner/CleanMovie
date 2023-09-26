package com.vanskarner.cleanmovie.ui.movie.favorites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.cleanmovie.databinding.FavoriteItemBinding;
import com.vanskarner.cleanmovie.main.di.scopes.PerFragment;
import com.vanskarner.cleanmovie.ui.movie.MovieModel;
import com.vanskarner.singleadapter.BindAdapter;

import javax.inject.Inject;

@PerFragment
class FavoritesBindAdapter implements BindAdapter<MovieModel, FavoritesBindAdapter.ItemViewHolder> {

    private View.OnClickListener onClickItem;

    @Inject
    public FavoritesBindAdapter() {
    }

    public void setOnClickItem(View.OnClickListener onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public Class<MovieModel> getClassItem() {
        return MovieModel.class;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder viewHolder, MovieModel item) {
        viewHolder.binding.setMovie(item);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        FavoriteItemBinding binding = FavoriteItemBinding
                .inflate(inflater, parent, false);
        return ItemViewHolder.create(binding, onClickItem);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final FavoriteItemBinding binding;

        private ItemViewHolder(@NonNull FavoriteItemBinding binding,
                               View.OnClickListener onClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            super.itemView.setTag(this);
            super.itemView.setOnClickListener(onClickListener);
        }

        private static ItemViewHolder create(FavoriteItemBinding binding,
                                             View.OnClickListener onClickItem) {
            return new ItemViewHolder(binding, onClickItem);
        }
    }

}