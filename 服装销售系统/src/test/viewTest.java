package test;

import View.ShopKepperPanel;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.junit.Test;

import javax.swing.*;

/**
 * Created by Administrator on 2018/11/27.
 */
public class viewTest {
    @Test
    public void test1(){
        try {
            // 设置窗口边框样式
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);
//            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JFrame frame=new JFrame();
        frame.setBounds(200,200,500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new ShopKepperPanel());
    }
}
