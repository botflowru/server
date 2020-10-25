package dao;

import java.util.Optional;

public interface CrudDao<T> {
    void save(T model);
    void delete(T model);
    void update(T model);
    Optional<T> get(String email);
}
