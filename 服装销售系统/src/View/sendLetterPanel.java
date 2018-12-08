package View;

import Model.User;
import Service.Impl.letterServiceImpl;
import Service.PanelManage;
import Service.letterService;
import Util.systemInfo;
import View.ToolComponent.myFont;
import View.ToolComponent.myPanel;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 用户发送邮件界面
 * @Parameter
 * Created by Administrator on 2018/11/30.
 */
public class sendLetterPanel extends myPanel implements MouseListener,FocusListener{
    private JTextField toIdText=new JTextField("接收一方的ID");
    private JTextArea messageArea=new JTextArea("输入消息");
    private JButton sendBtn=new JButton("发送");
    private User user=null;
//    private PanelManage manage=null;

    public sendLetterPanel(User user) {
        super("");
        this.user=user;
//        this.manage=manage;
//        setBounds(0,10,systemInfo.mainPaneWid-20,systemInfo.mainPaneHeight);
        init();
        repaint();
    }

    private void init(){

        toIdText.setBounds(systemInfo.mainMidX-100,70,200,30);
        toIdText.addFocusListener(this);
        messageArea.setBounds(systemInfo.mainMidX-100,110,200,100);
        messageArea.addFocusListener(this);

        sendBtn.setBounds(systemInfo.mainMidX-10,220,3* systemInfo.buttonSize,2*systemInfo.buttonSize);
        sendBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        sendBtn.setFont(myFont.Static);
        sendBtn.addMouseListener(this);
        sendBtn.setForeground(Color.white);
//        sendBtn.setToolTipText("写邮件");
        add(toIdText);
        add(messageArea);
        add(sendBtn);
        repaint();
    }

    /*
    * 发送信件函数
    * */
    private void sendLetter(){
        String to_id=toIdText.getText(),message=messageArea.getText();
        if(to_id.equals("")||to_id.equals("接收一方的ID")
                ||message.equals("")||message.equals("输入消息")){
            JOptionPane.showMessageDialog(this, "发送失败,必要信息未填写");
            return;
        }

        letterService service=new letterServiceImpl();
        if (service.sendLetter(user.getId(),to_id,message)){
            JOptionPane.showMessageDialog(this, "发送成功");
        }else {
            JOptionPane.showMessageDialog(this, "发送失败");
        }

        //提示信息处理
        toIdText.setText("接收一方的ID");
        messageArea.setText("输入信息");
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==sendBtn){
            sendLetter();
        }
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

    /*
    * 提示信息处理
    * */
    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource()==toIdText){
            if(toIdText.getText().equals("接收一方的ID")){
                toIdText.setText("");
            }
        }else if(e.getSource()==messageArea){
            if(messageArea.getText().equals("输入消息")){
                messageArea.setText("");
            }
        }
    }

    /*
    * 提示信息处理
    * */
    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource()==toIdText){
            if(toIdText.getText().equals("")){
                toIdText.setText("接收一方的ID");
            }
        }else if(e.getSource()==messageArea){
            if(messageArea.getText().equals("")){
                messageArea.setText("输入消息");
            }
        }
    }
}
