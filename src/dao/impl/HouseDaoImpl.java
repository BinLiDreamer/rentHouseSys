package dao.impl;

import bean.House;
import bean.PathConstant;
import dao.HouseDao;
import tool.Change;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HouseDaoImpl extends BasicDaoImpl<House> implements HouseDao {
    @Override
    public List<House> select(House house) {
        List<House> houseList = select(house,PathConstant.HOUSE_PATH);
        if(house==null){
            return houseList;
        }
        List<House> retList = new ArrayList<>();
        if(house.getId()!=0){
            for (House h:houseList){
                if(house.getId()==h.getId()){
                    retList.add(h);
                    return retList;
                }
            }
        }

        Set<House> houseSet=new HashSet<>();
        if(house.getAddress()!=null){
            for (House h:houseList){
                if(h.getAddress().equals(house.getAddress())){
                    houseSet.add(h);
                }
            }
        }
        if(house.getState()!=null){
            for (House h:houseList){
                if(h.getState().equals(house.getState())){
                    houseSet.add(h);
                }
            }
        }
        if(house.getRent()!=0){
            for (House h: houseList){
                if(h.getRent()==house.getRent()){
                    houseSet.add(h);
                }
            }
        }
        retList.addAll(houseSet);
        return retList;
    }

    @Override
    public House select(int id) {
            House house = new House();
            house.setId(id);
            List<House> houses = select(house);
            if(houses.size()==0){
                return new House();
            }
            return houses.get(0);
    }

    @Override
    public boolean add(House house) {
        return add(house, PathConstant.HOUSE_PATH);
    }

    @Override
    public boolean update(House house) {
        if(house==null||house.getId()==0){
            return false;
        }
        List<House> houseList = select(null);
        for (House h:houseList){
            if(house.getId()==h.getId()){
                Change.update(house,h);
                break;
            }
        }
        return  addAll(houseList, PathConstant.HOUSE_PATH);
    }

    @Override
    public boolean delete(House house) {
        if(house==null||house.getId()==0){
            return false;
        }
        return delete(house,PathConstant.HOUSE_PATH);
    }

}
