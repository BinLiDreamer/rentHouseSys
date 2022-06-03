package view;

import bean.Host;
import service.HostService;
import service.impl.HostServiceImpl;
import tool.ReadTool;

import java.util.List;

public class HostManageSys {
    private HostService hostService = new HostServiceImpl();

    /**
     * 功能：显示业主管理的主页面，并接收用户的输入并跳转相应界面
     * 包括 1、查看所有业主
     *     2、添加业主
     *     3、修改业主信息
     *     4、删除业主
     *     5、返回上一级
     */
    public void select(){
        boolean loop = true;
        while (loop){
            System.out.println("------------业主管理----------");
            System.out.println("\t\t1 查看所有业主");
            System.out.println("\t\t2 添 加 业 主");
            System.out.println("\t\t3 修改业主信息");
            System.out.println("\t\t4 删 除 业 主");
            System.out.println("\t\t5 返回上一级");
            System.out.print("请输入你的选择(输入序号):");
            int key = ReadTool.readInt(1,5);
            switch (key){
                case 1:
                    System.out.println("----------所有业主信息----------");
                    hostListAllShow();
                    break;
                case 2:
                    addHost();
                    break;
                case 3:
                    updateHostInfo();
                    break;
                case 4:
                    deleteHost();
                    break;
                case 5:
                    loop = false;
                    break;
            }
        }
    }

    /**
     * 功能：显示所有的业主信息
     */
    public void hostListAllShow(){
        //传递null作为select的参数将返回所有的业主的列表
        List<Host> hostList = hostService.select(null);
        System.out.println("编号\t\t姓名\t\t\t电话");
        for (Host host:hostList){
            System.out.println(host.getId()+"\t\t"+host.getName()+"\t\t"+host.getPhone());
        }
    }

    /**
     * 功能：添加业主界面并接收用户输入，添加成功后给出是否添加业主成功
     */
    public void addHost(){
        System.out.println("----------添加业主----------");
        System.out.print("请输入业主姓名:");
        String name = ReadTool.readString(10);
        System.out.print("请输入业主电话:");
        String phone = ReadTool.readString(11);
        /**
         * 根据用户输入创建一个Host对象，并传给service层进行添加，根据返回是否成功给出相应提示
         */
        Host host = new Host();
        host.setName(name);
        host.setPhone(phone);
        if(hostService.addHost(host)){
            System.out.println("添加业主成功！");
        }else {
            System.out.println("添加业主失败！");
        }
    }

    /**
     * 功能：更新业主信息，先显示所有业主信息，给用户选择业主编号，根据用户输入的编号调用service层进行查询
     * 如果返回的是业主id号为0，则表示没有该业主，给出相应提示
     * 如果有相应的业主信息，则提示用户输入并获取相应信息传给service层进行信息更新，并给出相应提示
     */
    public void updateHostInfo(){
        boolean loop = true;
        Host host = null;
        int id = -1;
        while (loop){
            System.out.println("----------修改业主信息----------");
            hostListAllShow();
            System.out.print("请选择需要修改的业主的编号(-1/取消修改):");
            id = ReadTool.readInt();
            if(id==-1){
                System.out.println("你取消了修改操作!");
                return;
            }
            host = hostService.select(id);
            if(host.getId()==0){
                System.out.println("查无此业主,请重新选择");
                continue;
            }
            break;
        }
        System.out.print("姓名("+host.getName()+"):");
        String name = ReadTool.readString(10,host.getName());
        System.out.print("电话("+host.getPhone()+"):");
        String phone = ReadTool.readString(11,host.getPhone());
        Host updateHost = new Host(id,name,phone,host.getCount());
        if(hostService.update(updateHost)){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
    }

    /**
     * 功能：删除业主信息，展示所有业主，输入编号进行删除，期间可以退出，删除前对业主做相应的校验
     * 1、根据id号查询是否存在该业主
     * 2、根据该业主发布的信息的数量判断是否能删除该业主信息，以保证数据的一致性，业主相对于发布的房屋信息来说属于父表数据
     *    所以删除父表数据的时候需要对子表是否引用父表数据作为判断
     * 3、再次显示用户需要删除的业主信息并确认是否删除
     */
    public void deleteHost(){
        boolean loop = true;
        Host host = null;
        int id = -1;
        while (loop){
            System.out.println("----------删除业主----------");
            hostListAllShow();
            System.out.print("请选择需要删除的业主的编号(-1/取消修改):");
            id = ReadTool.readInt();
            if(id==-1){
                System.out.println("你取消了修改操作!");
                return;
            }
            host = hostService.select(id);
            if(host.getId()==0){
                System.out.println("查无此业主,请重新选择");
                continue;
            }
            //判断该业主是否发布过房屋信息，如果要删除，必须是目前尚未发布过房屋信息的业主
            if(host.getCount()>0){
                System.out.println("该业主的租房信息已发布，无法删除！");
                return;
            }
            break;
        }
        System.out.println("------你本次要删除的业主为------");
        System.out.println("编号\t\t姓名\t\t\t电话");
        System.out.println(host.getId()+"\t\t"+host.getName()+"\t\t"+host.getPhone());
        while (true){
            System.out.print("请确认要删除业主(y/n),请小心选择:");
            String choice = ReadTool.readString(1);
            if("N".equalsIgnoreCase(choice)){
                System.out.println("你取消了删除操作！");
                return;
            }
            break;
        }
        if(hostService.delete(host)){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
    }
    public void selectHost(Host host){

    }
}
