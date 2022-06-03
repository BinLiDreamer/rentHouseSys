package service.impl;

import bean.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public boolean check(User user) {
        if(user==null||user.getName()==null){
            return false;
        }
        List<User> userList = userDao.select(user);
        if(userList==null||userList.size()==0){
            return false;
        }
        for(User u:userList){
            if(user.getName().equals(u.getName())&&u.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(User user) {
        if(user==null||user.getName()==null||user.getPassword()==null){
            return false;
        }
        return userDao.add(user);
    }
}
