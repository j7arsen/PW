package com.j7arsen.pw.data.repository.recipient.mapper;

import com.j7arsen.pw.data.model.mapper.IObjectListModelMapper;
import com.j7arsen.pw.data.model.net.transfer.RecipientEntity;
import com.j7arsen.pw.domain.model.RecipientModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arsen on 01.03.2018.
 */

public class RecipientMapper implements IObjectListModelMapper<RecipientEntity, RecipientModel> {

    @Override
    public RecipientModel mapEntityToDomain(RecipientEntity recipientEntity) {
        RecipientModel recipientModel = new RecipientModel();
        recipientModel.setId(recipientEntity.getId());
        recipientModel.setName(recipientEntity.getName());
        return recipientModel;
    }

    @Override
    public List<RecipientModel> mapEntityToDomainList(List<RecipientEntity> recipientEntities) {
        List<RecipientModel> resultList = new ArrayList<>();
        if(recipientEntities != null && recipientEntities.size() != 0) {
            for (RecipientEntity recipientEntity : recipientEntities) {
                RecipientModel recipientModel = new RecipientModel();
                recipientModel.setId(recipientEntity.getId());
                recipientModel.setName(recipientEntity.getName());
                resultList.add(recipientModel);
            }
        }
        return resultList;
    }

    @Override
    public RecipientEntity mapDomainToEntity(RecipientModel recipientModel) {
        RecipientEntity recipientEntity = new RecipientEntity();
        recipientEntity.setId(recipientModel.getId());
        recipientEntity.setName(recipientModel.getName());
        return recipientEntity;
    }

    @Override
    public List<RecipientEntity> mapDomainToEntityList(List<RecipientModel> recipientModels) {
        List<RecipientEntity> resultList = new ArrayList<>();
        if(recipientModels != null && recipientModels.size() != 0) {
            for (RecipientModel recipientModel : recipientModels) {
                RecipientEntity recipientEntity = new RecipientEntity();
                recipientEntity.setId(recipientModel.getId());
                recipientEntity.setName(recipientModel.getName());
                resultList.add(recipientEntity);
            }
        }
        return resultList;
    }
}
