package dao.daobyjdbc;

import bean.User;
import dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李彬
 * @describe 对user对象的增查功能，select方法可以优化为根据name和password进行查询，返回user对象
 * @date 2022-06-03-11:02
 */
public class UserDaoConnByJDBC extends BasicDaoConnJDBC<User> implements UserDao {
    @Override
    public List<User> select(User user) {
        String sql = "select * from user";
        List<User> users = queryMultiply(sql,User.class);
        if(users==null){
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public boolean add(User user) {
        String sql = "insert into user values(?,?)";
        int update = update(sql, user.getName(), user.getPassword());
        return update>0;
    }
}
