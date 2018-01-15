package dao;

import java.util.List;

public interface IDao<EntityType> {

    public int save(EntityType entity);

    public int update(EntityType entity);

    public EntityType queryById(EntityType entity);

    public int delete(EntityType entity);

    public List<EntityType> getAll();
}
