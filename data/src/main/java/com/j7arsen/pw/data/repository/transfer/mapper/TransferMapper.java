package com.j7arsen.pw.data.repository.transfer.mapper;

import com.j7arsen.pw.data.model.mapper.IObjectModelMapper;
import com.j7arsen.pw.data.model.net.transfer.TransTokenEntity;
import com.j7arsen.pw.domain.model.TransTokenModel;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransferMapper implements IObjectModelMapper<TransTokenEntity, TransTokenModel> {

    @Override
    public TransTokenModel mapEntityToDomain(TransTokenEntity transTokenEntity) {
        TransTokenModel transTokenModel = new TransTokenModel();
        transTokenModel.setId(transTokenEntity.getId());
        transTokenModel.setDate(transTokenEntity.getDate());
        transTokenModel.setUsername(transTokenEntity.getUsername());
        transTokenModel.setAmount(transTokenEntity.getAmount());
        transTokenModel.setBalance(transTokenEntity.getBalance());
        return transTokenModel;
    }

    @Override
    public TransTokenEntity mapDomainToEntity(TransTokenModel transTokenModel) {
        TransTokenEntity transTokenEntity = new TransTokenEntity();
        transTokenEntity.setId(transTokenModel.getId());
        transTokenEntity.setDate(transTokenModel.getDate());
        transTokenEntity.setUsername(transTokenModel.getUsername());
        transTokenEntity.setAmount(transTokenModel.getAmount());
        transTokenEntity.setBalance(transTokenModel.getBalance());
        return transTokenEntity;
    }
}
