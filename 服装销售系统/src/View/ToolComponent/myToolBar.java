package View.ToolComponent;

import Model.User;
import Util.systemInfo;
import javax.swing.*;
import java.awt.*;

/**
 * 工具栏
 * Created by Administrator on 2018/11/22.
 */
public class myToolBar extends JToolBar {

    private ImageIcon image;
    private boolean login=false;
    private  static backBtn bbtn=null;
    private  static preBtn pbtn=null;
    private final static RorLButton rlBtn=new RorLButton();
    private static InfoLabel person=null;
    private User user=null;
//    private final static

    public myToolBar(){
        bbtn=new backBtn();
        pbtn=new preBtn();
        person=new InfoLabel();
        rlBtn.setForeground(Color.black);
        setBounds(systemInfo.toolBarX,systemInfo.toolBarY,systemInfo.toolBarwid,systemInfo.toolBarHeight);

        this.image=new ImageIcon(systemInfo.loader.getResource(systemInfo.toolbar));

        add(bbtn);
        add(pbtn);
        add(rlBtn);
        add(person);
        repaint();
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        if(login){
            rlBtn.login(user);
        }else {
            rlBtn.logout();
        }
        repaint();
        this.login = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
