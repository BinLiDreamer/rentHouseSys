package dao;

import java.util.List;

public interface BasicDao<T> {
    List<T> select(T t,String path);
    boolean add(T t,String path);
    boolean addAll(List<T> list,String path);

    boolean delete(T t,String path);
}
