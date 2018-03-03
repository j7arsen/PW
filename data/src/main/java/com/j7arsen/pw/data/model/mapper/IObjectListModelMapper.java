package com.j7arsen.pw.data.model.mapper;

import java.util.List;

/**
 * Created by arsen on 25.02.2018.
 */

public interface IObjectListModelMapper<ENTITY, DOMAIN> {

    DOMAIN mapEntityToDomain(ENTITY entity);

    List<DOMAIN> mapEntityToDomainList(List<ENTITY> entityList);

    ENTITY mapDomainToEntity(DOMAIN domain);

    List<ENTITY> mapDomainToEntityList(List<DOMAIN> domainList);

}
