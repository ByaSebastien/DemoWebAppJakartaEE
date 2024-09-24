package be.bstorm.demowebappjakartaee.repositories;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<TEntity ,TId> {

    TEntity save(TEntity entity);
    Optional<TEntity> findById(TId id);
    List<TEntity> findAll();
    void update(TEntity entity);
    void delete(TId id);
}
