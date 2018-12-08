package View;

import Model.Manager;
import Model.ShopKeeper;
import Model.Solder;
import Model.User;
import Service.Impl.UserServiceOmpl;
import Service.PanelManage;
import Service.UserService;
import Util.systemInfo;
import View.ToolComponent.myFont;
import View.ToolComponent.myFrame;
import View.ToolComponent.myPanel;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 登陆界面
 * @Parameter   userNameText  账号输入框
 *              passwordText  密码输入框
 *              owner         装载面板的容器
 * Created by Administrator on 2018/11/23.
 */
public class loginPane extends myPanel implements FocusListener,MouseListener{

    private JTextField userNameText=new JTextField();
    private JPasswordField passwordText=new JPasswordField();
    private JButton login=new JButton("登陆"),
                    reset=new JButton("重置");
//    private PanelManage manage=null;
//    private int status;
    private final static UserService userService=new UserServiceOmpl();

    private User user=null;
    private myFrame frame=null;
    private JTabbedPane owner=null;
//    private JLabel label=new JLabel();
    public loginPane(myFrame frame,JTabbedPane owner){
        super(systemInfo.loader.getResource(systemInfo.background));
        this.frame=frame;
        this.owner=owner;
//        this.manage=manage;
//        this.status=status;

        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);

        userNameText.setBounds(systemInfo.mainMidX-70,systemInfo.mainMidY-30,200,30);
        userNameText.setText("用户名/账号");
        userNameText.setFont(myFont.Static);
        userNameText.addFocusListener(this);

        passwordText.setBounds(systemInfo.mainMidX-70,systemInfo.mainMidY+10,200,30);
        passwordText.setText("密码");
        passwordText.setFont(myFont.Static);
        passwordText.addFocusListener(this);

        login.setBounds(systemInfo.mainMidX-2*systemInfo.buttonSize,systemInfo.mainMidY+50,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        login.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        login.setFont(myFont.Static);
        login.addMouseListener(this);
        login.setForeground(Color.white);

        reset.setBounds(systemInfo.mainMidX+2*systemInfo.buttonSize,systemInfo.mainMidY+50,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        reset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        reset.setFont(myFont.Static);
        reset.addMouseListener(this);
        reset.setForeground(Color.white);

        add(userNameText);
        add(passwordText);
        add(login);
        add(reset);
        repaint();
    }

    /*
    * 主要是输入框的一些提示处理
    * */
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==userNameText){
            if(userNameText.getText().equals("用户名/账号")){
                userNameText.setText("");
            }
        }else if(e.getSource()==passwordText){
            if(passwordText.getText().equals("密码")){
                passwordText.setText("");
            }
        }
    }

    /*
    * 主要是输入框的一些提示处理
    * */
    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==userNameText){
            if(userNameText.getText().equals("")){
                userNameText.setText("用户名/账号");
            }
        }else if(e.getSource()==passwordText){
            if(passwordText.getText().equals("")){
                passwordText.setText("密码");
            }
        }
    }

    /*
    * 按钮处理
    * */
    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("click");
        if(e.getSource()==login){
            String userName=userNameText.getText(),password=passwordText.getText();
            if(userName.equals("")||userName.equals("用户名/账号")){
                JOptionPane.showMessageDialog(this, "用户名不能为空");
                return;
            }else if (password.equals("")||password.equals("密码")){
                JOptionPane.showMessageDialog(this, "密码不能为空");
                return;
            }

            if(myFrame.getStatus()== User.MANAGER){
                user=new Manager();
            }else if(myFrame.getStatus()==User.SHOP_KEEPER){
                user=new ShopKeeper();
            }else {
                user=new Solder();
            }

            user.setCount(userName);
            user.setPassword(password);

            //检查账户密码是否正确
            if(userService.exisit(user)){
                userService.setUserInfo(user);
                JOptionPane.showMessageDialog(this, "登陆成功");

                //登陆注册界面不包含在可前进后退的界面，所以移除后再跳转
                frame.remove(owner);
                userService.toUserPanel(user);
            }else {
                JOptionPane.showMessageDialog(this, "密码错误");
            }

        }else if(e.getSource()==reset){
            userNameText.setText("用户名/账号");
            passwordText.setText("密码");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("press");
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
