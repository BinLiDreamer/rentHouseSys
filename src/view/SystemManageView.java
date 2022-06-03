package view;

import service.HouseService;
import service.impl.HouseServiceImpl;
import tool.ReadTool;

/**
 * 功能：后台主页面，用于展示和接收用户输入
 */
public class SystemManageView {
    private HouseService houseService = new HouseServiceImpl();
    private HostManageSys hostManageSys = new HostManageSys();
    private HouseManageSys houseManageSys = new HouseManageSys();
    private HouseInfoManageSys houseInfoManageSys = new HouseInfoManageSys();
    private MessageView messageView = new MessageView();
    public void houseRentSys(String name){
        System.out.println("-----登陆成功,欢迎"+name+"------");
        boolean loop = true;
        while (loop){
            System.out.println("\t\t1 业 主 管 理");
            System.out.println("\t\t2 房 屋 管 理");
            System.out.println("\t\t3 房屋信息管理");
            System.out.println("\t\t4 处理租客留言");
            System.out.println("\t\t5 退 出 登 录");
            System.out.print("请输入你的选择(输入序号):");
            int key = ReadTool.readInt(1,5);
            switch (key){
                case 1:
                    hostManageSys.select();
                    break;
                case 2:
                    houseManageSys.select();
                    break;
                case 3:
                    houseInfoManageSys.houseInfoMenu();
                    break;
                case 4:
                    messageView.showSelect();
                    break;
                case 5:
                    loop = false;
                    break;
            }
        }

    }

}
