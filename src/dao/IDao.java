package dao;

import java.util.List;

public interface IDao<EntityType> {

    int save(EntityType entity);

    int update(EntityType entity);

    EntityType queryById(EntityType entity);

    int delete(EntityType entity);

    List<EntityType> getAll();
}
