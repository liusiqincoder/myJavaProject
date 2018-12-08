package View.ToolComponent;

import Model.User;
import Util.WindowOpacity;
import Util.systemInfo;
import com.sun.awt.AWTUtilities;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义JFrame
 * @Parameter  status  当前用户职位
 * Created by Administrator on 2018/11/21.
 */
public class myFrame extends JFrame {

    private static int status= User.MANAGER;

    public myFrame(String name){
        super(name);
//        new WindowOpacity(this);
        setLayout(null);
        setBounds(systemInfo.StartX, systemInfo.StartY, systemInfo.FrameWidth, systemInfo.FrameHeight);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(systemInfo.loader.getResource(systemInfo.background)).getImage());
   }


    public static int getStatus() {
        return status;
    }

    public static void setStatus(int statu) {
        status = statu;
    }

}
