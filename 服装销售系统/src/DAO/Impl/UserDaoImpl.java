package DAO.Impl;

import DAO.UserDao;
import Model.Manager;
import Model.ShopKeeper;
import Model.User;
import Util.ojdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/23.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public boolean check(String userName, String password,String table) {
        Connection conn= ojdbcUtil.getConnection();
        String sql="select password from "+table+" where count=?";
        PreparedStatement stat=null;
        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,userName);
            ResultSet rs=stat.executeQuery();
            if(!rs.next()){
                return false;
            }
            String p=rs.getString(1);
            if(p.equals(password)){
                return true;
            }
        } catch (SQLException e) {
            return false;
        }finally {
            ojdbcUtil.closse(conn);
        }
        return false;
    }

    @Override
    public void selectByCount(String count,String table,User sk) {
        String sql="select* from "+table+" where count='"+count+"'";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        try {
            stat=conn.prepareStatement(sql);
            rs=stat.executeQuery();

            if(rs.next()){
                sk.setId(rs.getString(1));
                sk.setCount(rs.getString(2));
                sk.setPassword(rs.getString(3));
                sk.setName(rs.getString(4));
                sk.setPicture(rs.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
    }

    @Override
    public void selectUserById(User user) {
        String table;
        if(user instanceof Manager){
            table="Manager";
        }else if(user instanceof ShopKeeper){
            table="ShopKeeper";
        }else {
            table="Solder";
        }
        String sql="select* from "+table+" where id='"+user.getId()+"'";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        try {
            stat=conn.prepareStatement(sql);
            rs=stat.executeQuery();

            if(rs.next()){
//                user.setId(rs.getString(1));
                user.setCount(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setName(rs.getString(4));
                user.setPicture(rs.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }

    }

    @Override
    public String selectCountById(String id) {
        String sql="SELECT count FROM users WHERE id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        User user=new User();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,id);
            rs=stat.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
            ojdbcUtil.closse(rs);
        }
        return "";
    }

    @Override
    public boolean insert(User user,String table) {
        String sql="insert into "+table+"(id,password,count,name,picture) values(?,?,?,?,?)";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,user.getId());
            stat.setString(2,user.getPassword());
            stat.setString(3,user.getCount());
            stat.setString(4,user.getName());
            stat.setString(5,user.getPicture());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    e1.printStackTrace();
                }
            }
            return false;
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public boolean updatePassWordByCount(String count, User user,String table) {
        String sql="UPDATE "+table+" SET password=? WHERE count=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,user.getPassword());
            stat.setString(2,user.getCount());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public String selectID(String table, User user) {
        String sql="SELECT id FROM "+table+" WHERE count=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,user.getCount());
            rs=stat.executeQuery();
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
            ojdbcUtil.closse(rs);
        }
        return "";
    }

    @Override
    public boolean modifyUser(String table, User user) {
        String sql="UPDATE "+table+" SET count=?,password=?,name=?,picture=? WHERE id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,user.getCount());
            stat.setString(2,user.getPassword());
            stat.setString(3,user.getName());
            stat.setString(4,user.getPicture());
            stat.setString(5,user.getId());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public boolean updateDefyId(String id, String defyId) {
        String sql="UPDATE user_defy SET defy_id=? WHERE user_id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,id);
            stat.setString(2,defyId);
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public List<String> selectDefy(String id) {
        String sql="select defy_id from user_defy where user_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        List<String> res=new ArrayList<>();
        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,id);
            rs=stat.executeQuery();

            while(rs.next()){
                res.add(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
        return res;
    }

    @Override
    public boolean deleteDefy(String id, String defyId) {
        String sql="DELETE FROM user_defy where user_id=? AND defy_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,id);
            stat.setString(2,defyId);
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
        return false;
    }

    @Override
    public boolean modifydefy(String id, String defyId) {
        String sql="UPDATE user_defy SET defy_id=? WHERE user_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,defyId);
            stat.setString(2,id);
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public boolean insert(String userDefyId,String id, String defy_id) {
        String sql="insert into user_defy(userDefy_id,user_id,defy_id) values(?,?,?)";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,userDefyId);
            stat.setString(2,id);
            stat.setString(3,defy_id);
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    e1.printStackTrace();
                }
            }
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public boolean insertUsers(User user) {
        String sql="insert into users(id,job,count) values(?,?,?)";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
//            stat.setString(1,user.getId());
            stat.setString(1,user.getId());
            stat.setString(2,user.getJob());
            stat.setString(3,user.getCount());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    e1.printStackTrace();
                }
            }
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public boolean modifyUsers(User user) {
        String sql="UPDATE users SET job=?,count=? WHERE id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,user.getJob());
            stat.setString(2,user.getCount());
            stat.setString(3,user.getId());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public User selectUser(String id) {
        String sql="select count,job from users where id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        User user=new User();
        try {
            stat=conn.prepareStatement(sql);
            rs=stat.executeQuery();

            if(rs.next()){
                user.setId(id);
                user.setCount(rs.getString(1));
                user.setJob(rs.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
        return user;
    }

    @Override
    public boolean updateUsers(User user) {
        String sql="UPDATE users SET count=?,job=? WHERE id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,user.getCount());
            stat.setString(2,user.getJob());
            stat.setString(3,user.getId());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public boolean deleteUsers(String id) {
        String sql="DELETE FROM users where id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,id);
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }finally {
            ojdbcUtil.closse(conn);
        }
        return true;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> res=new ArrayList<>();
        String sql="select id,count,job from users";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        try {
            stat=conn.prepareStatement(sql);
            rs=stat.executeQuery();

            while(rs.next()){
                User user=new User();
                user.setId(rs.getString(1));
                user.setCount(rs.getString(2));
                user.setJob(rs.getString(3));
                res.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
        return res;
    }
}
