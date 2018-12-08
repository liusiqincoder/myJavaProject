package View;

import Interface.flushAble;
import Model.Manager;
import Model.ShopKeeper;
import Model.Solder;
import Model.User;
import Service.Impl.UserServiceOmpl;
import Service.UserService;
import Util.systemInfo;
import View.ToolComponent.descUser;
import View.ToolComponent.myFindBox;
import View.ToolComponent.myFont;
import View.ToolComponent.myPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

/**
 *管理员修改用户信息界面
 * @Parameter   addBtn，modifyBtn，deleteBtn   用户添加删除修改按钮
 *
 *                 需要点击后再点击此按钮生效
 *              descBtn   查看用户具体信息按钮
 *              descPanel   显示用户详细信息的面板
 *
 *              content   数据显示面板
 *              rowNum    显示的数据总行数
 *              name,data  显示的数据
 *
 *              只有两个页面，所以用next，pre表示是否以及后退，前进，实现跳转逻辑
 *
 * Created by Administrator on 2018/11/27.
 */
public class userInfoPanel extends myPanel implements MouseListener, flushAble {


    private JButton addBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/添加.png"))),
            modifyBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/修改.png"))),
            deleteBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/删除.png"))),
            descBtn=new JButton("查看");

    private JTable content=null;
    private int rowNum=0;
    private Object[] name={"用户ID","用户职位","用户账号"};
    private Object[][] data=new Object[100][3];
    private JScrollPane jsp=new JScrollPane();

    private ArrayList<String> items=new ArrayList<>();
    private myFindBox findBox=new myFindBox(this);

    private User owner=null;
    private boolean next=false,pre=false;
    private descUser descPanel=null;

    public userInfoPanel(User owner) {
//        super(systemInfo.loader.getResource(image));
        super("");
        this.owner=owner;
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        init();
        repaint();
    }

