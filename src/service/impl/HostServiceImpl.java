package service.impl;

import bean.Host;
import dao.HostDao;
import dao.daobyjdbc.HostDaoByJDBC;
import dao.impl.HostDaoImpl;
import service.HostService;

import java.util.List;

public class HostServiceImpl implements HostService {
//    private HostDao hostDao = new HostDaoImpl();
    private HostDao hostDao = new HostDaoByJDBC();
    @Override
    public List<Host> select(Host host) {
        return hostDao.select(host);
    }

    @Override
    public Host select(int id) {
        Host host =hostDao.select(id);
        if(host==null){
            host = new Host();
        }
        return host;
    }

    @Override
    public boolean addHost(Host host) {
        return hostDao.addHost(host);
    }

    @Override
    public boolean update(Host host) {
        return hostDao.update(host);
    }

    @Override
    public boolean delete(Host host) {
        return hostDao.delete(host);
    }
}
