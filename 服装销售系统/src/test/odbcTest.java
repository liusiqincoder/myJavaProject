package test;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * Created by Administrator on 2018/11/18.
 */
public class odbcTest {
    public static Connection getConnection(){
        Connection conn=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");//找到oracle驱动器所在的类
            String url="jdbc:oracle:thin:@localhost:1521:liusiqin"; //URL地址
            String username="scott";
            String password="scott";
            conn= DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conn;
    }

    public static void main(String[] args) throws Exception {
        Connection conn=getConnection();
        Statement stat=conn.createStatement();
        ResultSet rs=stat.executeQuery("SELECT * FROM sys.test");
        while (rs.next()){
            String name=rs.getString(1);
            int year=rs.getInt(2);
            System.out.println(name+":"+year);
        }
        conn.close();
    }
}
