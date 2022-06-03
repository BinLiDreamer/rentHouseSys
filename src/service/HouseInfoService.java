package service;

import bean.HouseInfo;

import java.util.List;

public interface HouseInfoService {
    List<HouseInfo> select(HouseInfo houseInfo);
    HouseInfo select(int id);
    boolean add(HouseInfo houseInfo);
    boolean delete(HouseInfo houseInfo);
}
