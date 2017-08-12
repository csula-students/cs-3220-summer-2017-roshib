package jaxrs;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    // List a list of objects
    public List<T> list();

    // return single object given its id
    public Optional<T> get(int id);

    // add item obj into database
    public void add(T obj);

    // update item
    public void update(T obj);

    // delete item given id
    public void delete(int id);
}