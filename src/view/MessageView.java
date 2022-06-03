package view;

import bean.Host;
import bean.House;
import bean.HouseInfo;
import bean.Message;
import service.HostService;
import service.HouseInfoService;
import service.HouseService;
import service.MessageService;
import service.impl.HostServiceImpl;
import service.impl.HouseInfoServiceImpl;
import service.impl.HouseServiceImpl;
import service.impl.MessageServiceImpl;
import tool.ReadTool;

import java.util.List;

/**
 * 功能：后台信息管理页面，接收用户输入和展示相应信息给
 */
public class MessageView {
    private MessageService messageService = new MessageServiceImpl();
    private HouseInfoService houseInfoService = new HouseInfoServiceImpl();
    private HouseService houseService = new HouseServiceImpl();
    private HostService hostService = new HostServiceImpl();
    public void showSelect(){
        boolean loop = true;
        while (loop){
            System.out.println("------------租客信息查看----------");
            System.out.println("\t\t1 查看所有租客留言");
            System.out.println("\t\t2 查看未处理租客留言");
            System.out.println("\t\t3 返回上一级");
            System.out.print("请输入你的选择(序号):");
            int key = ReadTool.readInt(1,3);
            switch (key){
                case 1:
                    showAllMessage();
                    break;
                case 2:
                    showNotReadMessage();
                    break;
                case 3:
                    loop = false;
                    break;
            }
        }

    }
    public void showMessage(int id){
        Message message = messageService.select(id);
        if(message.getId()==0){
            System.out.println("该编号id租客留言信息不存在");
            return;
        }
        HouseInfo houseInfo = houseInfoService.select(message.getHouseInfoId());
        Host host = hostService.select(houseInfo.getHostId());
        House house = houseService.select(houseInfo.getHouseId());
        System.out.println("-----该条租客留言信息如下-----");
        System.out.println("编号\t客户姓名\t客户电话\t\t房主姓名\t房主电话\t房子位置\t\t房子月租\t房子状态\t\t留言时间\t\t\t\t\t\t客户留言");
        System.out.println(message.getId()+"\t"+message.getCustomerName()+"\t"+message.getPhone()+"\t\t"+host.getName()+"\t"+host.getPhone()+"\t"+house.getAddress()+"\t\t"+house.getRent()+"\t\t"+house.getState()+"\t\t"+message.getLocalDateTime()+"\t\t"+message.getMessage());
        message.setIsRead(true);
        messageService.update(message);
    }
    public void showAllMessage(){
        List<Message> messageList = messageService.select(null);

        System.out.println("-----------所有的客户留言如下--------");
        System.out.println("编号\t客户姓名\t客户电话\t\t房主姓名\t房主电话\t房子位置\t\t房子月租\t房子状态\t\t是否已处理\t\t留言时间\t\t\t\t\t\t客户留言");
        for (Message message:messageList){
            HouseInfo houseInfo = houseInfoService.select(message.getHouseInfoId());
            Host host = hostService.select(houseInfo.getHostId());
            House house = houseService.select(houseInfo.getHouseId());
            if(!message.getIsRead()){
                System.out.println(message.getId()+"\t"+message.getCustomerName()+"\t"+message.getPhone()+"\t\t"+host.getName()+"\t"+host.getPhone()+"\t"+house.getAddress()+"\t\t"+house.getRent()+"\t\t"+house.getState()+"\t\t未处理"+"\t\t"+message.getLocalDateTime()+"\t\t"+message.getMessage());
            }else {
                System.out.println(message.getId()+"\t"+message.getCustomerName()+"\t"+message.getPhone()+"\t\t"+host.getName()+"\t"+host.getPhone()+"\t"+house.getAddress()+"\t\t"+house.getRent()+"\t\t"+house.getState()+"\t\t已处理"+"\t\t"+message.getLocalDateTime()+"\t\t"+message.getMessage());
            }
        }
    }
    public void showNotReadMessage(){
        List<Message> messageList = messageService.select(null);
        System.out.println("-----------未读的客户留言如下--------");
        System.out.println("编号\t\t客户姓名\t\t留言时间");
        boolean flag = false;
        for (Message message:messageList){
            if(!message.getIsRead()){
                System.out.println(message.getId()+"\t\t"+message.getCustomerName()+"\t\t"+message.getLocalDateTime().toLocalDateTime());
                flag = true;
            }
        }
        if(flag){
            lookDetailMessage();
        }
    }
    public void lookDetailMessage(){
        System.out.print("是否选择看租客详细留言信息(y/n)");
        String key = ReadTool.selectYorN();
        if("N".equalsIgnoreCase(key)){
            return;
        }
        if("Y".equalsIgnoreCase(key)){
            System.out.print("请输入要查看的留言编号:");
            int messId = ReadTool.readInt();
            showMessage(messId);
        }
    }
}
