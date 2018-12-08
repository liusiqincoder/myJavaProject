package View.ToolComponent;

import Util.systemInfo;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * 点击通知栏消息后显示的小窗口
 * Created by Administrator on 2018/11/22.
 */
public class smallFrame extends JFrame implements WindowListener{

    private boolean close=true;
//    private String Info="";
    private String picture="";
    private JButton ok=new JButton("确认");
    private myPanel mainPanel=new myPanel(systemInfo.loader.getResource(systemInfo.background));
    private messageLabel InfoPanel=new messageLabel();

    public smallFrame(){
        init();
    }
    public smallFrame(String name){
        super(name);
        init();
    }

    private void init(){
        mainPanel.setLayout(null);
        mainPanel.setSize(systemInfo.smallWidth,systemInfo.smallHeight);
        setLayout(null);
        addWindowListener(this);
//        ok.setBounds(systemInfo.smallWidth/2-systemInfo.buttonSize,systemInfo.smallHeight-2*systemInfo.buttonSize,
//                      2*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        ok.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
//        ok.setFont(myFont.Static);

        add(mainPanel);
        mainPanel.add(InfoPanel);
//        mainPanel.add(ok);
        setBounds(systemInfo.mainMidX,systemInfo.mainMidY,systemInfo.smallWidth,systemInfo.smallHeight);
    }

    public void setInfo(String Info){
        InfoPanel.setText(Info);
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
        System.out.println(picture);
        myPanel ppanel=new myPanel(picture);
        ppanel.setBounds(systemInfo.smallImageX,systemInfo.smallImageY,systemInfo.smallImageWidth,systemInfo.smallImageheight);
        mainPanel.add(ppanel);
        if(InfoPanel.getText()!=null){
            System.out.println(InfoPanel.getText());
            InfoPanel.setBounds(systemInfo.smallImageWidth+10,0,systemInfo.smallImageWidth,systemInfo.smallHeight);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
//        close=false;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        close=true;
        setVisible(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean colse) {
        this.close = colse;
    }
}
