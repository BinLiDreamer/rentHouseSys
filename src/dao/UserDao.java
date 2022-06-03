package dao;

import bean.User;

import java.util.List;

public interface UserDao {
    List<User> select(User user);
    boolean add(User user);
}
