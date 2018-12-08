package View.ToolComponent;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.List;

/**
 * @Description  可修改JTable的输入观察者，相应提示JTable更新信息
 * @Parameter     owner   搜索框
 *                str     搜索框里的文字
 *                editor    搜索框
 * Created by Administrator on 2018/11/28.
 */
public class comboxModel implements KeyListener {

    private myFindBox owner=null;
    private StringBuffer str=new StringBuffer();
    private JTextField editor = null;

    public comboxModel() {

    }

    public comboxModel(myFindBox owner) {
        this.owner=owner;
        editor= owner;
        editor.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char ch = e.getKeyChar();
        if(ch==KeyEvent.VK_BACK_SPACE){
            return;
        }
        if (ch == KeyEvent.VK_ENTER) {
            owner.notifyPanel(str.toString());
            return;
        }
        str.append(ch);
//        System.out.println(str.toString());
    }

    /*
    * 按下enter后开始搜索
    * */
    @Override
    public void keyReleased(KeyEvent e) {
        char ch = e.getKeyChar();
        if(ch==KeyEvent.VK_BACK_SPACE){
            str=new StringBuffer(editor.getText());
            return;
        }
        if (ch == KeyEvent.VK_ENTER) {
            owner.flush();
        }
    }
}
