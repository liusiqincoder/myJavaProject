package Util;

import org.apache.log4j.Logger;

import java.awt.*;
import java.io.IOException;

/**
 * @Description   系统配置信息
 * @Parameter   详情看配置文件  systemInfo.properties
 * Created by Administrator on 2018/11/21.
 */
public class systemInfo {

    public static int StartX=300,
                       StartY=300,
                       FrameWidth=500,
                       FrameHeight=500,

                       mainPaneX=0,
                       mainPaneY=80,
                       mainPaneWid=500,
                       mainPaneHeight=420,

                       toolBarX=80,
                       toolBarY=0,
                       toolBarwid=340,
                       toolBarHeight=40,

                       messagePanelX=80,
                       messagePanelY=40,
                       messagePanelWidth=420,
                       messagePanelHeight=40,

                       messageWidth=340,
                       messageHeight=20,
                       buttonSize=20,
                       messageSpeed=5,
                               midX=350,
                               midY=350,
                               smallWidth=200,
                               smallHeight=200,
                               mainMidX=250,
                               mainMidY=230,
                               smallImageX=0,
                               smallImageY=0,
                               smallImageWidth=100,
                               smallImageheight=100;
    public static String backBtn1="Resource/Picture/backBtn1.png",
            backBtn2="/Picture/backBtn2.png",
            backBtn3="/Picture/backBtn3.png",
            preBtn1="Resource/Picture/preBtn1.png",
            preBtn2="/Picture/preBtn2.png",
            preBtn3="/Picture/preBtn3.png",
            background="/Picture/background.png",
            toolbar="Resource/Picture/toolBar.png",
            messagePanel="Resource/Picture/messagePanel.png",
            shopKeeper="Resource/Picture/Shopkeeper/default.png",
            manager="Resource/Picture/Manager/default.png",
            solder="Resource/Picture/Solder/default.png",
            Good="Resource/Picture/Good/default.png",
            GoodSave="Resource/Picture/Good",
            ManagerSave="Resource/Picture/Manager",
            ShopKeeperSave="Resource/Picture/ShopKeeper",
            SolderSave="Resource/Picture/Solder";

    public static Color Manager=Color.red,
                        ShopKeeper=Color.MAGENTA,
    Solder=Color.DARK_GRAY;

    public static ClassLoader loader=Thread.currentThread().getContextClassLoader();

//    public static String Default_SK_Image=
    private final static java.util.Properties props = new java.util.Properties();
    private final static Logger logger = Logger.getLogger(String.valueOf(systemInfo.class));
    static {
        try {
            props.load(systemInfo.class.getResourceAsStream("/systemInfo.properties"));
            StartX=Integer.valueOf(props.getProperty("StartX"));
            StartY=Integer.valueOf(props.getProperty("StartY"));
            FrameWidth=Integer.valueOf(props.getProperty("FrameWidth"));
            FrameHeight=Integer.valueOf(props.getProperty("FrameHeight"));

            mainPaneX=Integer.valueOf(props.getProperty("mainPaneX"));
            mainPaneY=Integer.valueOf(props.getProperty("mainPaneY"));
            mainPaneWid=Integer.valueOf(props.getProperty("mainPaneWid"));
            mainPaneHeight=Integer.valueOf(props.getProperty("mainPaneHeight"));

            toolBarX=Integer.valueOf(props.getProperty("toolBarX"));
            toolBarY=Integer.valueOf(props.getProperty("toolBarY"));
            toolBarwid=Integer.valueOf(props.getProperty("toolBarwid"));
            toolBarHeight=Integer.valueOf(props.getProperty("toolBarHeight"));

            messagePanelX=Integer.valueOf(props.getProperty("messagePanelX"));
            messagePanelY=Integer.valueOf(props.getProperty("messagePanelY"));
            messagePanelWidth=Integer.valueOf(props.getProperty("messagePanelWidth"));
            messagePanelHeight=Integer.valueOf(props.getProperty("messagePanelHeight"));

            messageWidth=Integer.valueOf(props.getProperty("messageWidth"));
            messageHeight=Integer.valueOf(props.getProperty("messageHeight"));

            buttonSize=Integer.valueOf(props.getProperty("buttonSize"));

            messageSpeed=Integer.valueOf(props.getProperty("messageSpeed"));

            mainMidX=Integer.valueOf(props.getProperty("mainMidX"));
            mainMidY=Integer.valueOf(props.getProperty("mainMidY"));

            smallWidth=Integer.valueOf(props.getProperty("smallWidth"));
            smallHeight=Integer.valueOf(props.getProperty("smallHeight"));

            mainMidX=Integer.valueOf(props.getProperty("mainMidX"));
            mainMidY=Integer.valueOf(props.getProperty("mainMidY"));

            smallImageX=Integer.valueOf(props.getProperty("smallImageX"));
            smallImageY=Integer.valueOf(props.getProperty("smallImageY"));
            smallImageWidth=Integer.valueOf(props.getProperty("smallImageWidth"));
            smallImageheight=Integer.valueOf(props.getProperty("smallImageheight"));

            backBtn1=props.getProperty("backBtn1");
            backBtn2=props.getProperty("backBtn2");
            backBtn3=props.getProperty("backBtn3");

            preBtn1=props.getProperty("preBtn1");
            preBtn2=props.getProperty("preBtn2");
            preBtn3=props.getProperty("preBtn3");

            background=props.getProperty("background");

            toolbar=props.getProperty("toolbar");

            messagePanel=props.getProperty("messagePanel");

            shopKeeper=props.getProperty("shopKeeper");
            manager=props.getProperty("manager");
            solder=props.getProperty("solder");
            Good=props.getProperty("Good");
            GoodSave=props.getProperty("GoodSave");
            ManagerSave=props.getProperty("ManagerSave");
            ShopKeeperSave=props.getProperty("ShopKeeperSave");
            SolderSave=props.getProperty("SolderSave");

        } catch (IOException e) {
            logger.error("加载systemInfo.properties配置文件异常",e);
        }
    }

}
