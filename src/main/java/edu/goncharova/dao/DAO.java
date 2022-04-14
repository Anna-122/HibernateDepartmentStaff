package edu.goncharova.dao;

import java.util.List;

public interface DAO<Entity, Key> {
    Entity saveOrUpdate(Entity model);
    List<Entity> findAll();
    Entity find(Key key);
    void delete(Entity model);
    Entity getByField(String fieldName);
}

