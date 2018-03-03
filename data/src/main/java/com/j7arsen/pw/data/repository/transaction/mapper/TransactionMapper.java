package com.j7arsen.pw.data.repository.transaction.mapper;

import com.j7arsen.pw.data.model.mapper.IObjectListModelMapper;
import com.j7arsen.pw.data.model.net.transfer.TransTokenEntity;
import com.j7arsen.pw.domain.model.TransTokenModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arsen on 02.03.2018.
 */

public class TransactionMapper implements IObjectListModelMapper<TransTokenEntity, TransTokenModel> {

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
    public List<TransTokenModel> mapEntityToDomainList(List<TransTokenEntity> transTokenEntities) {
        List<TransTokenModel> transTokenModelList = new ArrayList<>();
        if (transTokenEntities != null && transTokenEntities.size() != 0) {
            for (TransTokenEntity transTokenEntity : transTokenEntities) {
                TransTokenModel transTokenModel = new TransTokenModel();
                transTokenModel.setId(transTokenEntity.getId());
                transTokenModel.setDate(transTokenEntity.getDate());
                transTokenModel.setUsername(transTokenEntity.getUsername());
                transTokenModel.setAmount(transTokenEntity.getAmount());
                transTokenModel.setBalance(transTokenEntity.getBalance());
                transTokenModelList.add(transTokenModel);
            }
        }
        return transTokenModelList;
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

    @Override
    public List<TransTokenEntity> mapDomainToEntityList(List<TransTokenModel> transTokenModels) {
        List<TransTokenEntity> transTokenEntityList = new ArrayList<>();
        if (transTokenModels != null && transTokenModels.size() != 0) {
            for (TransTokenModel transTokenModel : transTokenModels) {
                TransTokenEntity transTokenEntity = new TransTokenEntity();
                transTokenEntity.setId(transTokenModel.getId());
                transTokenEntity.setDate(transTokenModel.getDate());
                transTokenEntity.setUsername(transTokenModel.getUsername());
                transTokenEntity.setAmount(transTokenModel.getAmount());
                transTokenEntity.setBalance(transTokenModel.getBalance());
            }
        }
        return transTokenEntityList;
    }

}
