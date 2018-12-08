package DAO.Impl;

import DAO.letterDao;
import Model.Good;
import Model.letter;
import Util.ojdbcUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */
public class letterDaoImpl implements letterDao {
    @Override
    public letter selectById(String letter_id) {
        String sql="SELECT letter_id,from_id,to_id,message,to_char(send_date,'YYYY-MM-DD') FROM letter WHERE letter_id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        letter res=new letter();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,letter_id);
            rs=stat.executeQuery();
            if (rs.next()){
                res.setLetter_id(rs.getString(1));
                res.setFrom_id(rs.getString(2));
                res.setTo_id(rs.getString(3));
                res.setMessage(rs.getString(4));
                res.setSend_date(LocalDate.parse(rs.getString(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
            ojdbcUtil.closse(rs);
        }
        return res;
    }

    @Override
    public List<letter> selectByFromId(String from_id) {
        String sql="SELECT letter_id,to_id,message,to_char(send_date,'YYYY-MM-DD') FROM letter WHERE from_id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        List<letter> res=new ArrayList<>();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,from_id);
            rs=stat.executeQuery();
            letter l=new letter();
            while (rs.next()){
                l.setLetter_id(rs.getString(1));
                l.setFrom_id(from_id);
                l.setTo_id(rs.getString(2));
                l.setMessage(rs.getString(3));
                l.setSend_date(LocalDate.parse(rs.getString(4)));
                res.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
            ojdbcUtil.closse(rs);
        }
        return res;
    }

    @Override
    public List<letter> selectByToId(String to_id) {
        String sql="SELECT letter_id,from_id,message,to_char(send_date,'YYYY-MM-DD') FROM letter WHERE to_id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        List<letter> res=new ArrayList<>();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,to_id);
            rs=stat.executeQuery();
            while(rs.next()){
                letter l=new letter();
                l.setLetter_id(rs.getString(1));
                l.setFrom_id(rs.getString(2));
                l.setTo_id(to_id);
                l.setMessage(rs.getString(3));
                l.setSend_date(LocalDate.parse(rs.getString(4)));
                res.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
            ojdbcUtil.closse(rs);
        }
        return res;
    }

    @Override
    public boolean insert(letter l) {
        String sql="INSERT INTO letter(letter_id,from_id,to_id,message,send_date) VALUES (?,?,?,?,?)";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        letter res=new letter();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,l.getLetter_id());
            stat.setString(2,l.getFrom_id());
            stat.setString(3,l.getTo_id());
            stat.setString(4,l.getMessage());
            stat.setDate(5,Date.valueOf(l.getSend_date()));
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            ojdbcUtil.closse(conn);
            ojdbcUtil.closse(rs);
        }
        return true;
    }

    @Override
    public boolean deleteById(String letter_id) {
        String sql="DELETE FROM letter WHERE letter_id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,letter_id);
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
    public boolean deleteByFromId(String fromId) {
        String sql="DELETE FROM letter WHERE from_id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,fromId);
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
    public boolean deleteByToId(String toId) {
        String sql="DELETE FROM letter WHERE to_id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,toId);
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
    public boolean update(letter l) {
        String sql="UPDATE letter SET from_id=?,to_id=?,message=?,send_date=? WHERE letter_id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,l.getFrom_id());
            stat.setString(2,l.getTo_id());
            stat.setString(3,l.getMessage());
            stat.setDate(4,Date.valueOf(l.getSend_date()));
            stat.setString(5,l.getLetter_id());
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

}
