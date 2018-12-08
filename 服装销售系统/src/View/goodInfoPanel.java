package View;

import Interface.flushAble;
import Model.ShopKeeper;
import Model.User;
import Service.Impl.goodServiceImpl;
import Service.goodService;
import Util.systemInfo;
import View.ToolComponent.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

/**修改商品信息面板
 * 店长
 * @Parameter  addBtn，modifyBtn，deleteBtn  添加，修改，删除按钮图片
 *             content   显示商品内容
 *             rowNum    显示的商品行数
 *             name，data 商品列表需要的数据
 *             jsp         content的装载JScrollPanel
 *             items       需要返给搜索框筛选的商品列表的一列值
 *             findBox     搜索框
 *             owner       店长用户
 * Created by Administrator on 2018/11/27.
 */
public class goodInfoPanel extends myPanel implements MouseListener, flushAble {
    private JButton addBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/添加.png"))),
                    modifyBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/修改.png"))),
                    deleteBtn=new JButton(new ImageIcon(systemInfo.loader.getResource("Resource/Picture/删除.png")));

    private JTable content=null;
    private int rowNum=0;
    private Object[] name={"商品ID","商品名","价格","生产日期","图片","数量"};
    private Object[][] data=new Object[100][6];
    private JScrollPane jsp=new JScrollPane();

    private ArrayList<String> items=new ArrayList<>();
    private myFindBox findBox=new myFindBox(this);
//    private PanelManage manage=null;
    private User owner=null;

    public goodInfoPanel(User owner) {
        super("");
//        this.manage=manage;
        this.owner=owner;
        init();
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        repaint();
    }

    @Override
    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    private void init(){
        jsp.setBounds(0,2*systemInfo.buttonSize+10,systemInfo.mainPaneWid,1000);
        add(jsp);
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
//        deleteBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
        deleteBtn.setFont(myFont.Static);
        deleteBtn.addMouseListener(this);
        deleteBtn.setForeground(Color.white);
        deleteBtn.setToolTipText("删除商品");

        List<String> content=new ArrayList<>();

        findBox.setBounds(12*systemInfo.buttonSize+90,10,5*systemInfo.buttonSize,2*systemInfo.buttonSize);

        flush();

        add(addBtn);
        add(modifyBtn);
        add(deleteBtn);
//        add(turnStatus);
        add(findBox);
        repaint();
    }

    /*
    * 刷新商品列表面板
    * */
    @Override
    public void flushContent(List<String> match) {
        Object[][] d2 = new Object[match.size() + 100][6];
        int idx = 0;
        for (int i = 0; i < rowNum; i++) {
            if (match.contains(data[i][0])) {
                d2[idx++] = data[i];
            }
        }
        data = d2;
        rowNum=idx;

        /*for (int i=0;i<rowNum;i++)
            System.out.println(data[i][0]);
        System.out.println();*/

        flushGood();
    }

    /*
    * 刷新data数据
    * */
    @Override
    public void flushData(){
        goodService service=new goodServiceImpl();
        rowNum=service.selectGoodAndNum(data);
        if(rowNum>=data.length){
            data=new Object[2*rowNum][6];
            flushData();
        }
        items=new ArrayList<>();
        for (int i=0;i<rowNum;i++) {
            items.add((String) data[i][0]);
        }
    }

    /*
    * 更新数据并刷新面板
    * */
    private void flush(){
        flushData();
        flushGood();
    }

    /*
    * flushContent调用更新面板
    * */
    public void flushGood(){
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
        jsp.repaint();
    }

    /*
    * 点击addGood按钮调用的添加商品方法
    * row  总行数
    * */
    private void addGood(){
        int row=content.getRowCount();
        goodService  service=new goodServiceImpl();
        boolean ok=true;
        for(;rowNum<row;rowNum++) {
            String name = (String) content.getValueAt(rowNum, 1);
            if(name == null){
                break;
            }
            Double price = Double.valueOf((String) content.getValueAt(rowNum, 2));
            LocalDate date = LocalDate.parse((String) content.getValueAt(rowNum, 3));
            String picture = (String) content.getValueAt(rowNum, 4);
            Integer num = Integer.valueOf((String) content.getValueAt(rowNum, 5));
            if (num == null || price == null
                    || date == null || picture == null) {
                break;
            }
            ok = service.addGood(name, picture, price, date, num);
        }
        if(!ok){
            JOptionPane.showMessageDialog(this,"添加失败，请检查列值是否为空");
        }else {
            JOptionPane.showMessageDialog(this,"添加成功");
        }
        flush();
    }

    /*
    * 点击modifyGood按钮调用的修改商品方法
    * idx  选中的行
    * */
    private void modifyGod(){
        int idx=content.getSelectedRow();
        if(idx==-1||idx>=rowNum){
            return;
        }
        goodService service=new goodServiceImpl();
        String id = (String)data[idx][0];
        String name = (String) content.getValueAt(idx, 1);
        Double price = Double.valueOf((String) content.getValueAt(idx, 2));
        LocalDate date = LocalDate.parse((String) data[idx][3]);
        String picture = (String) content.getValueAt(idx, 4);
        Integer num = (Integer.valueOf((String) content.getValueAt(idx, 5)));

//        System.out.println(owner instanceof ShopKeeper);
//        System.out.println(data[idx][5].equals("0"));
        if((owner instanceof ShopKeeper)&&!service.exisitInStock((String) data[idx][0])){
            service.addGoodNum((String) data[idx][0],num);
        }

        boolean ok=service.modifyGoodAndNum(id,name,picture,date,price,num);
        if(!ok){
            JOptionPane.showMessageDialog(this,"修改失败，请检查列值是否为空");
        }else {
            JOptionPane.showMessageDialog(this,"修改成功");
        }
        flush();
    }

    @Override
    public boolean next() {
        return super.next();
    }

    @Override
    public boolean pre() {
        return super.pre();
    }

    private void deleteGood(){
        int idx=content.getSelectedRow();
        if(idx==-1||idx>=rowNum){
            return;
        }
        goodService service=new goodServiceImpl();
        String id = (String)data[idx][0];
        boolean ok=service.delete(id);
        if(!ok){
            JOptionPane.showMessageDialog(this,"删除失败");
        }else {
            JOptionPane.showMessageDialog(this,"删除成功");
        }
        flush();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(e.getSource()==addBtn){
            addGood();
        }else if(e.getSource()==modifyBtn){
            modifyGod();
        }else if(e.getSource()==deleteBtn){
            deleteGood();
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
