package dao.daobyjdbc;

import bean.HouseInfo;
import dao.HouseInfoDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李彬
 * @describe
 * @date 2022-06-03-11:10
 */
public class HouseInfoDaoConnByJDBC extends BasicDaoConnJDBC<HouseInfo> implements HouseInfoDao {
    @Override
    public List<HouseInfo> select(HouseInfo houseInfo) {
        String sql = "select * from houseInfo";
        List<HouseInfo> houseInfos = queryMultiply(sql, HouseInfo.class);
        if(houseInfos==null){
            houseInfos = new ArrayList<>();
        }
        return houseInfos;
    }

    @Override
    public HouseInfo select(int id) {
        String sql = "select * from houseInfo where id=?";
        HouseInfo houseInfo = querySingle(sql, HouseInfo.class, id);
        if(houseInfo==null){
            houseInfo = new HouseInfo();
        }
        return houseInfo;
    }

    @Override
    public boolean add(HouseInfo houseInfo) {
        String sql = "insert into houseInfo values(null,?,?)";
        int update = update(sql, houseInfo.getHostId(), houseInfo.getHouseId());
        return update>0;
    }

    @Override
    public boolean delete(HouseInfo houseInfo) {
        String sql = "delete from houseInfo where id =?";
        int update = update(sql, houseInfo.getId());
        return update>0;
    }
}
