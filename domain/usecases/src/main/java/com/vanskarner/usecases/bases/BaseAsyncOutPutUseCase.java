package com.vanskarner.usecases.bases;

import com.vanskarner.core.concurrent.FutureResult;

public abstract class BaseAsyncOutPutUseCase<OutPut> {

    public abstract FutureResult<OutPut> execute();

}