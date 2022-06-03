package dao.daobyjdbc;

import bean.Message;
import dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李彬
 * @describe
 * @date 2022-06-03-12:31
 */
public class MessageDaoConnByJDBCImpl extends BasicDaoConnJDBC<Message> implements MessageDao {
    @Override
    public List<Message> select(Message message) {
        String sql = "select * from message";
        List<Message> messages = queryMultiply(sql, Message.class);
        if(messages==null){
            messages = new ArrayList<>();
        }
        return messages;
    }

    @Override
    public boolean add(Message message) {
        String sql = "insert into message values(null,?,?,?,?,?,?)";
        int update = update(sql, message.getCustomerName(), message.getPhone(), message.getMessage(), message.getHouseInfoId(), message.getLocalDateTime(),message.getIsRead());
        return update>0;
    }

    @Override
    public boolean update(Message message) {
        String sql = "update message set customerName=?,phone=?,message=?,houseInfoId=?,localDateTime=?,isRead=? where id =?";
        int update = update(sql, message.getCustomerName(), message.getPhone(), message.getMessage(), message.getHouseInfoId(), message.getLocalDateTime(),message.getIsRead(), message.getId());
        return update>0;
    }

    @Override
    public Message select(int id) {
        String sql = "select * from message where id=?";
        Message message = querySingle(sql, Message.class, id);
        if(message==null){
            message = new Message();
        }
        return message;
    }
}
