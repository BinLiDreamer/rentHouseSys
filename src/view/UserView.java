package view;

import bean.User;
import service.UserService;
import service.impl.UserServiceImpl;
import tool.ReadTool;

/**
 * 功能：用户注册或者登陆页面
 */
public class UserView {
    private UserService userService = new UserServiceImpl();
    private SystemManageView systemManageView = new SystemManageView();

    /**
     * 功能：显示后台登录注册页面
     * 包括：1、登陆账号（有账号直接登陆，admin为默认账号密码）
     *      2、注册账号（用于注册账号）
     *      3、返回上一级菜单
     */
    public void login(){
        System.out.println("------------登陆系统----------");
        boolean loop = true;
        while (loop){
            System.out.println("\t\t1 登 录 账 号");
            System.out.println("\t\t2 注 册 账 号");
            System.out.println("\t\t3 返回上一级");
            System.out.print("请输入你的选择(输入序号):");
            int key = ReadTool.readInt(1,3);
            switch (key){
                case 1:
                    loginUser();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    loop = false;
                    break;
            }
        }
    }

    /**
     * 功能：输入账号密码并验证是否正确，正确跳转后台管理，否则提示错误信息
     *
     */
    public void loginUser(){
        System.out.print("请输入账号:");
        String name = ReadTool.readString(10);
        System.out.print("请输入密码:");
        String password = ReadTool.readString(10);
        if(userService.check(new User(name,password))){
            systemManageView.houseRentSys(name);
        }else {
            System.out.println("----------账号或密码有误----------");
        }
    }

    /**
     * 功能：注册账号并验证，注册成功则将账号密码持久化到文件中
     */
    public void registerUser(){
        System.out.println("------------注册账号----------");
        boolean loop = true;
        while (loop){
            System.out.print("请输入账号:");
            String name = ReadTool.readString(10);
            System.out.print("请输入密码:");
            String password = ReadTool.readString(10);
            System.out.print("请再次输入密码:");
            String surePassword = ReadTool.readString(10);
            if(!password.equals(surePassword)){
                System.out.println("两次密码不一致，请重新输入");
                continue;
            }
            if(userService.add(new User(name,password))){
                loop = false;
            }
        }
        System.out.println("注册账号成功！");
    }
}
