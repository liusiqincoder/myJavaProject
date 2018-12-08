package View;

import Model.User;
import Service.Impl.UserServiceOmpl;
import Service.UserService;
import Util.systemInfo;
import View.ToolComponent.myFont;
import View.ToolComponent.myFrame;
import View.ToolComponent.myPanel;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * 用户修改私人信息的界面
 * @Parameter   user  用户
 *              chooser   选择图片的选择器
 *              f      图片文件
 *              filter  筛选图片的过滤器
 *              pictureLabel  头像Label
 *              idLabel    用户ID  Label
 *              nameText   用户名输入框
 *              countText   账户输入框
 *              passwordText 密码输入框
 *
 *              icon      用户头像
 * Created by Administrator on 2018/12/6.
 */
public class modifyFrame extends JFrame implements FocusListener,MouseListener {
    private User user=null;
    private JFileChooser chooser=new JFileChooser();
    private File f=null;
    private FileNameExtensionFilter filter=new FileNameExtensionFilter("请选择头像","jpg","png","jpeg","bmp");
    private JLabel pictureLabel=null,idLabel=null;
    private JTextField nameText=null,
            countText=null,passwordText=null;
    private JButton modifyBtn=new JButton("确认"),
                    cancelBtn=new JButton("取消"),
                     uploadBtn=new JButton("上传");
    private ImageIcon icon=null;
    
    public modifyFrame(String name,User user) {
        super(name);
        this.user=user;
        setLayout(null);
        setBounds(systemInfo.StartX+25,systemInfo.StartY+50,450,400);
        setVisible(true);
        init();
    }

    private void init(){
        chooser.setFileFilter(filter);
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );

        String picture= start.getUser().getPicture();
        if(picture==null||!(new File(picture).exists())){
            picture=String.valueOf(systemInfo.loader.getResource(systemInfo.solder));
        }
        icon=new ImageIcon(picture);
        pictureLabel=new JLabel(icon,JLabel.CENTER);
        pictureLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY-50,200,100);

        idLabel=new JLabel();
        idLabel.setText("ID:    "+user.getId());
        idLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+60,200,30);
        idLabel.setFont(myFont.Static);

        nameText=new JTextField();
        nameText.setText("名称:  "+user.getName());
        nameText.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+100,200,30);
        nameText.setFont(myFont.Static);
        nameText.addFocusListener(this);

        countText=new JTextField();
        countText.setText("账号:  "+user.getCount());
        countText.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+140,200,30);
        countText.setFont(myFont.Static);
        countText.addFocusListener(this);

        passwordText=new JTextField();
        passwordText.setText("密码:  "+user.getPassword());
        passwordText.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+180,200,30);
        passwordText.setFont(myFont.Static);
        passwordText.addFocusListener(this);

        uploadBtn.setBounds(systemInfo.mainMidX-6*systemInfo.buttonSize,systemInfo.mainPaneY+220,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        uploadBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        uploadBtn.setFont(myFont.Static);
        uploadBtn.addMouseListener(this);
        uploadBtn.setForeground(Color.black);
        uploadBtn.addMouseListener(this);

        modifyBtn.setBounds(systemInfo.mainMidX-2*systemInfo.buttonSize,systemInfo.mainPaneY+220,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        modifyBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        modifyBtn.setFont(myFont.Static);
        modifyBtn.addMouseListener(this);
        modifyBtn.setForeground(Color.black);
        modifyBtn.addMouseListener(this);

        cancelBtn.setBounds(systemInfo.mainMidX+2*systemInfo.buttonSize,systemInfo.mainPaneY+220,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        cancelBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        cancelBtn.setFont(myFont.Static);
        cancelBtn.addMouseListener(this);
        cancelBtn.setForeground(Color.black);
        cancelBtn.addMouseListener(this);

//        panel.setSize(400,300);
        
        add(pictureLabel);
        add(nameText);
        add(countText);
        add(idLabel);
        add(passwordText);

        add(modifyBtn);
        add(cancelBtn);
        add(uploadBtn);
//        panel.repaint();
//        add(panel);
        repaint();
    }

    /*
    * 上传图片
    * */
    private void uploadPicture() {
        chooser.showDialog(this,"选择你的头像上传");
        f=chooser.getSelectedFile();
        icon=new ImageIcon(f.getAbsolutePath());
        pictureLabel.repaint();
    }

    /*
    * 确认修改按钮点击后调用的函数
    * */
    private void modify(){
        //输入框还是提示信息是为空
        //空的视为用户原来信息
        if(!nameText.getText().equals("")&&!nameText.getText().equals("名称:  "+user.getName())){
            user.setName(nameText.getText());
        }

        if(!countText.getText().equals("")&&!countText.getText().equals("账号:  "+user.getCount())){
            user.setCount(countText.getText());
        }

        if(!passwordText.getText().equals("")&&!passwordText.getText().equals("密码:  "+user.getPassword())){
            user.setPassword(passwordText.getText());
        }

        if(f!=null){
            user.setPicture(f.getAbsolutePath());
        }

        UserService service=new UserServiceOmpl();
        if(service.modifyUser(user)){
            JOptionPane.showMessageDialog(this,"修改成功");
        }else {
            JOptionPane.showMessageDialog(this,"修改失败");
        }
    }

    /*
    * 提示信息处理
    * */
    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource()==nameText){
            if(nameText.getText().equals("名称:  "+user.getName())){
                nameText.setText("");
            }
        }else if(e.getSource()==passwordText){
            if(passwordText.getText().equals("密码:  "+user.getPassword())){
                passwordText.setText("");
            }
        }else if(e.getSource()==countText){
            if(countText.getText().equals("账号:  "+user.getCount())){
                countText.setText("");
            }
        }
    }

    /*
    * 提示信息处理
    * */
    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource()==nameText){
            if(nameText.getText().equals("")){
                nameText.setText("名称:  "+user.getName());
            }
        }else if(e.getSource()==passwordText){
            if(passwordText.getText().equals("")){
                passwordText.setText("密码:  "+user.getPassword());
            }
        }else if(e.getSource()==countText){
            if(countText.getText().equals("")){
                countText.setText("账号:  "+user.getCount());
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==modifyBtn){
            modify();
        }else if(e.getSource()==cancelBtn){
            countText.setText("账号:  "+user.getCount());
            passwordText.setText("密码:  "+user.getPassword());
            nameText.setText("名称:  "+user.getName());
        }else if(e.getSource()==uploadBtn){
            uploadPicture();
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
