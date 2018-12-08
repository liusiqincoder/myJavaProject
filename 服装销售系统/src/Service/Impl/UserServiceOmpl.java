package Service.Impl;

import DAO.Impl.UserDaoImpl;
import DAO.UserDao;
import Model.Manager;
import Model.ShopKeeper;
import Model.Solder;
import Model.User;
import Service.PanelManage;
import Service.UserService;
import Util.UUIDUtils;
import Util.systemInfo;
import View.ManagerPanel;
import View.ShopKepperPanel;
import View.SolderPanel;
import View.ToolComponent.Message;
import View.ToolComponent.MessagePanel;
import View.start;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/11/23.
 */
public class UserServiceOmpl implements UserService {

    @Override
    public boolean exisit(User user) {
        UserDao dao=new UserDaoImpl();
        String name=null;
        if(user instanceof Manager){
            name="Manager";
        }else if(user instanceof ShopKeeper){
            name="ShopKeeper";
        }else {
            name="Solder";
        }
        return dao.check(user.getCount(),user.getPassword(),name);
    }

    @Override
    public void setUserInfo(User user) {
        UserDao dao=new UserDaoImpl();
        String table=null;
        if(user instanceof ShopKeeper){
            table="ShopKeeper";
        }else if(user instanceof Manager){
            table="Manager";
        }else {
            table="Solder";
        }
        dao.selectByCount(user.getCount(),table,user);
        if(table.equals("Manager")){
            MessagePanel.putMessage(new Message("欢迎管理员"+user.getName()+"上线", systemInfo.Manager));
        }else if(table.equals("ShopKeeper")){
            MessagePanel.putMessage(new Message("欢迎店长"+user.getName()+"上线", systemInfo.Manager));
        }
    }

    @Override
    public boolean exisitCount(User user) {
        UserDao dao=new UserDaoImpl();
        String table="";
        User user1=null;
        if(user instanceof Manager){
            table="Manager";
            user1=new Manager();
        }else if(user instanceof ShopKeeper){
            table="ShopKeeper";
            user1=new ShopKeeper();
        }else if(user instanceof Solder){
            table="Solder";
            user1=new Solder();
        }
        dao.selectByCount(user.getCount(),table,user1);
        if(user1.getCount()!=null&&user1.getCount()!=""){
            return true;
        }
        return false;
    }

    @Override
    public void toUserPanel(User user) {
      /*  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        start.toolBar.setUser(user);
        start.toolBar.setLogin(true);
        if (user instanceof ShopKeeper){
            PanelManage.add(new ShopKepperPanel(user));
            PanelManage.next();
        }else if (user instanceof Manager){
            PanelManage.add(new ManagerPanel(user));
            PanelManage.next();
        }else if (user instanceof Solder){
            PanelManage.add(new SolderPanel(user));
            PanelManage.next();
        }
    }

    @Override
    public boolean insert(User user) {
        UserDao dao=new UserDaoImpl();
        String table;
        if(user instanceof Manager){
            table="Manager";
        }else if(user instanceof ShopKeeper){
            table="ShopKeeper";
        }else {
            table="Solder";
        }
        user.setJob(table);
        user.setId(UUIDUtils.getUUID64());
        dao.insertUsers(user);
        return dao.insert(user,table);
    }

    @Override
    public int selectAllUsers(Object[][] gs) {
        UserDao dao=new UserDaoImpl();
        List<User> allUsers=dao.selectAllUsers();
        for (int i=0;i<allUsers.size();i++){
            gs[i][0]=allUsers.get(i).getId();
            gs[i][1]=allUsers.get(i).getJob();
            gs[i][2]=allUsers.get(i).getCount();
        }
        return allUsers.size();
    }

    @Override
    public boolean addUsers(String job, String count) {
        UserDao dao=new UserDaoImpl();
        User user=new User();
        user.setId(UUIDUtils.getUUID64());
        user.setCount(count);
        user.setJob(job);
        return dao.insertUsers(user);
    }

    @Override
    public boolean modifyUsersById(String id, String job, String count) {
        UserDao dao=new UserDaoImpl();
        User user=new User();
        user.setId(id);
        user.setCount(count);
        user.setJob(job);
        return dao.modifyUsers(user);
    }

    @Override
    public boolean deleteUsersById(String id) {
        UserDao dao=new UserDaoImpl();
        return dao.deleteUsers(id);
    }

    @Override
    public String selevtID(User user) {
        UserDao dao=new UserDaoImpl();
        String table;
        if(user instanceof Manager){
            table="Manager";
        }else if(user instanceof ShopKeeper){
            table="ShopKeeper";
        }else {
            table="Solder";
        }
        return dao.selectID(table,user);
    }

    @Override
    public void selectUserById(User user) {
        UserDao dao=new UserDaoImpl();
        dao.selectUserById(user);
    }

    @Override
    public boolean modifyUser(User user) {
        UserDao dao=new UserDaoImpl();
        String table="";

        if(user instanceof Manager){
            table="Manager";
        }else if(user instanceof ShopKeeper){
            table="shopKeeper";
        }else {
            table="Solder";
        }

        if(!dao.modifyUser(table,user)){
            return false;
        }
        dao.modifyUsers(user);
        return true;
    }

    @Override
    public void defyUser(String id, String defyId) {
        UserDao dao=new UserDaoImpl();
        dao.insert(UUIDUtils.getId(),id,defyId);
    }

    @Override
    public void cancelDefy(String id, String defyId) {
        UserDao dao=new UserDaoImpl();
        dao.deleteDefy(id,defyId);
    }
}
