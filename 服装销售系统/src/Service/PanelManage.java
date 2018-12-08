package Service;

import Model.User;
import Util.systemInfo;
import View.ToolComponent.myFrame;
import View.ToolComponent.myPanel;
import View.start;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description   负责界面的前后跳转逻辑
 *        创建双向链表实现，mypanel就是结点
 *
 * @Parameter    head  头节点
 *               now   当前界面所对应的节点
 *               p      尾节点
 *               frame  start里创建的myFrame
 *
 * Created by Administrator on 2018/11/22.
 */
public class PanelManage {
    private static myPanel head=new myPanel(""),now=head,p=head;
    private static myFrame frame=null;

    public PanelManage() {

    }


    public PanelManage(myFrame frame) {
        this.frame = frame;
    }

    public static myFrame getFrame() {
        return frame;
    }

    public static void setFrame(myFrame frame) {
        PanelManage.frame = frame;
    }

    /* @Description  添加尾结点
    * @return
    */
    public static void add(myPanel node){
        p.setNext(node);
        node.setPre(p);
        p=node;
    }

    /* @Description  清除所有结点
               由于frame要清除Panel后再添加Panel
    * @return
    */
    public static void clear(){
        now=p=head;
        frame.setVisible(false);
        frame=new myFrame("服装销售系统");
        start.setFrame(frame);
        head.setNext(null);
         now=p=head;
         frame.repaint();
      }

    /* @Description  当前界面的下一个界面
                      如果没有下一个界面则停留再当前界面
    * @return   下一界面myPanel
    */
    public static myPanel next(){
        if (now==null||frame==null||now!=head&&!now.next()){
            return null;
        }

        if(now.getNext()!=null)
            now=now.getNext();
        else
            return null;

        frame.setVisible(false);
        frame=new myFrame("服装销售系统");
        start.setFrame(frame);
//        if(now!=head)
//            frame.remove(now);
        frame.add(now);
        frame.add(start.toolBar);
        frame.add(start.messagePanel);
        frame.repaint();
        return now;
    }

    /* @Description  当前界面的上一个界面
                      如果没有上一个界面则停留再当前界面
    * @return   上一界面myPanel
    */
    public static myPanel pre(){
        if (now==null||frame==null||now!=head&&!now.pre()){
            return null;
        }

        if(now.getPre()!=null&&now.getPre()!=head)
            now=now.getPre();
        else
            return null;

        frame.setVisible(false);
        frame=new myFrame("服装销售系统");
        /*if(now.getPre()!=null){
            now=now.getPre();
        }*/
        frame.add(now);
        frame.add(start.toolBar);
        frame.add(start.messagePanel);
        return now;
    }

}
