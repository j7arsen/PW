package com.j7arsen.pw.domain.interactor;

import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.base.single.BaseSingleUseCaseWithParams;
import com.j7arsen.pw.domain.model.RecipientModel;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.requestbody.RecipientFilter;
import com.j7arsen.pw.domain.repository.IRecipientRepository;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by arsen on 01.03.2018.
 */

public class GetRecipientUseCase extends BaseSingleUseCaseWithParams<List<RecipientModel>, GetRecipientUseCase.Params> {

    private IRecipientRepository recipientRepository;

    public GetRecipientUseCase(IRecipientRepository recipientRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.recipientRepository = recipientRepository;
    }

    @Override
    protected Single<List<RecipientModel>> buildUseCase(Params params) {
        return recipientRepository.getRecipient(params.tokenModel, params.recipientFilter);
    }

    public static class Params{

        private TokenModel tokenModel;
        private RecipientFilter recipientFilter;

        public Params(TokenModel tokenModel, RecipientFilter recipientFilter) {
            this.tokenModel = tokenModel;
            this.recipientFilter = recipientFilter;
        }

        public TokenModel getTokenModel() {
            return tokenModel;
        }

        public void setTokenModel(TokenModel tokenModel) {
            this.tokenModel = tokenModel;
        }

        public RecipientFilter getRecipientFilter() {
            return recipientFilter;
        }

        public void setRecipientFilter(RecipientFilter recipientFilter) {
            this.recipientFilter = recipientFilter;
        }
    }
}
