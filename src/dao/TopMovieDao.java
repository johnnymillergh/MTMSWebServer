package dao;

import entity.TopMovieEntity;

import java.util.List;

public class TopMovieDao implements IDao<TopMovieEntity>{
    @Override
    public int save(TopMovieEntity entity) {
        return 0;
    }

    @Override
    public int update(TopMovieEntity entity) {
        return 0;
    }

    @Override
    public TopMovieEntity queryById(TopMovieEntity entity) {
        return null;
    }

    @Override
    public int delete(TopMovieEntity entity) {
        return 0;
    }

    @Override
    public List<TopMovieEntity> getAll() {
        return null;
    }
}
