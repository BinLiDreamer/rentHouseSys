package service;

import bean.User;

public interface UserService {
    boolean check(User user);
    boolean add(User user);
}
