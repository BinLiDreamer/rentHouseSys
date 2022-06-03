package tool;

import bean.Host;

import java.lang.reflect.Field;

public class Change {

    public static void main(String[] args) {
        Host host1 = new Host(1,"刘德才","123",0);
        Host host2 = new Host(2,"马传标","188",0);
        if(update(host1,host2)){
            System.out.println(host2);
        }
    }

    /**
     * 功能：用于值的复制，可以接收任何引用类型
     * @param origin 源数据引用
     * @param dest 目的数据引用
     * @return 返回是否复制成功
     * @param <T> 接收的类型为泛型
     */
    public static<T> boolean update(T origin,T dest){
        /**
         * 先判断源数据和目的数据的运行时类型是否相同，不同则无法复制
         */
        if(origin.getClass()!=dest.getClass()){
            return false;
        }
        /**
         * 1、先取得目的数据引用的Class对象
         * 2、得到包含所有属性的数组
         * 3、爆破，解除访问限制（private）
         * 4、将源数据的相应属性值赋给目的数据引用
         */
        try {
            Class<?> clazz = dest.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field:fields){
                if(field.getName().equals("serialVersionUID")){
                    continue;
                }
                field.setAccessible(true);
                field.set(dest,field.get(origin));
            }
            return true;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
