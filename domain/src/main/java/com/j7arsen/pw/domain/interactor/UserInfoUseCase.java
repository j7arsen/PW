package com.j7arsen.pw.domain.interactor;

import com.j7arsen.pw.domain.executor.IThreadExecutor;
import com.j7arsen.pw.domain.interactor.base.single.BaseSingleUseCaseWithParams;
import com.j7arsen.pw.domain.model.TokenModel;
import com.j7arsen.pw.domain.model.UserInfoModel;
import com.j7arsen.pw.domain.repository.IUserInfoRepository;

import io.reactivex.Single;

/**
 * Created by arsen on 28.02.2018.
 */

public class UserInfoUseCase extends BaseSingleUseCaseWithParams<UserInfoModel, TokenModel> {

    private IUserInfoRepository userRepository;

    public UserInfoUseCase(IUserInfoRepository userRepository, IThreadExecutor threadExecutor) {
        super(threadExecutor);
        this.userRepository = userRepository;
    }

    @Override
    protected Single<UserInfoModel> buildUseCase(TokenModel tokenModel) {
        return userRepository.getUserInfo(tokenModel);
    }
}
