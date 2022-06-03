package dao;

import bean.Message;

import java.util.List;

public interface MessageDao {
    List<Message> select(Message message);
    boolean add(Message message);
    boolean update(Message message);
    Message select(int id);
}
