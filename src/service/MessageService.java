package service;

import bean.Message;

import java.util.List;

public interface MessageService {
    List<Message> select(Message message);
    Message select(int id);
    boolean add(Message message);
    boolean update(Message message);
}
