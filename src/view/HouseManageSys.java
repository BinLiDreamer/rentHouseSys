package view;

import bean.House;
import service.HouseInfoService;
import service.HouseService;
import service.impl.HouseInfoServiceImpl;
import service.impl.HouseServiceImpl;
import tool.ReadTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：管理房屋的页面
 */
public class HouseManageSys {

    private HouseService houseService = new HouseServiceImpl();
    private HouseInfoService houseInfoService = new HouseInfoServiceImpl();

    public void select(){
        boolean loop = true;
        while (loop){
            System.out.println("------------房屋管理----------");
            System.out.println("\t\t1 查看所有房屋");
            System.out.println("\t\t2 添 加 房 屋");
            System.out.println("\t\t3 修改房屋信息");
            System.out.println("\t\t4 删 除 房 屋");
            System.out.println("\t\t5 返回上一级");
            System.out.print("请输入你的选择(输入序号):");
            int key = ReadTool.readInt(1,5);
            switch (key){
                case 1:
                    System.out.println("----------所有房屋信息----------");
                    showAllHouse();
                    break;
                case 2:
                    addHouse();
                    break;
                case 3:
                    updateHouse();
                    break;
                case 4:
                    deleteHouse();
                    break;
                case 5:
                    loop = false;
                    break;
            }
        }
    }
    public void showAllHouse(){
        List<House> houseList = houseService.select(null);
        System.out.println("编号\t\t位置\t\t\t月租\t\t状态");
        for (House house:houseList){
            System.out.println(house.getId()+"\t\t"+house.getAddress()+"\t\t"+house.getRent()+"\t\t"+house.getState());
        }

    }
    public void addHouse(){
        System.out.println("----------添加房屋----------");
        System.out.print("请输入房屋位置:");
        String address = ReadTool.readString(10);
        System.out.print("请输入房屋月租:");
        int rent = ReadTool.readInt();
        String state = null;
        while (true){
            System.out.print("请输入房屋状态(已出租/未出租):");
            state = ReadTool.readString(10);
            if("已出租".equals(state)||"未出租".equals(state)){
                break;
            }
            continue;
        }
        House house = new House();
        house.setAddress(address);
        house.setRent(rent);
        house.setState(state);
        if(houseService.add(house)){
            System.out.println("添加房屋成功！");
        }else {
            System.out.println("添加房屋失败！");
        }
    }
    public void updateHouse(){
        boolean loop = true;
        House house = null;
        int id = -1;
        while (loop){
            System.out.println("----------修改房屋----------");
            showAllHouse();
            System.out.print("请选择需要修改的房屋的编号(-1/取消修改):");
            id = ReadTool.readInt();
            if(id==-1){
                System.out.println("你取消了修改操作!");
                return;
            }
            house = houseService.select(id);
            if(house.getId()==0){
                System.out.println("查无此房屋,请重新选择");
                continue;
            }
            break;
        }
        System.out.println("你此次修改的房屋为：");
        System.out.println("编号:"+house.getId());
        System.out.print("位置("+house.getAddress()+"):");
        String address = ReadTool.readString(10,house.getAddress());
        System.out.print("月租("+house.getRent()+"):");
        int rent = ReadTool.readInt(house.getRent());
        System.out.print("状态("+house.getState()+"):");
        String state = ReadTool.readString(11,house.getState());
        House newHouse = new House(id,rent,address,state,false);
        if(houseService.update(newHouse)){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
    }
    public void deleteHouse(){
        boolean loop = true;
        House house = null;
        int id = -1;
        while (loop){
            System.out.println("----------删除房屋----------");
            showAllHouse();
            System.out.print("请选择需要删除的房屋的编号(-1/取消修改):");
            id = ReadTool.readInt();
            if(id==-1){
                System.out.println("你取消了修改操作!");
                return;
            }
            house = houseService.select(id);
            if(house.getId()==0){
                System.out.println("查无此房屋,请重新选择");
                continue;
            }
            if(house.getIsPublish()){
                System.out.println("该房屋已发布房屋信息，无法删除！");
                return;
            }
            break;
        }
        System.out.println("------你本次要删除的房屋为------");
        System.out.println("编号\t\t位置\t\t\t月租\t\t状态");
        System.out.println(house.getId()+"\t\t"+house.getAddress()+"\t\t"+house.getRent()+"\t\t"+house.getState());
        while (true){
            System.out.print("请确认要删除房屋(y/n),请小心选择:");
            String choice = ReadTool.readString(1);
            if("Y".equalsIgnoreCase(choice)){
                break;
            }else if("N".equalsIgnoreCase(choice)){
                System.out.println("你取消了删除操作！");
                return;
            }
        }
        if(houseService.delete(house)){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
    }
    public List<House> showNotYetPublishHouses(){
        System.out.println("------尚未发布的房子如下-------");

        List<House> houseList = houseService.select(null);
        System.out.println("编号\t\t位置\t\t\t月租\t\t状态");

        List<House> houses = new ArrayList<>();
        for (House house:houseList){
            if(!house.getIsPublish()){
                System.out.println(house.getId()+"\t\t"+house.getAddress()+"\t\t"+house.getRent()+"\t\t"+house.getState());
                houses.add(house);
            }

        }
        return houses;
    }
}
