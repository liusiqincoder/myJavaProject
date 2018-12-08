package View.ToolComponent;

import Interface.flushAble;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索框，被观察者，需要注册到medel即观察者里
 * @Parameter  model  观察者
 *             owner  可刷新的Component
 * Created by Administrator on 2018/11/28.
 */
public class myFindBox extends JTextField {
    private comboxModel model=null;
    private myPanel owner=null;

    public myFindBox(myPanel owner) {
        super();
        this.owner=owner;
        setFont(myFont.Static);
        model=new comboxModel(this);
    }

    /*
    * 找出owner的items里的含有str的字符串
    * */
    public List<String> match(String str){
        ArrayList<String> res=new ArrayList<>();
        ArrayList<String> content=((flushAble)owner).getItems();
        for (String s:content){
            if(s.contains(str)){
                res.add(s);
            }
        }
        return res;
    }

    /*
    * 提醒owner刷新面板
    * */
    public void notifyPanel(String s) {
        if(owner instanceof flushAble) {
            ((flushAble) owner).flushContent(match(s));
        }
    }

    public void flush(){
        ((flushAble) owner).flushData();
     }

}
