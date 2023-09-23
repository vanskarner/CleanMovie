package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

abstract class BaseAsyncOutPutUseCase<OutPut> {

    public abstract FutureResult<OutPut> execute();

}