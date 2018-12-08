package View;

import Model.Good_Sold;
import Model.Good_Stock;
import Model.User;
import Service.Impl.goodServiceImpl;
import Service.Impl.letterServiceImpl;
import Service.goodService;
import Service.letterService;
import Util.systemInfo;
import View.ToolComponent.myFont;
import View.ToolComponent.myPanel;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

/**
 * 售货员售卖商品界面
 * Created by Administrator on 2018/12/5.
 */
public class addSoldPanel extends myPanel implements FocusListener,MouseListener {
    private JTextField goodIdText=new JTextField("商品ID"),
            numText=new JTextField("商品数量");
    private JButton soldBtn=new JButton("售卖"),
            cancelBtn=new JButton("取消");
    private User user=null;

    public addSoldPanel(User user) {
        super("");
        this.user=user;
        init();
        repaint();
    }

    private void init(){

        goodIdText.setBounds(systemInfo.mainMidX-100,systemInfo.mainMidY-100,200,30);
        goodIdText.addFocusListener(this);
        numText.setBounds(systemInfo.mainMidX-100,systemInfo.mainMidY-60,200,30);
        numText.addFocusListener(this);

        soldBtn.setBounds(systemInfo.mainMidX-2*systemInfo.buttonSize-10,systemInfo.mainMidY-20,3* systemInfo.buttonSize,2*systemInfo.buttonSize);
        soldBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
        soldBtn.setFont(myFont.Static);
        soldBtn.addMouseListener(this);
        soldBtn.setForeground(Color.white);

        cancelBtn.setBounds(systemInfo.mainMidX+systemInfo.buttonSize+10,systemInfo.mainMidY-20,3* systemInfo.buttonSize,2*systemInfo.buttonSize);
        cancelBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
        cancelBtn.setFont(myFont.Static);
        cancelBtn.addMouseListener(this);
        cancelBtn.setForeground(Color.white);

        add(goodIdText);
        add(numText);
        add(soldBtn);
        add(cancelBtn);
    }

    /*
    * 添加商品
    * 库存不足售卖失败
    * 库存足则添加售卖记录
    * */
    private void soldGood(){
        String good_Id=goodIdText.getText(),num=numText.getText();
        if(good_Id.equals("")||good_Id.equals("商品ID")
                ||num.equals("")||num.equals("商品数量")){
            JOptionPane.showMessageDialog(this, "添加失败,必要信息未填写");
            return;
        }
        goodService service=new goodServiceImpl();
        Good_Stock gs=service.selectGoodNumByGoodId(good_Id);
        if(gs.getNum()<Integer.valueOf(num)){
            JOptionPane.showMessageDialog(this,"库存不足");
            return;
        }else {
            gs.setNum(gs.getNum()-Integer.valueOf(num));
            service.nodifyGoodNum(good_Id,gs.getNum());
        }
        if (service.addGoodSold(user.getId(),good_Id,Integer.valueOf(num), LocalDate.now())){
            JOptionPane.showMessageDialog(this, "售卖成功");
        }else {
            JOptionPane.showMessageDialog(this, "售卖失败");
        }
        goodIdText.setText("商品ID");
        numText.setText("商品数量");
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==soldBtn){
            String goodId=goodIdText.getText(),num=numText.getText();
            if(goodId.equals("")||goodId.equals("商品ID")){
                JOptionPane.showMessageDialog(this, "商品ID不能为空");
                return;
            }else if (num.equals("")||num.equals("商品数量")){
                JOptionPane.showMessageDialog(this, "商品数量不能为空");
                return;
            }

            soldGood();
        }else if(e.getSource()==cancelBtn){
            goodIdText.setText("商品ID");
            numText.setText("商品数量");
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

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource()==goodIdText){
            if(goodIdText.getText().equals("商品ID")){
                goodIdText.setText("");
            }
        }else if(e.getSource()==numText){
            if(numText.getText().equals("商品数量")){
                numText.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource()==goodIdText){
            if(goodIdText.getText().equals("")){
                goodIdText.setText("商品ID");
            }
        }else if(e.getSource()==numText){
            if(numText.getText().equals("")){
                numText.setText("商品数量");
            }
        }
    }
}
