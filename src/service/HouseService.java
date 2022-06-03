package service;

import bean.House;

import java.util.List;

public interface HouseService {
    List<House> select(House house);
    boolean add(House house);
    boolean update(House house);
    House select(int id);
    boolean delete(House house);
}
