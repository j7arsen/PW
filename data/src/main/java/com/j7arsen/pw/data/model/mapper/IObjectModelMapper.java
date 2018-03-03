package com.j7arsen.pw.data.model.mapper;

/**
 * Created by arsen on 26.02.2018.
 */

public interface IObjectModelMapper<ENTITY, DOMAIN> {

    DOMAIN mapEntityToDomain(ENTITY entity);

    ENTITY mapDomainToEntity(DOMAIN domain);
}
