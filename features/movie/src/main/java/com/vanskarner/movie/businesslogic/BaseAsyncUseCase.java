package com.vanskarner.movie.businesslogic;

import com.vanskarner.core.concurrent.FutureResult;

abstract class BaseAsyncUseCase<InPut, OutPut> {

    public abstract FutureResult<OutPut> execute(InPut inPut);

}