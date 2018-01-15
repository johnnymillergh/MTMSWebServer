package dao;

import entity.OrderEntity;

import java.util.List;

public class OrderDao implements IDao<OrderEntity> {
    @Override
    public int save(OrderEntity entity) {
        return 0;
    }

    @Override
    public int update(OrderEntity entity) {
        return 0;
    }

    @Override
    public OrderEntity queryById(OrderEntity entity) {
        return null;
    }

    @Override
    public int delete(OrderEntity entity) {
        return 0;
    }

    @Override
    public List<OrderEntity> getAll() {
        return null;
    }
}
