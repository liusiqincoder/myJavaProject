package View.ToolComponent;

import Model.Good;
import Model.ShopKeeper;
import Model.Solder;
import Model.User;
import Service.Impl.UserServiceOmpl;
import Service.Impl.goodServiceImpl;
import Service.UserService;
import Service.goodService;
import Util.systemInfo;

import javax.swing.*;
import java.io.File;
import java.net.URL;

/**
 * @Description  查看用户详细信息
 * @Parameter     pictureLabel   显示用户图片
 *                idLabel     显示用户ID
 *                nameLabel    显示用户名
 *                。。。。。
 * Created by Administrator on 2018/12/6.
 */
public class descUser extends myPanel {
   

    private JLabel pictureLabel=null,idLabel=null,nameLabel=null,
            countLabel=null;
    private User user=null;
    
    public descUser(User user) {
        super("");
        this.user=user;
        init();
    }
    
    private void init(){

//        System.out.println(systemInfo.loader.getResource(user.getPicture()));
        String picture=user.getPicture();
        if(picture==null||!(new File(picture)).exists()){
            if(user instanceof ShopKeeper)
                user.setPicture(systemInfo.shopKeeper);
            else if(user instanceof Solder)
                user.setPicture(systemInfo.solder);
            else 
                user.setPicture(systemInfo.manager);
        }

        pictureLabel=new JLabel(new ImageIcon(user.getPicture()),JLabel.CENTER);
        pictureLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY-50,200,100);

        idLabel=new JLabel();
        idLabel.setText("用户ID:      "+user.getId());
        idLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+60,200,30);

        nameLabel=new JLabel();
        nameLabel.setText("用户名称:    "+user.getName());
        nameLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+100,200,30);

        countLabel=new JLabel();
        countLabel.setText("用户账号:    "+user.getCount());
        countLabel.setBounds(systemInfo.mainMidX-100,systemInfo.mainPaneY+140,200,30);

        add(pictureLabel);
        add(idLabel);
        add(nameLabel);
        add(countLabel);
//        add(numLabel);
        repaint();

    }
    
}
