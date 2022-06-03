package dao.daobyjdbc;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import tool.JDBCConnByDruid;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDaoConnJDBC<T> {
   private QueryRunner queryRunner= new QueryRunner();

   public List<T> queryMultiply(String sql,Class<T> clazz,Object... params){
      Connection connection = null;
      try {
         connection = JDBCConnByDruid.getConnection();
         List<T> list = queryRunner.query(connection, sql, new BeanListHandler<>(clazz), params);
         return list;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         JDBCConnByDruid.close(null,null,connection);
      }
   }

   public T querySingle(String sql,Class<T> clazz,Object... params){
      Connection connection = null;
      try {
         connection = JDBCConnByDruid.getConnection();
         T query = queryRunner.query(connection, sql, new BeanHandler<>(clazz), params);
         return query;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         JDBCConnByDruid.close(null,null,connection);
      }
   }

   public Object queryScalar(String sql,Object... params){
      Connection connection = null;
      try {
         connection = JDBCConnByDruid.getConnection();
         Object query = queryRunner.query(connection, sql, new ScalarHandler(), params);
         return query;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         JDBCConnByDruid.close(null,null,connection);
      }
   }

   public int update(String sql,Object... params){
      Connection connection = null;
      try {
         connection = JDBCConnByDruid.getConnection();
         int updateRow = queryRunner.update(connection, sql, params);
         return updateRow;
      } catch (SQLException e) {
         throw new RuntimeException(e);
      } finally {
         JDBCConnByDruid.close(null,null,connection);
      }
   }
}
