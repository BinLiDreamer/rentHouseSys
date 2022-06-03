package dao.impl;

import dao.BasicDao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能：基本dao，一个工具类，其他dao继承它，用于实现对程序数据与文件的读写进行交互，能减少代码量
 * @param <T> 必须是泛型，因为需要操作的数据可能是多种多样的
 */
public class BasicDaoImpl<T> implements BasicDao<T> {
    /**
     * 功能：从path路径的文件中读取数据并将其返回
     * @param t 传入的参数为泛型
     * @param path 读取文件数据的路径
     * @return 返回传入参数的list列表对象
     */
    @Override
    public List<T> select(T t,String path) {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            List<T> tList = (List<T>)ois.readObject();
            if(tList==null){
                tList = new ArrayList<>();
            }
            return tList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 功能:将传入的参数t加入到path文件的中
     * @param t 传入的参数为泛型
     * @param path 需要将参数加入到的文件路径
     * @return 返回是否加入成功
     */
    @Override
    public boolean add(T t, String path) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            List<T> tList =(List<T>) ois.readObject();
            tList.add(t);
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(tList);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(ois != null){
                    ois.close();
                }
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 功能：将传入的list对象全部存入path路径的文件中
     * @param list 需要存储的list对象
     * @param path 需要存储list对象的文件路径
     * @return 返回是否成功存储
     */
    @Override
    public boolean addAll(List<T> list, String path) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(list);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(oos != null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 功能：将t从文件中删除
     * @param t 需要删除的t对象，注意，t的equals和hashCode方法必须重写，不然删除不了
     *
     * @param path 删除数据的文件路径
     * @return 返回是否删除成功，
     */
    @Override
    public boolean delete(T t,String path) {
        List<T> list = select(t, path);
        boolean flag = list.remove(t);
        return flag&&addAll(list,path);
    }

}
