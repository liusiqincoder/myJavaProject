package View;

import Model.Manager;
import Model.ShopKeeper;
import Model.Solder;
import Model.User;
import Service.Impl.UserServiceOmpl;
import Service.UserService;
import Util.SocketUtil;
import Util.systemInfo;
import View.ToolComponent.*;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 *用户注册界面
 *@Parameter  userNameText  用户名输入框
 *            countText     账户输入框
 *            passwordText   密码输入框
 *            retypeText     确认密码框
 *
 *            uploadPicture  上传图片按钮
 *
 *            f     图片文件
 *            show  文件选择器的面板
 *
 * Created by Administrator on 2018/11/23.
 */
public class RegisterPanel extends myPanel implements FocusListener,MouseListener {
    private JTextField userNameText=new JTextField("用户名"),
                       countText=new JTextField("账号");
//                       pictureText=new;
    private JPasswordField passwordText=new JPasswordField("输入密码"),
                           retypeText=new JPasswordField("确认密码");
    private JButton register=new JButton("注册"),
            reset=new JButton("重置"),uploadPicture=new JButton("图片");
    private FileNameExtensionFilter filter=new FileNameExtensionFilter("请选择头像","jpg","png","jpeg","bmp");

    private File f=null;
    private JLabel show=new JLabel();

    private JFileChooser chooser=new JFileChooser();
    private final static UserService userService=new UserServiceOmpl();
    private int status;

    public RegisterPanel(int status){
        super(systemInfo.loader.getResource(systemInfo.background));
        this.status=status;
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);

        userNameText.setBounds(systemInfo.mainMidX-70,systemInfo.mainMidY-100,200,30);
        userNameText.setFont(myFont.Static);
        userNameText.addFocusListener(this);

        countText.setBounds(systemInfo.mainMidX-70,systemInfo.mainMidY-60,200,30);
        countText.setFont(myFont.Static);
        countText.addFocusListener(this);

        passwordText.setBounds(systemInfo.mainMidX-70,systemInfo.mainMidY-20,200,30);
        passwordText.setFont(myFont.Static);
        passwordText.addFocusListener(this);

        retypeText.setBounds(systemInfo.mainMidX-70,systemInfo.mainMidY+20,200,30);
        retypeText.setFont(myFont.Static);
        retypeText.addFocusListener(this);

        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
//        chooser.setBounds(systemInfo.mainMidX-70,systemInfo.mainMidY+50,200,40);

        uploadPicture.setBounds(systemInfo.mainMidX-70,systemInfo.mainMidY+60,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        uploadPicture.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        uploadPicture.setFont(myFont.Static);
        uploadPicture.addMouseListener(this);
        uploadPicture.setForeground(Color.white);
        uploadPicture.setToolTipText("上传你的头像");

        register.setBounds(systemInfo.mainMidX-systemInfo.buttonSize+20,systemInfo.mainMidY+60,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        register.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        register.setFont(myFont.Static);
        register.addMouseListener(this);
        register.setForeground(Color.white);

        reset.setBounds(systemInfo.mainMidX+10+3*systemInfo.buttonSize,systemInfo.mainMidY+60,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        reset.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        reset.setFont(myFont.Static);
        reset.addMouseListener(this);
        reset.setForeground(Color.white);

        add(userNameText);
        add(passwordText);
        add(retypeText);
        add(countText);
        add(uploadPicture);
        add(register);
        add(reset);
        repaint();
    }

    /*
    * 提示信息处理
    * */
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==userNameText){
            if(userNameText.getText().equals("用户名")){
                userNameText.setText("");
            }
        }else if(e.getSource()==passwordText){
            if(passwordText.getText().equals("输入密码")){
                passwordText.setText("");
            }
        }else if(e.getSource()==retypeText){
            if(retypeText.getText().equals("确认密码")){
                retypeText.setText("");
            }
        }else if(e.getSource()==countText){
            if(countText.getText().equals("账号")){
                countText.setText("");
            }
        }
    }

    /*
    * 提示信息处理
    * */
    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==userNameText){
            if(userNameText.getText().equals("")){
                userNameText.setText("用户名");
            }
        }else if(e.getSource()==passwordText){
            if(passwordText.getText().equals("")){
                passwordText.setText("输入密码");
            }
        }else if(e.getSource()==countText){
            if(countText.getText().equals("")){
                countText.setText("账号");
            }
        }else if(e.getSource()==retypeText){
            if(retypeText.getText().equals("")){
                retypeText.setText("确认密码");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==register){
            register();
        }else if(e.getSource()==reset){
            reset();
        }else if(e.getSource()==uploadPicture){
            uploadPicture();
        }
    }

    private void uploadPicture() {
        chooser.showDialog(show,"选择你的头像上传");
        f=chooser.getSelectedFile();
    }

    private void reset() {
        userNameText.setText("用户名");
        passwordText.setText("输入密码");
        retypeText.setText("确认密码");
        countText.setText("账号");
    }

    /*
    * 注册函数
    * */
    private void register() {
        String userName=userNameText.getText(),password=passwordText.getText();
        if(userName.equals("")||userName.equals("用户名")){
            JOptionPane.showMessageDialog(this, "用户名不能为空");
            return;
        }else if (password.equals("")||password.equals("输入密码")){
            JOptionPane.showMessageDialog(this, "密码不能为空");
            return;
        }else if (retypeText.equals(password)){
            JOptionPane.showMessageDialog(this, "前后密码不一致");
            return;
        }else if (countText.equals("")||password.equals("账号")){
            JOptionPane.showMessageDialog(this, "账号不能为空");
            return;
        }

        User user=null;

        if(myFrame.getStatus()== User.MANAGER){
            user=new Manager();
        }else if(myFrame.getStatus()==User.SHOP_KEEPER){
            user=new ShopKeeper();
        }else {
            user=new Solder();
        }

        user.setCount(countText.getText());
        user.setName(userName);
        user.setPassword(password);
        user.setName(userNameText.getText());
//            String filePath=SocketUtil.uploadFile(status,f);
        //暂时
        String filePath=f.getAbsolutePath();
        user.setPicture(filePath);

        if(!userService.exisitCount(user)){
//            smallFrame typeFrame=new smallFrame();
//            typeFrame.setPicture(filePath);
            user.setId(userService.selevtID(user));
            if(userService.insert(user)) {
//                    JOptionPane.showMessageDialog(this, "注册成功");
                smallFrame frame=new smallFrame("注册成功");
                String info="ID:"+user.getId()+";用户名:"+user.getName()+";账号:"+user.getCount()
                        +";密码:"+user.getPassword()+";";
                frame.setInfo(info);
                frame.setPicture(filePath);
                frame.setVisible(true);
            }else {
                JOptionPane.showMessageDialog(this, "注册失败");
                return;
            }
//            userService.toUserPanel(manage,user);
        }else {
            JOptionPane.showMessageDialog(this, "注册失败");
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
}
