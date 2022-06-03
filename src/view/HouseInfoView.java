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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 功能：房屋信息页面
 */
public class HouseInfoView {
    private HouseInfoService houseInfoService = new HouseInfoServiceImpl();
    private HostService hostService = new HostServiceImpl();
    private HouseService houseService = new HouseServiceImpl();
    private MessageService messageService = new MessageServiceImpl();
    public void selectHouseShow(){

        boolean loop = true;
        while (loop){
            System.out.println("----------选择展示房屋信息--------");
            System.out.println("\t\t1 所有房屋信息");
            System.out.println("\t\t2 搜索房屋信息");
            System.out.println("\t\t3 返回上一级");
            System.out.print("请输入你的选择(输入序号):");
            int key = ReadTool.readInt(1,3);
            switch (key){
                case 1:
                    if(showAllHouseInfo()){
                        askIsRent();
                    }
                    break;
                case 2:
                    searchHouseInfo();
                    break;
                case 3:
                    loop = false;
                    break;
            }
        }
    }
    public void showHouseInfoById(int houseInfoId){
        HouseInfo houseInfo = houseInfoService.select(houseInfoId);
        if(houseInfo.getId()==0){
            System.out.println("查无此房屋信息！");
            return;
        }
        House house = houseService.select(houseInfo.getHouseId());
        Host host = hostService.select(houseInfo.getHostId());
        System.out.println(houseInfo.getId()+"\t\t"+host.getName()+"\t"+host.getPhone()+"\t"+house.getAddress()+"\t"+house.getRent()+"\t\t"+house.getState());
    }
    public boolean showAllHouseInfo(){
        System.out.println("------------房屋信息----------");
        System.out.println("编号\t\t房主\t\t电话\t\t位置\t\t月租\t\t状态");
        List<HouseInfo> houseInfoList = houseInfoService.select(null);
        boolean flag = false;
        for (HouseInfo houseInfo:houseInfoList){
            int hostId = houseInfo.getHostId();
            Host host = hostService.select(hostId);
            int houseId = houseInfo.getHouseId();
            House house = houseService.select(houseId);
            if(!"未出租".equals(house.getState())){
                continue;
            }
            System.out.println(houseInfo.getId()+"\t\t"+host.getName()+"\t"+host.getPhone()+"\t"+house.getAddress()+"\t"+house.getRent()+"\t\t"+house.getState());
            flag = true;
        }
        System.out.println("房屋信息展示完毕");
        return flag;
    }
    public void askIsRent(){
        int infoId ;
        boolean loop = true;
        while (loop){
            System.out.print("是否需要租房？(y/n):");
            String key = ReadTool.readString(1);
            if("Y".equalsIgnoreCase(key)){
                System.out.print("请输入意向租房房屋信息编号(-1取消):");
                infoId = ReadTool.readInt();
                if(infoId==-1){
                    System.out.println("你取消了租房！");
                    return;
                }
                HouseInfo houseInfo = houseInfoService.select(infoId);
                if(houseInfo.getId()==0){
                    System.out.println("查无此房屋信息,请查证后再输入!");
                    showAllHouseInfo();
                    continue;
                }
                customerMessage(houseInfo);
                break;
            }else if("N".equalsIgnoreCase(key)){
                System.out.println("");
                return;
            }else {
                System.out.println("你的输入有误,请重新输入!");
            }
        }
    }
    public void customerMessage(HouseInfo houseInfo){
        Host host = hostService.select(houseInfo.getHostId());
        House house = houseService.select(houseInfo.getHouseId());
        System.out.println("请留下你的姓名，电话，以及要求，稍后有客服联系您！");
        System.out.print("请输入你的姓名:");
        String name = ReadTool.readString(10);
        System.out.print("请输入你的电话:");
        String phone = ReadTool.readString(11);
        System.out.print("请输入你的留言(100字以内):");
        String message = ReadTool.readString(100);
        System.out.println("--------请确认以下房屋信息是否自己所选房屋----");
        System.out.println("编号\t\t房主\t\t电话\t\t位置\t\t月租\t\t状态");
        System.out.println(houseInfo.getId()+"\t\t"+host.getName()+"\t"+host.getPhone()+"\t"+house.getAddress()+"\t"+house.getRent()+"\t\t"+house.getState());
        System.out.print("请确认是否自己选择房屋(y/n):");
        while (true){
            String chooseHouse = ReadTool.selectYorN();
            if ("N".equalsIgnoreCase(chooseHouse)) {
                showAllHouseInfo();
                System.out.print("请重新选择房屋信息编号(-1退出):");
                int sureInfoId = ReadTool.readInt();
                if(sureInfoId==-1){
                    System.out.println("你退出了租房");
                    return;
                }
                houseInfo = houseInfoService.select(sureInfoId);
                if(houseInfo.getId()==0){
                    System.out.println("查无此房屋，请重新输入！");
                }
                host = hostService.select(houseInfo.getHostId());
                house = houseService.select(houseInfo.getHouseId());
                System.out.println("您重新选择的意向房屋信息如下:");
                System.out.println("编号\t\t房主\t\t电话\t\t位置\t\t月租\t状态");
                System.out.println(houseInfo.getId()+"\t\t"+host.getName()+"\t"+host.getPhone()+"\t"+house.getAddress()+"\t"+house.getRent()+"\t\t"+house.getState());
            }
            break;
        }
        System.out.println("--------请确认以下信息是否自己留言信息----");
        System.out.println("姓名:"+name);
        System.out.println("电话:"+phone);
        System.out.println("留言:"+message);
        System.out.print("确认是否自己留言信息(y/n):");
        String key = ReadTool.selectYorN();
        if("N".equalsIgnoreCase(key)){
            System.out.println("请重新输入您的信息，回车键跳过修改！");
            System.out.print("请重新输入你的姓名("+name+"):");
            name = ReadTool.readString(10,name);
            System.out.print("请重新输入你的电话("+phone+"):");
            phone = ReadTool.readString(11,phone);
            System.out.print("请重新输入你的留言(100字以内):");
            message = ReadTool.readString(100,message);
        }
        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setPhone(phone);
        newMessage.setIsRead(false);
        newMessage.setLocalDateTime(new Timestamp(System.currentTimeMillis()));
        newMessage.setCustomerName(name);
        newMessage.setHouseInfoId(houseInfo.getId());
        messageService.add(newMessage);
        System.out.println("已留言，近期将有客服联系您看房，请注意接听！");
    }
    public boolean searchHouseByRent(){
        System.out.println("-----------按房屋月租搜索---------");
        System.out.print("请输入接受房子最高月租:");
        int mostRent = ReadTool.readInt();
        List<HouseInfo> houseInfos = houseInfoService.select(null);
        boolean flag = false;
        System.out.println("编号\t\t房主\t\t电话\t\t位置\t\t月租\t\t状态");
        for (HouseInfo houseInfo:houseInfos){
            House house = houseService.select(houseInfo.getHouseId());
            if(house.getRent()>mostRent){
                continue;
            }

            showHouseInfoById(houseInfo.getId());
            flag = true;
        }
        return flag;
    }
    public boolean searchHouseByAddress(){
        System.out.println("-----------按房屋位置搜索---------");
        System.out.print("请输入房子位置:");
        String address = ReadTool.readString(10);
        List<HouseInfo> houseInfos = houseInfoService.select(null);
        boolean flag = false;
        System.out.println("编号\t\t房主\t\t电话\t\t位置\t\t月租\t\t状态");
        for (HouseInfo houseInfo:houseInfos){
            House house = houseService.select(houseInfo.getHouseId());
            if(!house.getAddress().contains(address.trim())){
                continue;
            }

            showHouseInfoById(houseInfo.getId());
            flag = true;
        }
        return flag;
    }
    public boolean searchHouseByState(){
        System.out.println("----------未出租房屋--------");
        List<HouseInfo> houseInfos = houseInfoService.select(null);
        boolean flag = false;
        System.out.println("编号\t\t房主\t\t电话\t\t位置\t\t月租\t\t状态");
        for (HouseInfo houseInfo:houseInfos){
            House house = houseService.select(houseInfo.getHouseId());
            if(!house.getState().equals("未出租")){
                continue;
            }

            showHouseInfoById(houseInfo.getId());
            flag = true;
        }
        return flag;
    }
    public boolean searchIntegrative(){
        System.out.println("----------综合搜索--------");
        System.out.print("请输入房子位置(回车表示忽略):");
        String address = ReadTool.readString(10,"");
        System.out.print("请输入房子的最高月租(回车表示忽略):");
        int rent = ReadTool.readInt(Integer.MAX_VALUE);
        boolean flag = false;
        List<HouseInfo> houseInfos = houseInfoService.select(null);
        System.out.println("编号\t\t房主\t\t电话\t\t位置\t\t月租\t\t状态");
        for (HouseInfo houseInfo:houseInfos){
            House house = houseService.select(houseInfo.getHouseId());
            if(!"".equals(address)){
                if(!house.getAddress().contains(address.trim())){
                    continue;
                }
                if(rent<house.getRent()){
                    continue;
                }
                showHouseInfoById(houseInfo.getId());
                flag = true;
            }else {
                if(rent>house.getRent()){
                    showHouseInfoById(houseInfo.getId());
                    flag = true;
                }
            }

        }
        return flag;
    }
    public void searchHouseInfo(){

        boolean loop = true;
        while (loop){
            System.out.println("----------搜索房屋信息--------");
            System.out.println("\t\t1 按位置搜索");
            System.out.println("\t\t2 按月租区间搜索");
            System.out.println("\t\t3 按是否出租搜索");
            System.out.println("\t\t4 综合搜索");
            System.out.println("\t\t5 返回上一级");
            System.out.print("请输入你的选择:");
            int key = ReadTool.readInt(1,5);
            switch (key){
                case 1:
                    if(searchHouseByAddress()){
                        askIsRent();
                    }else {
                        System.out.println("-------暂无符合该位置的房屋信息---------");
                    }
                    break;
                case 2:
                    if(searchHouseByRent()){
                        askIsRent();
                    }else {
                        System.out.println("-------暂无符合该月租的房屋信息---------");
                    }

                    break;
                case 3:
                    if(searchHouseByState()){
                        askIsRent();
                    }else {
                        System.out.println("-------暂无未出租的房屋---------");
                    }

                    break;
                case 4:
                    if(searchIntegrative()){
                        askIsRent();
                    }else {
                        System.out.println("-------暂无符合该条件的房屋信息---------");
                    }
                    break;
                case 5:
                    loop = false;
                    break;

            }
        }

    }
}
