package Util;

import java.io.IOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;
/**
 * @Description  一个普通的OJdbc工具类
 *           配置文件为  /ojdbc.properties
 *
 * @Parameter   props  加载配置文件的properties
 *              Logger   日志
 * Created by Administrator on 2018/11/21.
 */
public class ojdbcUtil {
    public static String url=null;
    private static String username = null;
    private static String password = null;
    private static String driver = null;
    private final static Properties props = new Properties();
    private final static Logger logger = Logger.getLogger(String.valueOf(ojdbcUtil.class));

    static {

        try {
            // 读取数据库配置文件
            props.load(ojdbcUtil.class.getResourceAsStream("/ojdbc.properties"));
        } catch (IOException e) {
            logger.error("加载ojdbc.properties配置文件异常", e);
        }

        url = (props.getProperty("ojdbc.url"));
        username = (props.getProperty("ojdbc.username"));
        password = (props.getProperty("ojdbc.password"));
        driver = (props.getProperty("ojdbc.driver"));

        // 加载数据库驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("加载数据库驱动异常", e);
        }

    }

    /**
     * 创建一个数据库连接
     *
     * @return 一个数据库连接
     *
     */
    public static Connection getConnection() {
        Connection conn = null;
        // 创建数据库连接
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            logger.error("创建数据库连接发生异常", e);
        }
        return conn;
    }

    public static void closse(Object o){
        if(o==null){
            return;
        }

        try {
            if(o instanceof ResultSet) {
                ((ResultSet) o).close();
            }else if(o instanceof Statement){
                ((Statement)o).close();
            }else if (o instanceof Connection){
                Connection c=(Connection)o;
                if(!c.isClosed()){
                    c.commit();
                    c.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (o instanceof Connection){
                try {
                    ((Connection)o).rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
