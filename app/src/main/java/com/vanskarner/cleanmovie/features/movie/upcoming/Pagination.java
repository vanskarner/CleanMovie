package com.vanskarner.cleanmovie.features.movie.upcoming;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.vanskarner.cleanmovie.di.scopes.PerFragment;

import javax.inject.Inject;

@PerFragment
class Pagination extends RecyclerView.OnScrollListener {

    public static final int LAST_POSITION = 1;
    public static final int LAST_POSITION_COMPLETE = 2;

    private int pageNumber = 1;
    private boolean allowScroll;
    private OnLoadMoreListener onLoadMoreListener;
    private int positionType = LAST_POSITION;

    @Inject
    public Pagination() {
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int page);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void enableScroll() {
        allowScroll = true;
    }

    public void disableScroll() {
        allowScroll = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setPositionType(int positionType) {
        this.positionType = positionType;
    }

    public void increment() {
        pageNumber++;
        allowScroll = true;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        if (isValidScrolled(recyclerView.getLayoutManager(), dy)) {
            disableScroll();
            onLoadMoreListener.onLoadMore(pageNumber);
        }
    }

    private boolean isValidScrolled(RecyclerView.LayoutManager manager, int dy) {
        return manager != null &&
                dy > 0 &&
                isLastPosition(manager) &&
                allowScroll;
    }

    private boolean isLastPosition(RecyclerView.LayoutManager manager) {
        return (getLastPosition(manager) == manager.getItemCount() - 1);
    }

    private int getLastPosition(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager)
            return lastPositionGrid((GridLayoutManager) layoutManager);
        else if (layoutManager instanceof LinearLayoutManager)
            return lastPositionLinear((LinearLayoutManager) layoutManager);
        else if (layoutManager instanceof StaggeredGridLayoutManager)
            return lastPositionStaggered((StaggeredGridLayoutManager) layoutManager);
        return 0;
    }

    private int lastPositionGrid(@NonNull GridLayoutManager manager) {
        return (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPosition() :
                manager.findLastCompletelyVisibleItemPosition();
    }

    private int lastPositionLinear(@NonNull LinearLayoutManager manager) {
        return (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPosition() :
                manager.findLastCompletelyVisibleItemPosition();
    }

    private int lastPositionStaggered(@NonNull StaggeredGridLayoutManager manager) {
        int[] lastPositions = (positionType == LAST_POSITION) ?
                manager.findLastVisibleItemPositions(null) :
                manager.findLastCompletelyVisibleItemPositions(null);
        if (lastPositions == null || lastPositions.length <= 0) return 0;
        int max = lastPositions[0];
        for (int value : lastPositions) max = Math.max(max, value);
        return max;
    }

}