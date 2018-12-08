package View.ToolComponent;

import Interface.moveAble;
import Util.systemInfo;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 自定义JPanel，实现moveAble接口
 * @Parameter  image  背景
 *             pre，next  为了实现moveAble而定义的前后指针
 * Created by Administrator on 2018/11/21.
 */
public class myPanel extends JPanel implements moveAble {

    private ImageIcon image;
    private myPanel pre=null,next=null;
    private int x=0,y=0,width=systemInfo.mainPaneWid,height=systemInfo.mainPaneHeight;

    public myPanel(String image){
        setLayout(null);
        this.image=new ImageIcon(image);
    }

    public myPanel(URL resource) {
        setLayout(null);
        this.image=new ImageIcon(resource);
    }

    public void setPos(int x,int y,int width,int height){
        this.x=x;this.y=y;this.width=width;this.height=height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image.getImage(),x,y, width,width,null);
    }

    public myPanel getNext() {
        return next;
    }

    public void setNext(myPanel next) {
        this.next = next;
    }

    public myPanel getPre() {
        return pre;
    }

    public void setPre(myPanel pre) {
        this.pre = pre;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }



    @Override
    public boolean pre() {
        return false;
    }

    @Override
    public boolean next() {
        return false;
    }

    @Override
    public boolean addNode(Component c) {
        return false;
    }
}
