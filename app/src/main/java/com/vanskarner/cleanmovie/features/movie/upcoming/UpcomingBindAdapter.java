package com.vanskarner.cleanmovie.features.movie.upcoming;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.cleanmovie.databinding.UpcomingItemBinding;
import com.vanskarner.cleanmovie.di.scopes.PerFragment;
import com.vanskarner.cleanmovie.features.movie.MovieModel;
import com.vanskarner.singleadapter.BindAdapter;

import javax.inject.Inject;

@PerFragment
class UpcomingBindAdapter implements BindAdapter<MovieModel, UpcomingBindAdapter.ViewHolder> {

    private View.OnClickListener onClickItem;

    @Inject
    public UpcomingBindAdapter() {
    }

    public void setOnClickItem(View.OnClickListener onClickItem) {
        this.onClickItem = onClickItem;
    }

    @Override
    public Class<MovieModel> getClassItem() {
        return MovieModel.class;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, MovieModel item) {
        viewHolder.binding.setMovie(item);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, LayoutInflater inflater) {
        UpcomingItemBinding binding = UpcomingItemBinding
                .inflate(inflater, parent, false);
        return new ViewHolder(binding, onClickItem);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final UpcomingItemBinding binding;

        private ViewHolder(@NonNull UpcomingItemBinding binding,
                           View.OnClickListener onClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            super.itemView.setTag(this);
            super.itemView.setOnClickListener(onClickListener);
        }
    }

}