package com.vanskarner.movie.presentation.upcoming;

import javax.inject.Qualifier;

class UpcomingPresenterQualifiers {
    @Qualifier
    public @interface FullList {
    }

    @Qualifier
    public @interface FilterList {
    }
}