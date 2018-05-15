package service;

import java.util.List;

public interface CrudTemplate<T> {

    void create(T t);

    T selectById(int id);

    List<T> selectAll();

    void delete(int id);

    void update(T auction, int id);
}
