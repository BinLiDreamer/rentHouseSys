package service;

import bean.Host;

import java.util.List;

public interface HostService {
    List<Host> select(Host host);
    Host select(int id);
    boolean addHost(Host host);
    boolean update(Host host);
    boolean delete(Host host);
}
