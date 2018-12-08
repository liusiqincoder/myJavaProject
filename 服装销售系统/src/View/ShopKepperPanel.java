package View;

import Model.User;
import Service.PanelManage;
import Util.systemInfo;
import View.ToolComponent.myPanel;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.junit.Test;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 店长主界面
 *
 * @Parameter   modifyGoodPanel  修改商品界面（默认）
 *              goodSoldPanel    查看商品销售界面
 *              solveMessagePanel  信件处理界面
 *
 * Created by Administrator on 2018/11/23.
 */
public class ShopKepperPanel extends myPanel implements ChangeListener {
    private JTabbedPane sPanel=new JTabbedPane();
    private myPanel modifyGoodPanel=null,
                goodSoldPanel=null,
                solveMessagePanel=null,nowPanel=null;

    private ImageIcon goodIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/商品.png")),
                       stockIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/库存报表.png")),
                       messageIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/消息.png"));

    private User user=null;

    public ShopKepperPanel(User user){
        super("");
        this.user=user;
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        init();
    }

    private void init(){
        modifyGoodPanel=new goodInfoPanel(user);
        goodSoldPanel=new goodSoldPanel(user);
        solveMessagePanel=new messageSolvePanel(user);
        nowPanel=modifyGoodPanel;
        sPanel.setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        sPanel.addTab("修改商品",goodIcon,modifyGoodPanel);
        sPanel.addTab("销售情况",stockIcon,goodSoldPanel);
        sPanel.addTab("消息通知",messageIcon,solveMessagePanel);
        sPanel.addChangeListener(this);
        add(sPanel);
        repaint();
        sPanel.updateUI();
    }

    /*
    * 当前界面的向后滚动
    * */
    @Override
    public boolean next() {
        if (nowPanel.next()){
            return true;
        }
        return false;
    }

    /*
    * 当前界面的向前滚动
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
            nowPanel=modifyGoodPanel;
        }else if(panel.getSelectedIndex()==1){
            nowPanel= goodSoldPanel;
        }else if(panel.getSelectedIndex()==2){
            nowPanel=solveMessagePanel;
        }
    }
}
