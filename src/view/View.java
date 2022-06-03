package view;

import service.UserService;
import service.impl.UserServiceImpl;
import tool.ReadTool;

/**
 * 功能：房屋出租的主页面
 */
public class View {
    private UserService userService = new UserServiceImpl();
    private UserView userView = new UserView();
    private HouseInfoView houseInfoView = new HouseInfoView();


    /**
     * 功能：主菜单页面，显示房屋出租系统的主界面
     * 主要包括：1，登陆系统（管理员登陆后台操作）
     *         2，房屋信息（租客浏览租房信息）
     *         3，退出系统
     *
     */
    public void mainMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("------------房屋出租----------");
            System.out.println("\t\t1 登 录 系 统");
            System.out.println("\t\t2 房 屋 信 息");
            System.out.println("\t\t3 退      出");
            System.out.print("请输入你的选择(输入序号):");
            int key = ReadTool.readInt(1,3);
            switch (key){
                case 1:
                    userView.login();
                    break;
                case 2:
                    houseInfoView.selectHouseShow();
                    break;
                case 3:
                    loop = false;
                    break;
            }
        }
        System.out.println("你已退出了房屋出租系统！");
    }
}
