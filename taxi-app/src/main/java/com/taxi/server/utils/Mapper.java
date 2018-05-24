package com.taxi.server.utils;

import org.glassfish.hk2.utilities.reflection.ParameterizedTypeImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Mapper<Entity, Dto> {

    private ModelMapper modelMapper = new ModelMapper();

    private Class<Entity> entityClass;
    private Class<Dto> dtoClass;

    private Type entityTypeList;
    private Type dtoTypeList;

    public Mapper(Class<Entity> entityClass, Class<Dto> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;

        this.entityTypeList = new TypeToken<List<Entity>>() {}.getType();
        this.dtoTypeList = new TypeToken<List<Dto>>() {}.getType();
    }

    public Dto createDto(Entity entity) {
        return modelMapper.map(entity, dtoClass);
    }

    public Entity createEntity(Dto dto) {
        return modelMapper.map(dto, entityClass);
    }

    public List<Dto> createDtoList(List<Entity> list) {
        return modelMapper.map(list, dtoTypeList);
    }

    public List<Entity> createEntityList(List<Dto> list) {
        return modelMapper.map(list, entityTypeList);
    }

    public void addProperty(PropertyMap<Entity, Dto> propertyMap) {
        modelMapper.addMappings(propertyMap);
    }
}
