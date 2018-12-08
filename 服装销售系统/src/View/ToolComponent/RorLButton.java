package View.ToolComponent;

import Model.User;
import Service.PanelManage;
import Util.systemInfo;
import View.RegisterPanel;
import View.loginPane;
import View.start;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 登陆或注册切换按钮，登陆后点击可以退出登陆
 * @Parameter  login  登陆状态
 *             comIn  是否进入了用户界面
 *             status  用户职位
 *             loginIcon，registerIcon  图片缓存
 * Created by Administrator on 2018/11/24.
 */
public class RorLButton extends JLabel implements MouseListener {
    private boolean login=true,comIn=false;
//    private myFrame frame=null;
    private int status= User.MANAGER;
    private ImageIcon loginIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/登陆.png")),
            registerIcon=new ImageIcon(systemInfo.loader.getResource("Resource/Picture/注册.png"));

    public RorLButton(){
        setSize(systemInfo.buttonSize,systemInfo.buttonSize);
        setIcon(registerIcon);
        setToolTipText("注册");
        addMouseListener(this);
        setForeground(Color.white);
        setFont(myFont.Static);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(login){
            setIcon(loginIcon);
            login=false;
            setToolTipText("登陆");
            start.setRegisterPanel();
        }else if (!comIn){
            setIcon(registerIcon);
            login = true;
            setToolTipText("注册");
            start.setLoginisterPanel();
        }else if (comIn){
            logout();
        }
    }

    public void logout(){
        start.setUser(new User());
        setText("");
        setIcon(loginIcon);
        login=true;
        PanelManage.clear();
        start.addFrame();
        setToolTipText("登陆");
        start.setLoginisterPanel();
        comIn=false;
    }

    public void login(User user){
        start.setUser(user);
        setSize(3*systemInfo.buttonSize,systemInfo.buttonSize);
        login=false;
        comIn=true;
        setIcon(null);
        setText("你好,"+user.getCount());
        setToolTipText("点击退出");
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
