package main.repositories;

import main.utils.NotFoundException;

import java.util.List;

public interface BaseRepository<T> {
    T findByName(String name) throws NotFoundException;

    void add(T course);

    void remove(T course);

    void update(T updatedInstance);

    List<T> getAll();

}
