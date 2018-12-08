package View.ToolComponent;

import Util.WindowOpacity;
import Util.systemInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 通知栏上的信息
 * @Parameter   message   信息
 *              Color    字体信息
 *              ok        用来重复点击时调用messageFrame的setVisible
 *              messageFrame   显示信息详细
 * Created by Administrator on 2018/11/22.
 */
public class Message extends JLabel implements MouseListener {

    /*
    * 对应职位的颜色
    * */
    public final static Color Manager=Color.RED,
            ShopKepper=Color.darkGray,
            Solder=Color.CYAN;

    private String message;
    private Color c;
    private boolean ok=true;
    private smallFrame messageFrame=null;

    public Message(String message, Color c){
        this.message=message;
        this.c=c;
        setOpaque(false);
        addMouseListener(this);
        setBounds(0,0, systemInfo.messageWidth,systemInfo.messageHeight);
        setForeground(c);
        setText(message);
        messageFrame=new smallFrame();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!messageFrame.isClose()){
//            messageFrame.setClose(false);
            return;
        }
        messageFrame.setClose(false);
        messageFrame.setInfo(message+";");
        messageFrame.setVisible(true);
        new WindowOpacity(messageFrame);
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

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
