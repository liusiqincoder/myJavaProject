package View.ToolComponent;

import Util.systemInfo;

import javax.swing.*;
import java.awt.*;

/**
 * @Description  显示通知栏上的信息
 * @Parameter     maxLen   一行最大的字符数
 * Created by Administrator on 2018/12/6.
 */
public class messageLabel extends JLabel {
    private int maxLen=17;
   public messageLabel(){
        setLayout(null);
        setSize(systemInfo.smallWidth,systemInfo.smallHeight);
       setHorizontalAlignment(JLabel.CENTER);
       setVerticalAlignment(JLabel.TOP);
       setForeground(Color.CYAN);
    }

    public int getMaxLen() {
        return maxLen;
    }

    public void setMaxLen(int maxLen) {
        this.maxLen = maxLen;
    }

    @Override
    public void setText(String text) {
        StringBuffer message=new StringBuffer("<html>");
        String[] arr=text.split(";");
        for(int i=0;i<arr.length;i++){
            if(arr[i].length()>maxLen){
                message.append(arr[i].substring(0,maxLen-1)+"<br/>");
               int start=maxLen;
                while (start+maxLen<arr[i].length()){
                    message.append("  "+arr[i].substring(start,start+maxLen-1)+"<br/>");
                    start+=maxLen;
                }
                if(start<arr[i].length()){
                    message.append(arr[i].substring(start)+"<br/>");
                }
            }else {
                message.append(arr[i]+"<br/>");
            }
        }
        message.append("</html>");
        super.setText(message.toString());
    }
}
