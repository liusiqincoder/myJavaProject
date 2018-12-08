package View;

import Model.User;
import Service.PanelManage;
import Util.systemInfo;
import View.ToolComponent.*;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import javax.swing.event.ChangeListener;

/**
 * Created by Administrator on 2018/11/21.
 */
public class start {
    private static myFrame frame=null;
    public static startMainPanel panel=null;
    private static User user=new User();

    public  final static myToolBar toolBar=new myToolBar();
    public final  static MessagePanel messagePanel= new MessagePanel();

    public static void main(String[] args){
        try {
            // 设置窗口边框样式
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new start();
        messagePanel.putMessage(new Message("XXX服装销售店欢迎你",Message.Manager));
    }

    public start(){
        init();
    }

    private void init(){
//        mainPanel.setBounds(0,0,systemInfo.FrameWidth,systemInfo.FrameHeight);

        frame=new myFrame("服装销售系统");

        panel=new startMainPanel(frame);
//        PanelManage manage=new PanelManage(frame);
        PanelManage.setFrame(frame);

        frame.add(panel);
        frame.add(toolBar);
        frame.add(messagePanel);
        frame.repaint();
    }

    public static myFrame getFrame() {
        return frame;
    }

    public static void setFrame(myFrame frame) {
        start.frame = frame;
    }

    public static void addFrame(){
//        frame.setVisible(false);
//        frame=new myFrame("服装销售系统");

        frame.add(panel);
        frame.add(toolBar);
        frame.add(messagePanel);
        frame.repaint();
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        start.user = user;
    }

    public static void setRegisterPanel(){
        panel.setRegisterPanel();
    }

    public static void setLoginisterPanel(){
        panel.setLoginPanel();
    }
}
