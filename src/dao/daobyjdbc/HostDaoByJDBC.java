package dao.daobyjdbc;

import bean.Host;
import dao.HostDao;

import java.util.List;

/**
 * @author 李彬
 * @describe  连接数据库的HostDao类，负责增删改查工作
 * @date 2022-06-03-10:02
 */
public class HostDaoByJDBC extends BasicDaoConnJDBC<Host> implements HostDao {
    @Override
    public List<Host> select(Host host) {
        String sql = "select * from host";
        return queryMultiply(sql,Host.class);
    }

    @Override
    public Host select(int id) {
        String sql = "select * from host where id=?";
        return querySingle(sql,Host.class,id);
    }

    @Override
    public boolean addHost(Host host) {
        String sql = "insert into host values(null,?,?,?)";
        int updateRows = update(sql, host.getName(), host.getPhone(), host.getCount());
        return updateRows>0;
    }

    @Override
    public boolean update(Host host) {
        String sql = "update host set name=?,phone=?,count=? where id=?;";
        int update = update(sql, host.getName(), host.getPhone(), host.getCount(), host.getId());
        return update>0;
    }

    @Override
    public boolean delete(Host host) {
        String sql = "delete from host where id=?";
        int update = update(sql, host.getId());
        return update>0;
    }
}
