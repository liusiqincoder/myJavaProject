package View.ToolComponent;

import Service.PanelManage;
import Util.systemInfo;
import com.sun.awt.AWTUtilities;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * 通知栏，独立的线程，程序开启就启动。
 * 用队列实现存储信息
 * @Parameter  image   背景图片
 *              messages  消息队列
 * Created by Administrator on 2018/11/22.
 */
public class MessagePanel extends JScrollPane {

    private static ImageIcon image;
    private static Queue<Message> messages=new LinkedList<>();

    public MessagePanel(){
        setLayout(null);
        setBounds(systemInfo.messagePanelX,systemInfo.messagePanelY,systemInfo.messagePanelWidth,systemInfo.mainPaneHeight);
//        setBackground(Color.YELLOW);
        this.image=new ImageIcon(systemInfo.loader.getResource(systemInfo.messagePanel));
//        putMessage(new Message("服装销售系统",Message.Manager));
//        setOpaque(false);
        ActionListener lisener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!messages.isEmpty()) {
                    Message m = messages.poll();
                    playMessage(m);
                }
            }
        };

        new Timer(200,lisener).start();
    }

   /* @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getImage(),0,0, systemInfo.messagePanelWidth,systemInfo.messagePanelHeight,this);
    }*/

    /*
    * 向通知栏放置消息
    * */
    public synchronized static void putMessage(Message m){
        messages.add(m);
    }

    /*
    *消息在通知栏上移动
    * */
    private void playMessage(Message m){
        m.setBounds(systemInfo.messageWidth,0,systemInfo.messageWidth, systemInfo.messageHeight);
        add(m);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=systemInfo.messageWidth;i>=-systemInfo.messageWidth;i-=systemInfo.messageSpeed) {
                        Thread.currentThread().sleep(200);
                        m.setBounds(i, 0, systemInfo.messageWidth, systemInfo.messageHeight);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

}
