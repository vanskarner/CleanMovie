package com.vanskarner.movie.businesslogic.services;

import com.vanskarner.core.concurrent.FutureResult;

abstract class BaseAsyncUseCase<InPut, OutPut> {

    public abstract FutureResult<OutPut> execute(InPut inPut);

}