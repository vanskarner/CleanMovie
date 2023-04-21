package com.vanskarner.usecases.bases;

import com.vanskarner.core.concurrent.FutureResult;

public abstract class BaseAsyncUseCase<InPut, OutPut> {

    public abstract FutureResult<OutPut> execute(InPut inPut);

}