package View;

import Model.Good_Sold;
import Model.User;
import Service.Impl.goodServiceImpl;
import Service.goodService;
import Util.systemInfo;
import View.ToolComponent.myFont;
import View.ToolComponent.myPanel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * 销售员查看自己的报表信息
 * @Parameter  soldPanel  报表的放置面板
 *             tableType   类型选项
 *             info        销售信息
 *             info        报表选择框
 *             tables      报表管理，负责显示对应的报表
 *
 * Created by Administrator on 2018/12/5.
 */
public class solderSoldPanel extends myPanel implements ItemListener {

    private JScrollPane soldPanel=new JScrollPane();
    private String[] tableType={"日报表","月报表"};
    private JLabel info=new JLabel("本月销售情况:--");
    private JComboBox type=new JComboBox(tableType);
    private tableManager tables=null;
    private User owner=null;

    public solderSoldPanel(User user) {
        super("");
        this.owner=user;
        setBounds(systemInfo.mainPaneX,systemInfo.mainPaneY,systemInfo.mainPaneWid,systemInfo.mainPaneHeight);
        init();
        repaint();
    }

    private void init(){
        soldPanel=new JScrollPane();
        soldPanel.setBounds(0,4*systemInfo.buttonSize+10,systemInfo.mainPaneWid-20,1000);
        tables=new tableManager(soldPanel);
        setSize(systemInfo.mainPaneWid,systemInfo.mainPaneHeight);

        java.util.List<String> content=new ArrayList<>();

        type.setBounds(12*systemInfo.buttonSize+90,10,5*systemInfo.buttonSize,2*systemInfo.buttonSize);
        type.addItemListener(this);

        info.setBounds(10,2*systemInfo.buttonSize,systemInfo.mainPaneWid,2*systemInfo.buttonSize);

        add(info);
//        add(turnStatus);
        add(type);
        add(soldPanel);
//        selected(;
        repaint();
    }

    private void selected(){
        String t=(String)type.getSelectedItem();
        goodService service=new goodServiceImpl();
        java.util.List<Good_Sold> solds=null;
        int status;
        if(t.equals("日报表")){
            LocalDate date=LocalDate.now();
            solds=service.selectSoldByDay(date);
        }else if(t.equals("月报表")){
            LocalDate date=LocalDate.now();
            solds=service.selectSoldByMon(date);
        }

        //设置售卖信息
        double profit=0;
        for (Good_Sold sold:solds){
            profit+=sold.getNum()*service.selectPrice(sold.getGood_id());
        }
        setInfo(profit,solds.size());

        tables.setContent(solds);
    }

    //设置售卖信息调用的函数
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
