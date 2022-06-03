package service.impl;

import bean.House;
import dao.HouseDao;
import dao.daobyjdbc.HouseDaoConnByJDBC;
import service.HouseService;

import java.util.List;

public class HouseServiceImpl implements HouseService {
//    private HouseDao houseDao = new HouseDaoImpl();
    private HouseDao houseDao = new HouseDaoConnByJDBC();
    @Override
    public List<House> select(House house) {
        return houseDao.select(house);
    }

    @Override
    public boolean add(House house) {
        List<House> houseList = houseDao.select(null);
        if(houseList.size()==0){
            house.setId(1);
        }else {
            house.setId(houseList.get(houseList.size()-1).getId()+1);
        }
        return houseDao.add(house);
    }

    @Override
    public boolean update(House house) {
        return houseDao.update(house);
    }

    @Override
    public House select(int id) {
        return houseDao.select(id);
    }

    @Override
    public boolean delete(House house) {
        return houseDao.delete(house);
    }
}
