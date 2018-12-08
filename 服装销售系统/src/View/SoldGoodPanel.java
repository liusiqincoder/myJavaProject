package View;

import Interface.flushAble;
import Model.User;
import Service.Impl.goodServiceImpl;
import Service.goodService;
import Util.systemInfo;
import View.ToolComponent.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 销售员查看商品信息，销售商品以及浏览商品详细信息界面
 * @Parameter   items  flushAble需要的List（需要以这一列为搜索内容）
 *              findBox  搜索框
 *              content   商品信息展示容器
 *              rowNum   商品信息行数
 *              pos      选中某一行的索引
 *              name,data   商品信息数据
 *              jsp       装载JTable的面板
 * Created by Administrator on 2018/12/5.
 */
public class SoldGoodPanel extends myPanel implements flushAble,MouseListener {
    private JButton soldBtn=new JButton("售卖"),
            descBtn=new JButton("浏览");

    private ArrayList<String> items=new ArrayList<>();
    private myFindBox findBox=new myFindBox(this);
    private User user=null;

    private JTable content=null;
    private int rowNum=0,pos=-1;
    private Object[] name={"商品ID","商品名","价格","生产日期","图片"};
    private Object[][] data=new Object[100][5];
    private JScrollPane jsp=new JScrollPane();

    private List<Component> panels=new LinkedList<>();

    public SoldGoodPanel(User user) {
        super("");
        this.user=user;
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        init();
        repaint();
    }

    private void init(){
        soldBtn.setBounds(10,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        soldBtn.setFont(myFont.Static);
        soldBtn.addMouseListener(this);
        
        descBtn.setBounds(3*systemInfo.buttonSize+20,10,3*systemInfo.buttonSize,2*systemInfo.buttonSize);
        descBtn.setFont(myFont.Static);
        descBtn.addMouseListener(this);

        findBox.setBounds(12*systemInfo.buttonSize+90,10,5*systemInfo.buttonSize,2*systemInfo.buttonSize);

        jsp.setBounds(0,2*systemInfo.buttonSize+10,systemInfo.mainPaneWid,1000);

        flush();

        add(jsp);
        add(soldBtn);
        add(descBtn);
        add(findBox);
        addNode(jsp);
//        repaint();
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

/*   *//* @Override
    public Component add(Component comp) {
        addNode(comp);
        return super.add(comp);
    }

    @Override
    public void remove(Component comp) {
        pos--;
        super.remove(comp);
    }
*/
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
    public void flushContent(List<String> match) {
        Object[][] d2 = new Object[match.size() + 100][5];
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

    @Override
    public void flushData(){
        goodService service=new goodServiceImpl();
        rowNum=service.selectGood(data);
        if(rowNum>=data.length){
            data=new Object[2*rowNum][5];
            flushData();
        }
        items=new ArrayList<>();
        for (int i=0;i<rowNum;i++) {
            items.add((String) data[i][0]);
        }
    }

    private void flush(){
        flushData();
        flushGood();
    }

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

    @Override
    public ArrayList<String> getItems() {
        return items;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==soldBtn){
            sold();
        }else if(e.getSource()==descBtn){
            desc();
        }
    }

    private void sold(){
//        remove(jsp);
//        addNode(jsp);
        addSoldPanel addSoldPanel=new addSoldPanel(user);
        addSoldPanel.setBounds(0,2*systemInfo.buttonSize+10,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        addNode(addSoldPanel);
    }

    private void desc() {
        int idx=content.getSelectedRow();
        if(idx<0||idx>=rowNum){
            return;
        }
        descGood descPanel=new descGood((String) data[idx][0]);
        descPanel.setBounds(0,2*systemInfo.buttonSize+10,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        addNode(descPanel);
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
