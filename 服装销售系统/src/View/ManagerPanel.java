package View;

import Model.Manager;
import Model.User;
import Util.systemInfo;
import View.ToolComponent.myFrame;
import View.ToolComponent.myPanel;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 管理员主界面
 * @Parameter  nowPanel  主界面显示的面板
 * Created by Administrator on 2018/11/30.
 */
public class ManagerPanel extends myPanel implements ChangeListener {
    private JTabbedPane sPanel=new JTabbedPane();
    private myPanel modifyUserPanel=null,
            goodSoldPanel=null,
            solveMessagePanel=null,nowPanel=null;

    private ImageIcon goodIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/商品.png")),
            stockIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/库存报表.png")),
            messageIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/消息.png"));

    private User user=null;
    public ManagerPanel(User user) {
        super("");
        this.user=user;
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        init();
    }


    private void init(){
        modifyUserPanel=new userInfoPanel(user);
        goodSoldPanel=new goodInfoPanel(user);
        solveMessagePanel=new messageSolvePanel(user);
        nowPanel=modifyUserPanel;
        sPanel.setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        sPanel.addTab("修改用户",goodIcon,modifyUserPanel);
        sPanel.addTab("销售表",stockIcon,goodSoldPanel);
        sPanel.addTab("消息通知",messageIcon,solveMessagePanel);
        sPanel.addChangeListener(this);
        add(sPanel);
        repaint();
    }

    /*
    * 如果面板不能next，则PanelManager next
    * */
    @Override
    public boolean next() {
        if (nowPanel.next()){
            return true;
        }
        return false;
    }

    /*
     * 如果面板不能pre，则PanelManager pre
     * */
    @Override
    public boolean pre() {
        if (nowPanel.pre()){
            return true;
        }
        return false;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JTabbedPane panel= (JTabbedPane) e.getSource();
        if(panel.getSelectedIndex()==0){
            nowPanel=modifyUserPanel;
        }else if(panel.getSelectedIndex()==1){
            nowPanel= goodSoldPanel;
        }else if(panel.getSelectedIndex()==2){
            nowPanel=solveMessagePanel;
        }
    }
}