    private void init(){
        jsp.setBounds(0,2*systemInfo.buttonSize+10,systemInfo.mainPaneWid,1000);
        add(jsp);
        setSize(systemInfo.mainPaneWid,systemInfo.mainPaneHeight);

        addBtn.setBounds(10,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        addBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        addBtn.setFont(myFont.Static);
        addBtn.addMouseListener(this);
        addBtn.setForeground(Color.white);
        addBtn.setToolTipText("添加用户");

        modifyBtn.setBounds(3*systemInfo.buttonSize+20,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        modifyBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        modifyBtn.setFont(myFont.Static);
        modifyBtn.addMouseListener(this);
        modifyBtn.setForeground(Color.white);
        modifyBtn.setToolTipText("修改用户");

        deleteBtn.setBounds(6*systemInfo.buttonSize+40,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        deleteBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        deleteBtn.setFont(myFont.Static);
        deleteBtn.addMouseListener(this);
        deleteBtn.setForeground(Color.white);
        deleteBtn.setToolTipText("删除用户");

        descBtn.setBounds(9*systemInfo.buttonSize+60,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        deleteBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        descBtn.setFont(myFont.Static);
        descBtn.addMouseListener(this);
        descBtn.setForeground(Color.BLACK);
        descBtn.setToolTipText("查看用户信息");

        java.util.List<String> content=new ArrayList<>();

        findBox.setBounds(12*systemInfo.buttonSize+90,10,5*systemInfo.buttonSize,2*systemInfo.buttonSize);

        flush();

        add(addBtn);
        add(modifyBtn);
        add(deleteBtn);
//        add(turnStatus);
        add(findBox);
        add(descBtn);
        repaint();
    }

    @Override
    public void flushContent(java.util.List<String> match) {
        Object[][] d2 = new Object[match.size() + 100][3];
        int idx = 0;
        for (int i = 0; i < rowNum; i++) {
            if (match.contains(data[i][0])) {
                d2[idx++] = data[i];
            }
        }
        data = d2;
        rowNum=idx;

        flushUser();
    }

    @Override
    public void flushData(){
        UserService service=new UserServiceOmpl();
        rowNum=service.selectAllUsers(data);
        if(rowNum>=data.length){
            data=new Object[2*rowNum][3];
            flushData();
        }
        items=new ArrayList<>();
        for (int i=0;i<rowNum;i++) {
            items.add((String) data[i][0]);
        }
    }

    private void flush(){
        flushData();
        flushUser();
    }

    public void flushUser(){
        if(content!=null)
            jsp.remove(content);
        content=new JTable(data,name);
        content.setFont(myFont.Static);
        content.setForeground(Color.BLACK);
        int col=content.getColumnCount();
        for (int i=0;i<col;i++){
            content.getColumnModel().getColumn(i).setPreferredWidth(100);
        }
        jsp.setViewportView(content);
        jsp.updateUI();
        repaint();
    }

    private void addUser(){
        int row=content.getRowCount();
        UserService service=new UserServiceOmpl();
        boolean ok=true;
        for(;rowNum<row;rowNum++) {
            String job = (String) content.getValueAt(rowNum, 1);
            if(job == null){
                break;
            }
            String count = (String) content.getValueAt(rowNum, 2);
            if (count == null) {
                break;
            }
            ok = service.addUsers(job,count);
        }
        if(!ok){
            JOptionPane.showMessageDialog(this,"添加失败，请检查列值是否为空");
        }else {
            JOptionPane.showMessageDialog(this,"添加成功");
        }
        flush();
    }

    private void modifyUser(){
        int idx=content.getSelectedRow();
        if(idx==-1||idx>=rowNum){
            return;
        }
        UserService service=new UserServiceOmpl();
        String id = (String)data[idx][0];
        String job = (String) content.getValueAt(idx, 1);
        String count = (String) content.getValueAt(idx, 2);
        boolean ok=service.modifyUsersById(id,job,count);
        if(!ok){
            JOptionPane.showMessageDialog(this,"修改失败，请检查列值是否为空");
        }else {
            JOptionPane.showMessageDialog(this,"修改成功");
        }
        flush();
    }

    @Override
    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    @Override
    public boolean next() {
        if(!next&&descPanel!=null){
            next=true;
            pre=false;
            remove(jsp);
            add(descPanel);
            repaint();
            return true;
        }
        return super.next();
    }

    @Override
    public boolean pre() {
        if(!pre&&descPanel!=null){
            next=false;
            pre=true;
            remove(descPanel);
            add(jsp);
            repaint();
            return true;
        }
        return super.pre();
    }

    private void deleteUser(){
        int idx=content.getSelectedRow();
        if(idx==-1||idx>=rowNum){
            return;
        }
        UserService service=new UserServiceOmpl();
        String id = (String)data[idx][0];
        boolean ok=service.deleteUsersById(id);
        if(!ok){
            JOptionPane.showMessageDialog(this,"删除失败");
        }else {
            JOptionPane.showMessageDialog(this,"删除成功");
        }
        flush();
    }

    private void descUser(){
        int idx=content.getSelectedRow();
        if(idx==-1||idx>=rowNum){
            return;
        }

        User user=null;

        if(data[idx][1].equals("Manager")){
            user=new Manager();
        }else if(data[idx][1].equals("ShopKeeper")){
            user=new ShopKeeper();
        }else {
            user=new Solder();
        }

        UserService service=new UserServiceOmpl();
        user.setId((String) data[idx][0]);
        service.selectUserById(user);

        descPanel=new descUser(user);
        descPanel.setBounds(0,2*systemInfo.buttonSize+10,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);

        if(!next){
            next=true;
            pre=false;
            remove(jsp);
            add(descPanel);
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource()==addBtn){
            addUser();
        }else if(e.getSource()==modifyBtn){
            modifyUser();
        }else if(e.getSource()==deleteBtn){
            deleteUser();
        }else if(e.getSource()==descBtn){
            descUser();
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
