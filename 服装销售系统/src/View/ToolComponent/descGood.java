package View.ToolComponent;

import Model.Good;
import Model.Good_Sold;
import Service.Impl.goodServiceImpl;
import Service.goodService;
import Util.systemInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.net.URL;

/**
 * @Description  查看商品详细信息
 * @Parameter     pictureLabel   显示商品图片
 *                idLabel     显示商品ID
 *                nameLabel    显示商品名
 *                。。。。。
 * Created by Administrator on 2018/12/6.
 */
public class descGood extends myPanel {
    private JLabel pictureLabel=null,idLabel=null,nameLabel=null,
            priceLabel=null,produceDateLabel=null;
    private String id="";
    public descGood(String good_id) {
        super("");
        this.id=good_id;
        init();
    }

    private void init(){
        goodService service=new goodServiceImpl();
        Good g=service.selectById(id);

        String picture= g.getPicture();
        if(picture==null||!(new File(picture)).exists()){
            g.setPicture(systemInfo.Good);
        }

        pictureLabel=new JLabel(new ImageIcon(g.getPicture()),JLabel.CENTER);
        pictureLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY-50,200,100);

        idLabel=new JLabel();
        idLabel.setText("商品ID:    "+g.getGood_id());
        idLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+60,200,30);

        nameLabel=new JLabel();
        nameLabel.setText("商品名称:  "+g.getGood_name());
        nameLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+100,200,30);

        priceLabel=new JLabel();
        priceLabel.setText("商品价格:  "+g.getPrice());
        priceLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+140,200,30);

        produceDateLabel=new JLabel();
        produceDateLabel.setText("商品生产日期:"+g.getProduceDate());
        produceDateLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+180,200,30);
//
//        numLabel=new JLabel();
//        numLabel.setText("库存:      "+sold.getNum());
//        numLabel.setBounds(systemInfo.mainMidX+170,systemInfo.mainPaneY-100,200,30);

        add(pictureLabel);
        add(idLabel);
        add(nameLabel);
        add(priceLabel);
        add(produceDateLabel);
//        add(numLabel);
        repaint();

    }
}
