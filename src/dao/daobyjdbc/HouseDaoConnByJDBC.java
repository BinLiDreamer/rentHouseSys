package dao.daobyjdbc;

import bean.House;
import dao.HouseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李彬
 * @describe 用于house这个javabean类的增删改查
 * @date 2022-06-03-10:41
 */
public class HouseDaoConnByJDBC extends BasicDaoConnJDBC<House> implements HouseDao {

    @Override
    public List<House> select(House house) {
        String sql = "select * from house";
        List<House> houseList= queryMultiply(sql,House.class);
        if(houseList==null){
            houseList=new ArrayList<>();
        }
        return houseList;
    }

    @Override
    public House select(int id) {
        String sql = "select * from house where id=?";
        House house = querySingle(sql,House.class,id);
        if(house==null){
            house = new House();
        }
        return house;
    }

    @Override
    public boolean add(House house) {
        String sql = "insert into house values(null,?,?,?,?)";
        int update = update(sql, house.getAddress(), house.getState(), house.getRent(), house.getIsPublish());
        return update>0;
    }

    @Override
    public boolean update(House house) {
        String sql = "update house set address=?,state=?,rent=?,isPublish=? where id =?";
        int update = update(sql, house.getAddress(), house.getState(), house.getRent(), house.getIsPublish(),house.getId());
        return update>0;
    }

    @Override
    public boolean delete(House house) {
        String sql = "delete from house where id=?";
        int update = update(sql, house.getId());
        return update>0;
    }
}
