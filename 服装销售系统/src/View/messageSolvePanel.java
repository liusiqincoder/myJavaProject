package View;

import Model.ShopKeeper;
import Model.User;
import Model.letter;
import Service.Impl.UserServiceOmpl;
import Service.Impl.goodServiceImpl;
import Service.Impl.letterServiceImpl;
import Service.PanelManage;
import Service.UserService;
import Service.goodService;
import Service.letterService;
import Util.systemInfo;
import View.ToolComponent.messageLabel;
import View.ToolComponent.myFont;
import View.ToolComponent.myPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 用户处理消息的面板
 *
 * @Parameter   catBtn  查看信件内容按钮
 *              addBtn  发邮件按钮
 *              deleteBtn  删除邮件按钮
 *              defyBtn    拒绝信件按钮
 *              cancelDefyBtn  取消拒绝信件按钮
 *
 * Created by Administrator on 2018/11/27.
 */
public class messageSolvePanel extends myPanel implements MouseListener {
    private JButton catBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/查看.png"))),
            addBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/写邮件.png"))),
            deleteBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/删除.png"))),
            defyBtn=new JButton("拒绝"),cancelDefyBtn=new JButton("取消");

    private JScrollPane letterPanel=new JScrollPane();
    private String[] name={"账号","发送时间","信件内容"};
    private tableManager tables=null;
    private List<letter> letters=new ArrayList<>();
    private List<String> users=new ArrayList<>();
    private List<Component> panels=new LinkedList<>();
    private int pos=-1;
    private User owner=null;
//    private PanelManage manage=null;

    public messageSolvePanel(User user) {
//        super(systemInfo.loader.getResource(image));
        super("");
//        this.manage=manage;
        this.owner=user;
        init();
    }

    /*@Override
    public Component add(Component comp) {
        while (panels.size()>pos){
            panels.remove(panels.size()-1);
        }
        panels.add(comp);
        pos++;
        return super.add(comp);
    }*/

    @Override
    public boolean addNode(Component comp) {
        while (panels.size()-1>pos){
            panels.remove(panels.size()-1);
        }
        if(comp instanceof Component){
            if(!panels.isEmpty())
                 remove(panels.get(panels.size()-1));
            panels.add(comp);
            add(comp);
            repaint();
            pos++;
//            System.out.println(pos);
        }
        return true;
    }

    @Override
    public boolean next() {
        if (pos==panels.size()-1){
            return true;
        }
        remove(panels.get(pos));
        pos++;
        add(panels.get(pos));
        repaint();
        return super.next();
    }

    @Override
    public boolean pre() {
        if (pos<1){
            return true;
        }
        remove(panels.get(pos));
        pos--;
        add(panels.get(pos));
        repaint();
        return super.pre();
    }

    private void init(){
        letterPanel=new JScrollPane();
        letterPanel.setBounds(0,2*systemInfo.buttonSize+10,systemInfo.mainPaneWid-20,1000);
        tables=new tableManager(letterPanel);
        tables.setName(name);
        setSize(systemInfo.mainPaneWid,systemInfo.mainPaneHeight);

        addBtn.setBounds(10,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        addBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        addBtn.setFont(myFont.Static);
        addBtn.addMouseListener(this);
        addBtn.setForeground(Color.white);
        addBtn.setToolTipText("写邮件");

        catBtn.setBounds(3*systemInfo.buttonSize+20,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        catBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        catBtn.setFont(myFont.Static);
        catBtn.addMouseListener(this);
        catBtn.setForeground(Color.white);
        catBtn.setToolTipText("查看邮件");

        deleteBtn.setBounds(6*systemInfo.buttonSize+40,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        deleteBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        deleteBtn.setFont(myFont.Static);
        deleteBtn.addMouseListener(this);
        deleteBtn.setForeground(Color.white);
        deleteBtn.setToolTipText("删除邮件");

        defyBtn.setBounds(9*systemInfo.buttonSize+60,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
//        deleteBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        defyBtn.setFont(myFont.Static);
        defyBtn.addMouseListener(this);
        defyBtn.setForeground(Color.BLACK);
        defyBtn.setToolTipText("拒绝某人来信");

        cancelDefyBtn.setBounds(12*systemInfo.buttonSize+80,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        cancelDefyBtn.setFont(myFont.Static);
        cancelDefyBtn.addMouseListener(this);
        cancelDefyBtn.setForeground(Color.BLACK);
        cancelDefyBtn.setToolTipText("取消拒绝某人来信");

        add(addBtn);
        add(catBtn);
        add(deleteBtn);
        add(defyBtn);
        add(cancelDefyBtn);
        addNode(letterPanel);
        flush();
    }

    public void flush(){
        letterService service=new letterServiceImpl();
        letters=service.selectByToId(owner.getId());

        for (letter l:letters){
            users.add(service.selectCountById(l.getFrom_id()));
        }

        String[][] data=new String[letters.size()+100][3];
        for (int i=0;i<letters.size();i++){
            data[i][0]=users.get(i);
            data[i][1]=String.valueOf(letters.get(i).getSend_date());
            data[i][2]=letters.get(i).getMessage();
        }
        tables.setDatas(letters.size(),data);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==deleteBtn){
            delete();
        }else if(e.getSource()==addBtn){
            addLetter();
        }else if(e.getSource()==catBtn){
            catletter();
        }else if(e.getSource()==defyBtn){
            defy();
        }else if(e.getSource()==cancelDefyBtn){
            cancelDefy();
        }
    }

    private void delete(){
        int idx=tables.getSelectrow();
        if(idx<1||idx>letters.size()){
            return;
        }
        if(!tables.deleteLetter(letters.get(idx).getLetter_id())){
            JOptionPane.showMessageDialog(this,"删除失败");
        }else {
            JOptionPane.showMessageDialog(this,"删除成功");
        }
    }

    private void addLetter(){
        sendLetterPanel sendPane=new sendLetterPanel(owner);
        sendPane.setBounds(0,10,systemInfo.mainPaneWid-20,systemInfo.mainPaneHeight);
        addNode(sendPane);
    }

    private void catletter() {
        int idx=tables.getSelectrow();
        if(idx==-1||idx>=letters.size()){
            return;
        }
        String Info="账号:    "+users.get(idx)+";消息:    "+letters.get(idx).getMessage()+";发送时间:  "+letters.get(idx).getSend_date()+";";

//        System.out.println(Info);
        JPanel content=new JPanel();
        content.setBounds(0,10,systemInfo.mainPaneWid-20,systemInfo.mainPaneHeight);
        messageLabel show=new messageLabel();
        show.setText(Info);
        show.setVerticalAlignment(JLabel.CENTER);
        show.setForeground(Color.black);
//        show.setBounds(0,10,systemInfo.mainPaneWid-20,systemInfo.mainPaneHeight);
        addNode(show);
    }

    private void defy(){
        String id=JOptionPane.showInputDialog(this,"请输入拒绝来信的ID：\n","信息输入框",JOptionPane.PLAIN_MESSAGE);
        UserService service=new UserServiceOmpl();
        service.defyUser(owner.getId(),id);
    }

    private void cancelDefy(){
        String id=JOptionPane.showInputDialog(this,"请输入取消拒绝来信的ID：\n","信息输入框",JOptionPane.PLAIN_MESSAGE);
        UserService service=new UserServiceOmpl();
        service.cancelDefy(owner.getId(),id);
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
