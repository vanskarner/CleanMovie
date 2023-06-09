package com.vanskarner.movie.domain.bases;

import com.vanskarner.core.sync.Result;

public abstract class BaseUseCase<InPut, OutPut> {

    public Result<OutPut> execute(InPut inPutValues) {
        try {
            OutPut outPut = buildUseCase(inPutValues);
            return Result.success(outPut);
        } catch (Exception exception) {
            return Result.failure(exception);
        }
    }

    protected abstract OutPut buildUseCase(InPut inPutValues);

}