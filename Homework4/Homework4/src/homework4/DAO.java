package homework4;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
  
    public List<T> list();


    public Optional<T> get(int id);


    public void add(T obj);


    public void update(T obj);

  
    public void delete(int id);
}