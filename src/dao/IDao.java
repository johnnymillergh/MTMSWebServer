package dao;

public interface IDao<EntityType> {

    public void save(EntityType entity);

    public void update(EntityType entity);

    public EntityType queryById(EntityType entity);

    public void delete(EntityType entity);

}
