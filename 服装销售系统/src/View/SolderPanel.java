package View;

import Model.User;
import Util.systemInfo;
import View.ToolComponent.myPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 销售员主界面
 * @Paraneter  sPanel  3个界面切换的容器
 *             soldPanel  查看并操作商品的界面
 *             catSoldPanel  查看销售情况界面
 *             solveMessagePanel   信件处理界面
 * Created by Administrator on 2018/12/5.
 */
public class SolderPanel extends myPanel  implements ChangeListener {

    private JTabbedPane sPanel=new JTabbedPane();
    private myPanel soldPanel=null,
            catSoldPanel=null,
            solveMessagePanel=null,nowPanel=null;

    private ImageIcon goodIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/商品.png")),
            stockIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/库存报表.png")),
            messageIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/消息.png"));

    private User user=null;

    public SolderPanel(User user) {
        super("");
        this.user=user;
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        init();
    }

    private void init(){
        soldPanel=new SoldGoodPanel(user);
        catSoldPanel=new solderSoldPanel(user);
        solveMessagePanel=new messageSolvePanel(user);
        nowPanel=soldPanel;
        sPanel.setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        sPanel.addTab("售卖商品",goodIcon,soldPanel);
        sPanel.addTab("销售表",stockIcon,catSoldPanel);
        sPanel.addTab("消息通知",messageIcon,solveMessagePanel);
        sPanel.addChangeListener(this);
        add(sPanel);
        repaint();
    }

    @Override
    public boolean next() {
        if (nowPanel.next()){
            return true;
        }
        return false;
    }

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
            nowPanel=soldPanel;
        }else if(panel.getSelectedIndex()==1){
            nowPanel= catSoldPanel;
        }else if(panel.getSelectedIndex()==2){
            nowPanel=solveMessagePanel;
        }
    }
}
