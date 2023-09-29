package com.vanskarner.movie.ui.upcoming;

import javax.inject.Qualifier;

class UpcomingPresenterQualifiers {
    @Qualifier
    public @interface FullList {
    }

    @Qualifier
    public @interface FilterList {
    }
}