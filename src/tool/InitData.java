package tool;

import bean.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InitData {
    public static void main(String[] args) {
        List<Host> userList = new ArrayList<>();
        userList.add(new Host(1,"黄伟荣","15918",1));
        userList.add(new Host(2,"刘德才","18688",0));
        initData(PathConstant.HOST_PATH,userList);
        List<User> users=new ArrayList<>();
        users.add(new User("admin","admin"));
        initData(PathConstant.USER_PATH,users);

        List<House> houseList = new ArrayList<>();
        houseList.add(new House(1,500,"番禺区","未出租",true));
        initData(PathConstant.HOUSE_PATH,houseList);
        List<HouseInfo> houseInfoList = new ArrayList<>();
        houseInfoList.add(new HouseInfo(1,1,1));
        initData(PathConstant.HOUSE_INFO_PATH,houseInfoList);
        List<Message> messageList = new ArrayList<>();
        initData(PathConstant.MESSAGE,messageList);
    }

    /**
     * 功能：初始化数据，运行主程序前需要先初始化数据，
     * @param path 初始化文件存放的路径，必须以/文件目录作为分界
     * @param t 需要初始化的数据，这里接收的是List类型的对象
     * @param <T> 采用泛型初始化各类数据
     */
    public static<T> void initData(String path, List<T> t){
        /**
         * 先判断是否有该文件夹和文件，如果没有则创建相应的文件夹和文件
         */
        String fatherPath = path.split("/")[0];
        File directory = new File(fatherPath);
        File file = new File(path);
        if(!directory.exists()){
            directory.mkdir();
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        /**
         * 将数据输出到文件中
         */
        ObjectOutputStream oos =null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            List<T> userList = new ArrayList<>();
            userList.addAll(t);
            oos.writeObject(userList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(oos!=null){
                    oos.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
