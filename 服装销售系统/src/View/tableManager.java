package View;

import Model.Good_Sold;
import Service.Impl.goodServiceImpl;
import Service.Impl.letterServiceImpl;
import Service.goodService;
import Service.letterService;
import View.ToolComponent.myFont;
import View.ToolComponent.myPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * 各种销售报表的管理和显示，操作等
 * @Parameter  table   数据显示容器
 *             row     信息行数
 *             name，solds，datas   数据
 *             owner    装载table的容器
 *
 * Created by Administrator on 2018/11/29.
 */
public class tableManager {
//    public final static int DAY=1,MONTH=2,SOLDERPRO=3;

    private JTable table=null;
    private int row=0;
    private Object[] name={"售卖编号","销售员ID","商品ID","销售数量","销售时间"};
    private List<Good_Sold> solds=null;
    private JScrollPane owner=null;
    private Object[][] datas=null;

    public tableManager(JScrollPane owner) {
        this.owner = owner;
    }

    public tableManager(JScrollPane owner, List<Good_Sold> solds) {
        this.solds=solds;
        this.owner=owner;
    }

    //设置数据并刷新owner的面板
    public void setContent(List<Good_Sold> solds){
        this.solds=solds;
        this.row=solds.size();
        flush();
    }

    //刷新数据
    private void flush(){
        datas=new Object[solds.size()+100][5];
        for (int i=0;i<solds.size();i++){
            datas[i][0]=solds.get(i).getGoodSold_id();
            datas[i][1]=solds.get(i).getSold_id();
            datas[i][2]=solds.get(i).getGood_id();
            datas[i][3]=solds.get(i).getNum();
            datas[i][4]=solds.get(i).getSold_date();
        }
        flash();
    }

    //刷新owner面板
    public void flash(){
        table=new JTable(datas,name);
        table.setFont(myFont.Static);
        table.setForeground(Color.BLACK);
        owner.setViewportView(table);
        owner.repaint();
    }

    public Object[][] getDatas() {
        return datas;
    }

    //设置数据并刷新owner面板
    public void setDatas(int row,Object[][] datas) {
        this.row=row;
        this.datas = datas;
        flash();
    }

    public int getSelectrow(){
        return table.getSelectedRow();
    }

    /*
    * 删除信件
    * */
    public boolean deleteLetter(String letter_id){
        int rowNum=table.getSelectedRow();
        if(rowNum<0||rowNum>=row){
            return false;
        }
        boolean ok=true;
        letterService service=new letterServiceImpl();
        ok=service.deleteLetter(letter_id);
        for (int i=rowNum;i<row;i++){
            datas[i]=datas[i+1];
        }
        flash();
        return ok;
    }

    /*
    * 添加数据
    * */
    public boolean add(){
        int rowNum=table.getRowCount();
        goodService service=new goodServiceImpl();
        boolean ok=true;
        for(int i=solds.size();i<rowNum;i++) {
            String sold_id = (String) table.getValueAt(i, 1);
            if(sold_id == null){
                break;
            }
            String good_id = (String) table.getValueAt(i, 2);
            Integer num = Integer.valueOf((String) table.getValueAt(i, 3));
            LocalDate date=LocalDate.parse((String) table.getValueAt(i, 4));
            Good_Sold sold=new Good_Sold();
            sold.setSold_id(sold_id);
            sold.setGood_id(good_id);
            sold.setSold_date(date);
            sold.setNum(num);
            solds.add(sold);
            if (num == null || good_id == null) {
                break;
            }
            ok = service.addSoldAndDate(sold_id,good_id,num,date);
        }
        flush();
        return ok;
    }
    
    public boolean delete(){
        int rowNum=table.getSelectedRow();
        if(rowNum<0||rowNum>=solds.size()){
            return false;
        }
        boolean ok=true;
        goodService service=new goodServiceImpl();
        ok=service.deleteSoldById(solds.get(rowNum).getGoodSold_id());
        solds.remove(rowNum);
        flush();
        return ok;
    }
    public boolean modify(){
        int idx=table.getSelectedRow();
        if(idx==-1||idx>=solds.size()){
            return false;
        }
        goodService service=new goodServiceImpl();
        String goodSold_id = solds.get(idx).getGoodSold_id();
        String sold_id=(String) table.getValueAt(idx, 1);
        String good_id = (String) table.getValueAt(idx, 2);
        Integer num = Integer.valueOf((String) table.getValueAt(idx, 3));
        LocalDate date = (LocalDate) table.getValueAt(idx, 4);
        boolean ok=service.modifySold(goodSold_id,sold_id,good_id,num,date);
        solds.remove(idx);
        flush();
        return ok;
    }

    public List<Good_Sold> getSolds() {
        return solds;
    }

    public void setSolds(List<Good_Sold> solds) {
        this.solds = solds;
    }

    public Object[] getName() {
        return name;
    }

    public void setName(Object[] name) {
        this.name = name;
    }

}
