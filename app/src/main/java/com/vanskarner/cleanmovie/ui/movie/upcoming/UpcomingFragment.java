package com.vanskarner.cleanmovie.ui.movie.upcoming;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vanskarner.movie.presentation.ErrorView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vanskarner.cleanmovie.R;
import com.vanskarner.cleanmovie.databinding.UpcomingFragmentBinding;
import com.vanskarner.cleanmovie.ui.errors.custom.ErrorDialog;
import com.vanskarner.cleanmovie.ui.movie.MovieModel;
import com.vanskarner.cleanmovie.ui.movie.MovieViewMapper;
import com.vanskarner.movie.businesslogic.ds.MovieDS;
import com.vanskarner.movie.presentation.upcoming.UpcomingContract;
import com.vanskarner.singleadapter.SingleAdapter;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import io.reactivex.disposables.CompositeDisposable;

public class UpcomingFragment extends DaggerFragment implements UpcomingContract.view {
    @Inject
    UpcomingContract.presenter presenter;
    @Inject
    @UpcomingQualifiers.UpcomingAdapter
    SingleAdapter singleAdapter;
    @Inject
    UpcomingBindAdapter upcomingBindAdapter;
    @Inject
    ErrorDialog errorDialog;
    @Inject
    Pagination pagination;
    @Inject
    RxSearchView rxSearchView;
    @Inject
    CompositeDisposable compositeDisposable;
    UpcomingFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = UpcomingFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        configNavToolbar();
        configSearchView();
        configRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        configFilter();
        presenter.initialLoad(pagination.getPageNumber());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        compositeDisposable.clear();
        presenter.asyncCancel();
        binding = null;
    }

    @Override
    public void setSearchView(boolean visible) {
        getSearchView().setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setInitialProgress(boolean visible) {
        binding.upcomingProgress.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setPagingProgress(boolean visible) {
        singleAdapter.setVisibleProgress(visible);
    }

    @Override
    public void enableScroll() {
        pagination.enableScroll();
    }

    @Override
    public void paginate() {
        pagination.increment();
    }

    @Override
    public void showUpcoming(List<MovieDS> list) {
        List<MovieModel> modelList = MovieViewMapper.convert(list);
        singleAdapter.set(modelList);
    }

    @Override
    public void showError(ErrorView<?> errorView) {
        errorDialog.setError(errorView, () -> errorDialog.dismiss());
        errorDialog.show(getChildFragmentManager());
    }

    private void configNavToolbar() {
        NavController controller = Navigation.findNavController(binding.getRoot());
        AppBarConfiguration barConfiguration = new AppBarConfiguration
                .Builder(controller.getGraph()).build();
        NavigationUI.setupWithNavController(binding.upcomingToolbar, controller, barConfiguration);
    }

    private void configSearchView() {
        SearchView searchView = getSearchView();
        searchView.setQueryHint(getString(R.string.search));
        searchView.setOnSearchClickListener(v -> presenter.asyncCancel());
        searchView.setOnCloseListener(() -> {
            pagination.enableScroll();
            return false;
        });
    }

    private void configRecyclerView() {
        RecyclerView recyclerView = binding.upcomingRecycler;
        GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        Objects.requireNonNull(gridLayoutManager)
                .setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return singleAdapter.isLoad(position) ? 2 : 1;
                    }
                });
        recyclerView.addOnScrollListener(pagination);
        recyclerView.setAdapter(singleAdapter);
        upcomingBindAdapter.setOnClickItem(this::onClickUpcomingItem);
        singleAdapter.add(upcomingBindAdapter);
        singleAdapter.set(R.layout.loading_item);
        pagination.setPositionType(Pagination.LAST_POSITION_COMPLETE);
        pagination.setOnLoadMoreListener(page ->
                presenter.loadMoreItems(page, getSearchView().isIconified()));
    }

    private void onClickUpcomingItem(View viewItem) {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) viewItem.getTag();
        MovieModel movieModel = singleAdapter.getItem(viewHolder.getAdapterPosition());
        UpcomingFragmentDirections.UpcomingAction upcomingAction =
                UpcomingFragmentDirections.upcomingAction();
        upcomingAction.setMovieId(movieModel.id);
        Navigation.findNavController(viewItem).navigate(upcomingAction);
    }

    private void configFilter() {
        compositeDisposable.add(rxSearchView.setFilter(getSearchView(),
                query -> presenter.filter(query)));
    }

    private SearchView getSearchView() {
        Toolbar toolbar = binding.upcomingToolbar;
        return (SearchView) toolbar.getMenu().findItem(R.id.searchMenuItem).getActionView();
    }

}
