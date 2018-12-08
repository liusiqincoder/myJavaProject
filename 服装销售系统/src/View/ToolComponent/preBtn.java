package View.ToolComponent;

import Service.PanelManage;
import Util.systemInfo;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**界面前进按钮
 * Created by Administrator on 2018/11/21.
 */
public class preBtn extends JButton implements MouseListener{

    private final static ImageIcon
            pre1=new ImageIcon(systemInfo.loader.getResource(systemInfo.preBtn1)),
            pre2=new ImageIcon(systemInfo.loader.getResource(systemInfo.preBtn2)),
            pre3=new ImageIcon(systemInfo.loader.getResource(systemInfo.preBtn3));


    public preBtn(){
//        this.manage=manage;
        setSize(systemInfo.buttonSize,systemInfo.buttonSize);
        setIcon(pre1);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setIcon(pre3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                setIcon(pre2);
            }
        }).start();
        PanelManage.next();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("mouse enter");
        setIcon(pre2);
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("mouse exit");
        setIcon(pre1);
    }
}
