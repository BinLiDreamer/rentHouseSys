package view;

import bean.Host;
import bean.House;
import bean.HouseInfo;
import service.HostService;
import service.HouseInfoService;
import service.HouseService;
import service.impl.HostServiceImpl;
import service.impl.HouseInfoServiceImpl;
import service.impl.HouseServiceImpl;
import tool.ReadTool;

import java.util.List;

/**
 * 功能：后台管理房屋信息的页面
 */
public class HouseInfoManageSys {
    private HostManageSys hostManageSys = new HostManageSys();
    private HouseManageSys houseManageSys = new HouseManageSys();
    private HostService hostService = new HostServiceImpl();
    private HouseService houseService = new HouseServiceImpl();
    private HouseInfoService houseInfoService = new HouseInfoServiceImpl();
    private HouseInfoView houseInfoView = new HouseInfoView();
    public void houseInfoMenu(){
        boolean loop = true;
        while (loop){
            System.out.println("----------房屋信息管理----------");
            System.out.println("\t\t1 发布房屋信息");
            System.out.println("\t\t2 删除房屋信息");
            System.out.println("\t\t3 查看房屋信息");
            System.out.println("\t\t4 返回上一级");
            System.out.print("请输入你的选择(输入序号):");
            int key = ReadTool.readInt(1,4);
            switch (key){
                case 1:
                    publishMessage();
                    break;
                case 2:
                    deleteHouseInfo();
                    break;
                case 3:
                    houseInfoView.showAllHouseInfo();
                    break;
                case 4:
                    loop = false;
                    break;
            }
        }
    }

    public void deleteHouseInfo(){
        System.out.println("-------删除房屋信息---------");
        houseInfoView.showAllHouseInfo();
        System.out.print("请选择需要删除的房屋信息编号(-1取消):");
        int infoId = ReadTool.readInt();
        if(infoId==-1){
            System.out.println("你取消了删除房屋信息操作！");
            return;
        }
        HouseInfo houseInfo = houseInfoService.select(infoId);
        if(houseInfo.getId()==0){
            System.out.println("该编号房屋信息不存在，请查证后再操作！");
            return;
        }
        System.out.println("请确认以下是否需要删除的房屋信息！");
        System.out.println("编号\t\t房主\t\t电话\t\t位置\t\t月租\t\t状态");
        Host host = hostService.select(houseInfo.getHostId());
        House house = houseService.select(houseInfo.getHouseId());
        System.out.println(houseInfo.getId()+"\t\t"+host.getName()+"\t"+host.getPhone()+"\t"+house.getAddress()+"\t"+house.getRent()+"\t\t"+house.getState());
        System.out.print("请确认是否删除(y/n):");
        String isOrNot = ReadTool.selectYorN();
        if("N".equalsIgnoreCase(isOrNot)){
            System.out.println("你取消了删除操作！");
            return;
        }
        host.setCount(host.getCount()-1);
        house.setIsPublish(false);

        if(houseInfoService.delete(houseInfo)&&hostService.update(host)&&houseService.update(house)){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }

    }
    public void publishMessage(){
        System.out.println("-------发布房屋信息--------");
        hostManageSys.hostListAllShow();
        System.out.print("请选择业主编号:");
        int hostId = ReadTool.readInt();
        Host host = hostService.select(hostId);
        if(host.getId()==0){
            System.out.println("业主不存在，请查证后再发布！");
            return;
        }
        List<House> houses= houseManageSys.showNotYetPublishHouses();
        System.out.print("请选择房屋编号:");
        int houseId = ReadTool.readInt();
        House house = houseService.select(houseId);
        if(!houses.contains(house)||house.getIsPublish()){
            System.out.println("房屋不存在或者已发布，请查证后再发布！");
            return;
        }
        HouseInfo houseInfo = new HouseInfo();
        houseInfo.setHouseId(houseId);
        houseInfo.setHostId(hostId);
        host.setCount(host.getCount()+1);
        house.setIsPublish(true);
        if(houseInfoService.add(houseInfo)&&houseService.update(house)&&hostService.update(host)){
            System.out.println("发布成功！");
        }else {
            System.out.println("发布失败！");
        }
    }
}
