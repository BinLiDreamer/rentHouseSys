package dao.impl;

import bean.Message;
import bean.PathConstant;
import dao.MessageDao;
import tool.Change;

import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl extends BasicDaoImpl<Message> implements MessageDao {

    @Override
    public List<Message> select(Message message) {
        List<Message> messageList = select(null, PathConstant.MESSAGE);
        if(messageList == null){
            messageList = new ArrayList<>();
        }
        if(message == null){
            return messageList;
        }
        List<Message> messages = new ArrayList<>();
        if(message.getId()!=0){
            for (Message m:messageList){
                if(m.getId()==message.getId()){
                    messages.add(m);
                    break;
                }
            }
        }
        return messages;
    }

    @Override
    public boolean add(Message message) {
        if(message==null||message.getId()==0){
            return false;
        }
        return add(message,PathConstant.MESSAGE);
    }

    @Override
    public boolean update(Message message) {
        if(message==null||message.getId()==0){
            return false;
        }
        List<Message> messages = select(null);
        for (Message m:messages){
            if(m.getId()==message.getId()){
                Change.update(message,m);
                break;
            }
        }
        return addAll(messages,PathConstant.MESSAGE);
    }

    @Override
    public Message select(int id) {
        return null;
    }
}
