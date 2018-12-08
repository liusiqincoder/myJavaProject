package View;

import Model.User;
import Util.systemInfo;
import View.ToolComponent.myFrame;
import View.ToolComponent.myPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 开始界面，包含用户的登陆/注册界面
 * @Parameter    shopperPanel   店长登陆界面
 *                managerPanel  管理员登陆界面
 *               solderPanel    销售员登陆界面
 *               RegShopperPanel  店长注册界面
 *               RegManagerPanel   管理员注册界面
 *               RegSolderPanel    销售员注册界面
 *
 *               frame        由外部传来的myFrame
 * Created by Administrator on 2018/12/6.
 */
public class startMainPanel extends JTabbedPane implements ChangeListener {
    private static myPanel shopperPanel=null,
            managerPanel=null,
            solderPanel=null,
            RegShopperPanel=new RegisterPanel(User.SHOP_KEEPER),
            RegManagerPanel=new RegisterPanel(User.MANAGER),
            RegSolderPanel=new RegisterPanel(User.SOLDER);

    private final static ImageIcon shopkeeper=new ImageIcon(start.class.getClassLoader().getResource(systemInfo.shopKeeper));
    private final static ImageIcon manager=new ImageIcon(start.class.getClassLoader().getResource(systemInfo.manager));
    private final static ImageIcon solder=new ImageIcon(start.class.getClassLoader().getResource(systemInfo.solder));

    private myFrame frame=null;
    
    public startMainPanel(myFrame frame) {
        this.frame=frame;
        init();
    }

    private void init(){

        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);

        addChangeListener(this);
        
        shopperPanel=new loginPane(frame,this);
        managerPanel=new loginPane(frame,this);
        solderPanel=new loginPane(frame,this);


        addTab("管理员",manager,managerPanel);
        addTab("店长",shopkeeper,shopperPanel);
        addTab("销售员",solder,solderPanel);
        repaint();
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if(((JTabbedPane)e.getSource()).getSelectedIndex()==0){
            myFrame.setStatus(User.MANAGER);
        }else if(((JTabbedPane)e.getSource()).getSelectedIndex()==1){
            myFrame.setStatus(User.SHOP_KEEPER);
        }else if(((JTabbedPane)e.getSource()).getSelectedIndex()==2){
            myFrame.setStatus(User.SOLDER);
        }
    }

    //由注册界面切换到登陆界面
    public void setLoginPanel(){
        remove(RegManagerPanel);
        remove(RegShopperPanel);
        remove(RegSolderPanel);
        addTab("管理员",manager,managerPanel);
        addTab("店长",shopkeeper,shopperPanel);
        addTab("销售员",solder,solderPanel);
        repaint();
    }

    //由登陆界面切换到注册界面
    public void setRegisterPanel(){
        remove(managerPanel);
        remove(shopperPanel);
        remove(solderPanel);
        addTab("管理员",manager,RegManagerPanel);
        addTab("店长",shopkeeper,RegShopperPanel);
        addTab("销售员",solder,RegSolderPanel);
        repaint();
    }
}
