package dao;

import bean.Host;

import java.util.List;

public interface HostDao {
    List<Host> select(Host host);
    Host select(int id);
    boolean addHost(Host host);

    boolean update(Host host);
    boolean delete(Host host);
}
