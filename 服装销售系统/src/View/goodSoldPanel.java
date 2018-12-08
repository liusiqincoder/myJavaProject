package View;

import Model.Good_Sold;
import Model.User;
import Service.Impl.goodServiceImpl;
import Service.PanelManage;
import Service.goodService;
import Util.systemInfo;
import View.ToolComponent.myFindBox;
import View.ToolComponent.myFont;
import View.ToolComponent.myPanel;
import com.sun.org.glassfish.gmbal.ManagedObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description   店长查看日报表，月报表和营业员业绩报表界面
 * @Parameter     addBtn，modifyBtn，deleteBtn 添加，修改，删除按钮
 *                soldPanel   装载报表的容器
 *                tableType    选择报表框的选项
 *                Info         总结销售情况的Label
 *                type          选择报表框
 *                tables        根据用户选择管理显示的报表类型，负责对报表的更新，添加，删除，修改记录
 *                owner          登陆的用户
 * Created by Administrator on 2018/11/27.
 */
public class goodSoldPanel extends myPanel implements MouseListener,ItemListener {
    private JButton addBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/添加.png"))),
            modifyBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/修改.png"))),
            deleteBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/删除.png")));

    private JScrollPane soldPanel=new JScrollPane();
    private String[] tableType={"日报表","月报表","营业员业绩报表"};
    private JLabel info=new JLabel("本月销售情况:--");
    private JComboBox type=new JComboBox(tableType);
    private tableManager tables=null;
    private User owner=null;

    public goodSoldPanel(User owner) {
//        super(systemInfo.loader.getResource(image));
        super("");
        this.owner=owner;
//        this.manage=manage;
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        init();
        repaint();
    }

    private void init(){
        soldPanel=new JScrollPane();
        soldPanel.setBounds(0,4*systemInfo.buttonSize+10,systemInfo.mainPaneWid-20,1000);
        tables=new tableManager(soldPanel);
        setSize(systemInfo.mainPaneWid,systemInfo.mainPaneHeight);

        addBtn.setBounds(10,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        addBtn.setFont(myFont.Static);
        addBtn.addMouseListener(this);
        addBtn.setForeground(Color.white);
        addBtn.setToolTipText("添加商品");

        modifyBtn.setBounds(3*systemInfo.buttonSize+20,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        modifyBtn.setFont(myFont.Static);
        modifyBtn.addMouseListener(this);
        modifyBtn.setForeground(Color.white);
        modifyBtn.setToolTipText("修改商品");

        deleteBtn.setBounds(6*systemInfo.buttonSize+40,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        deleteBtn.setFont(myFont.Static);
        deleteBtn.addMouseListener(this);
        deleteBtn.setForeground(Color.white);
        deleteBtn.setToolTipText("删除商品");
        List<String> content=new ArrayList<>();

        type.setBounds(12*systemInfo.buttonSize+90,10,5*systemInfo.buttonSize,2*systemInfo.buttonSize);
        type.addItemListener(this);

        info.setBounds(10,2*systemInfo.buttonSize,systemInfo.mainPaneWid,2*systemInfo.buttonSize);

        add(addBtn);
        add(modifyBtn);
        add(deleteBtn);
        add(info);
        add(type);
        add(soldPanel);
        repaint();
    }

    /*
    * 添加售卖记录
    * */
    private void add(){

        if(!tables.add()){
            JOptionPane.showMessageDialog(this,"添加失败，请检查列值是否为空");
        }else {
            JOptionPane.showMessageDialog(this,"添加成功");
        }
    }

    /*
    * 修改售卖记录
    * */
    private void modify(){
        if(!tables.modify()){
            JOptionPane.showMessageDialog(this,"修改失败，请检查列值是否为空");
        }else {
            JOptionPane.showMessageDialog(this,"修改成功");
        }
    }

    /*
    * 删除售卖记录
    * */
    private void delete(){

        if(!tables.delete()){
            JOptionPane.showMessageDialog(this,"删除失败");
        }else {
            JOptionPane.showMessageDialog(this,"删除成功");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==addBtn){
            add();
        }else if(e.getSource()==modifyBtn){
            modify();
        }else if(e.getSource()==deleteBtn){
            delete();
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
    * 选择报表框选中报表类型时调用的函数
    * */
    private void selected(){
        String t=(String)type.getSelectedItem();
        goodService service=new goodServiceImpl();
        List<Good_Sold> solds=null;

        //输入查找数据
        if(t.equals("日报表")){
            String day=JOptionPane.showInputDialog(this,"请输入本月的第几天:\n","日期",JOptionPane.PLAIN_MESSAGE);
            int num=Integer.valueOf(day);
            if(num<1||num>LocalDate.now().lengthOfMonth()){
                JOptionPane.showMessageDialog(this,"输入日期不合范围");
                return;
            }
            LocalDate date=LocalDate.now().withDayOfMonth(num);
            solds=service.selectSoldByDay(date);
        }else if(t.equals("月报表")){
            String mon=JOptionPane.showInputDialog(this,"请输入第几个月:\n","日期",JOptionPane.PLAIN_MESSAGE);
            int num=Integer.valueOf(mon);
            if(num<1||num>12){
                JOptionPane.showMessageDialog(this,"输入日期不合范围(1-12)");
                return;
            }
            LocalDate date=LocalDate.now().withMonth(num);
            solds=service.selectSoldByMon(date);
        }else if(t.equals("营业员业绩报表")){
            String id=JOptionPane.showInputDialog(this,"请输入销售员(连续一段)ID:\n","ID",JOptionPane.PLAIN_MESSAGE);
            solds=service.selectSoldBySoldId(id);
        }

        //设置Info
        double profit=0;
        for (Good_Sold sold:solds){
            profit+=sold.getNum()*service.selectPrice(sold.getGood_id());
        }
        setInfo(profit,solds.size());

        //设置数据并刷新面板
        tables.setContent(solds);
    }

    //设置Info内容
    public void setInfo(double profit,int num){
        info.setText("收入:"+(new DecimalFormat("0.00")).format(profit)+"      交易次数:"+num);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==ItemEvent.SELECTED){
            selected();
        }
    }
}
