package service.impl;

import bean.HouseInfo;
import dao.HouseInfoDao;
import dao.daobyjdbc.HouseInfoDaoConnByJDBC;
import dao.impl.HouseInfoDaoImpl;
import service.HouseInfoService;

import java.util.List;

public class HouseInfoServiceImpl implements HouseInfoService {
//    private HouseInfoDao houseInfoDao = new HouseInfoDaoImpl();
    private HouseInfoDao houseInfoDao = new HouseInfoDaoConnByJDBC();
    @Override
    public List<HouseInfo> select(HouseInfo houseInfo) {
        return houseInfoDao.select(houseInfo);
    }

    @Override
    public HouseInfo select(int id) {
        return houseInfoDao.select(id);
    }

    @Override
    public boolean add(HouseInfo houseInfo) {
        if(houseInfo==null||(houseInfo.getHostId()==0&&houseInfo.getHouseId()==0)){
            return false;
        }
        List<HouseInfo> houseInfos = select(null);
        if(houseInfos.size()==0){
            houseInfo.setId(1);
        }else {
            houseInfo.setId(houseInfos.get(houseInfos.size()-1).getId()+1);
        }
        return houseInfoDao.add(houseInfo);
    }

    @Override
    public boolean delete(HouseInfo houseInfo) {
        return houseInfoDao.delete(houseInfo);
    }
}
