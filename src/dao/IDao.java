package dao;

public interface IDao<EntityType> {

    public int save(EntityType entity);

    public int update(EntityType entity);

    public EntityType queryById(EntityType entity);

    public int delete(EntityType entity);

}
