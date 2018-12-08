package View.ToolComponent;

import Service.PanelManage;
import Util.systemInfo;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.*;

/**
 * @Description    界面回到前一界面的按钮
 * @Parameter    back1，back2，back3  点击按钮时变换效果图，用的比较频繁就缓存在内存
 *
 * Created by Administrator on 2018/11/21.
 */
public class backBtn extends JButton implements MouseListener{

    private final static ImageIcon
            back1=new ImageIcon(systemInfo.loader.getResource(systemInfo.backBtn1)),
            back2=new ImageIcon(systemInfo.loader.getResource(systemInfo.backBtn2)),
            back3=new ImageIcon(systemInfo.loader.getResource(systemInfo.backBtn3));
//    private PanelManage manage=null;
    public backBtn(){
        setSize(systemInfo.buttonSize,systemInfo.buttonSize);
        setIcon(back1);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setIcon(back3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                setIcon(back2);
            }
        }).start();
        PanelManage.pre();
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
        setIcon(back2);
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("mouse exit");
        setIcon(back1);
    }
}
