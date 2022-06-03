package dao.impl;

import bean.Host;
import bean.PathConstant;
import dao.HostDao;
import tool.Change;

import java.util.ArrayList;
import java.util.List;


public class HostDaoImpl extends BasicDaoImpl<Host> implements HostDao {
    @Override
    public List<Host> select(Host host) {

            List<Host> hostList = select(host,PathConstant.HOST_PATH);
            if(host==null){
                return hostList;
            }
            List<Host> temp = new ArrayList<>();
            if(host.getId()!=0){
                for (Host h:hostList){
                    if(host.getId()==h.getId()){
                        temp.add(h);
                        return temp;
                    }
                }
            }
            if(host.getName()!=null&&host.getPhone()!=null){
                for (Host h:hostList){
                    if(host.getName().equals(h.getName())){
                        temp.add(h);
                        break;
                    }
                }
            }else if(host.getName()!=null){
                for (Host h: hostList){
                    if(host.getName().equals(h.getName())){
                        temp.add(h);
                    }
                }
            }else if(host.getPhone()!=null){
                for (Host h:hostList){
                    if(host.getPhone().equals(h.getPhone())){
                        temp.add(h);
                    }
                }
            }
            return temp;
    }

    @Override
    public Host select(int id) {
        Host host = new Host();
        host.setId(id);
        List<Host> hosts = select(host);
        if(hosts.size()==0){
            return new Host();
        }
        return hosts.get(0);
    }

    @Override
    public boolean addHost(Host host) {
        if(host == null){
            return false;
        }
        List<Host> hostList = select(host,PathConstant.HOST_PATH);
        if(hostList==null||hostList.size()==0){
            host.setId(1);
        }else {
            host.setId(hostList.get(hostList.size()-1).getId()+1);
        }
        return add(host,PathConstant.HOST_PATH);
    }

    @Override
    public boolean update(Host host){
        if(host==null||host.getId()==0){
            return false;
        }
        List<Host> hostList = select(null,PathConstant.HOST_PATH);
        for (Host h:hostList){
            if(h.getId()==host.getId()){
                Change.update(host,h);
                break;
            }
        }
        return addAll(hostList,PathConstant.HOST_PATH);
    }

    @Override
    public boolean delete(Host host) {
        if(host==null||host.getId()==0||host.getName()==null||host.getPhone()==null){
            return false;
        }
        return delete(host,PathConstant.HOST_PATH);
    }
}
