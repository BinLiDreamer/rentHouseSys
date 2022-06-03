package dao.impl;

import bean.HouseInfo;
import bean.PathConstant;
import dao.HouseInfoDao;

import java.util.ArrayList;
import java.util.List;

public class HouseInfoDaoImpl extends BasicDaoImpl<HouseInfo> implements HouseInfoDao {

    @Override
    public List<HouseInfo> select(HouseInfo houseInfo) {
        List<HouseInfo> houseInfoList = select(null,PathConstant.HOUSE_INFO_PATH);
        if(houseInfo==null){
            return houseInfoList;
        }
        List<HouseInfo> retList= new ArrayList<>();
        if(houseInfo.getId()!=0){
            for (HouseInfo info:houseInfoList){
                if(houseInfo.getId()==info.getId()){
                    retList.add(info);
                    break;
                }
            }
        }
        return retList;
    }

    @Override
    public HouseInfo select(int id) {
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setId(id);
        List<HouseInfo> houseInfoList = select(houseInfo);
        if(houseInfoList.size()!=0){
            return houseInfoList.get(0);
        }else {
            return new HouseInfo();
        }
    }

    @Override
    public boolean add(HouseInfo houseInfo) {
        return add(houseInfo,PathConstant.HOUSE_INFO_PATH);
    }

    @Override
    public boolean delete(HouseInfo houseInfo) {
        if(houseInfo==null||houseInfo.getId()==0){
            return false;
        }
        return delete(houseInfo,PathConstant.HOUSE_INFO_PATH);
    }
}
