package dao;

import bean.House;

import java.util.List;

public interface HouseDao {
    List<House> select(House house);
    House select(int id);
    boolean add(House house);
    boolean update(House house);

    boolean delete(House house);
}

