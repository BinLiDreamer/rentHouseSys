package dao.impl;

import bean.PathConstant;
import bean.User;
import dao.UserDao;

import java.util.List;

/**
 * 是一个操作user的增删改查的dao类
 */
public class UserDaoImpl extends BasicDaoImpl<User> implements UserDao {
    @Override
    public List<User> select(User user) {
        return select(null,PathConstant.USER_PATH);
    }

    @Override
    public boolean add(User user) {
        if(user==null){
            return false;
        }
        List<User> users = select(null);
        users.add(user);
        return addAll(users,PathConstant.USER_PATH);
    }
}
