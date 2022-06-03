package service.impl;

import bean.Message;
import dao.MessageDao;
import dao.daobyjdbc.MessageDaoConnByJDBCImpl;
import dao.impl.MessageDaoImpl;
import service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
//    private MessageDao messageDao = new MessageDaoImpl();
    private MessageDao messageDao = new MessageDaoConnByJDBCImpl();
    @Override
    public List<Message> select(Message message) {
        return messageDao.select(message);
    }

    @Override
    public Message select(int id) {
        return messageDao.select(id);
    }

    @Override
    public boolean add(Message message) {
        List<Message> messageList = messageDao.select(null);
        if(messageList.size()==0){
            message.setId(1);
        }else {
            message.setId(messageList.get(messageList.size()-1).getId()+1);
        }
        return messageDao.add(message);
    }

    @Override
    public boolean update(Message message) {
        return messageDao.update(message);
    }
}
