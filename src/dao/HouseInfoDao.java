package dao;

import bean.HouseInfo;

import java.util.List;

public interface HouseInfoDao {
    List<HouseInfo> select(HouseInfo houseInfo);
    HouseInfo select(int id);
    boolean add(HouseInfo houseInfo);
    boolean delete(HouseInfo houseInfo);
}
