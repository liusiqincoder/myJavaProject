package View.ToolComponent;

import Service.PanelManage;
import Util.systemInfo;
import View.modifyFrame;
import View.start;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 工具类上的修改个人信息图标
 * 点击后弹出新的界面让用户修改信息
 * Created by Administrator on 2018/11/27.
 */
public class InfoLabel extends JLabel implements MouseListener{
    private ImageIcon loginIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/个人信息.png"));
//    private JPanel InfoPanel=new JPanel();
//    private PanelManage manage=null;

    public InfoLabel() {
        setSize(systemInfo.buttonSize,systemInfo.buttonSize);
        setIcon(loginIcon);
        setToolTipText("修改个人信息");
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new modifyFrame("修改个人信息", start.getUser());
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
