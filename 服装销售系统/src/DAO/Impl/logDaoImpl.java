package DAO.Impl;

import DAO.logDao;
import Model.User;
import Model.letter;
import Model.system_log;
import Util.ojdbcUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Created by Administrator on 2018/11/26.
 */
public class logDaoImpl implements logDao {
    @Override
    public system_log select(User user) {
        return null;
    }

    @Override
    public boolean add(system_log log) {
        String sql="INSERT INTO system_log(id,job,userId,thing,log_date,status) VALUES (?,?,?,?,?,?)";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        system_log res=new system_log();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,log.getId());
            stat.setString(2,log.getJob());
            stat.setString(3,log.getUserId());
            stat.setString(4, log.getThing());
            stat.setTimestamp(5,Timestamp.valueOf(log.getLog_date()));
            stat.setInt(6,log.getStatus());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
            ojdbcUtil.closse(rs);
        }
        return false;
    }

    @Override
    public boolean modify(system_log log) {
        String sql="UPDATE system_log SET job=?,userId=?,thing=?,log_date=?,status=? WHERE id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,log.getJob());
            stat.setString(2,log.getUserId());
            stat.setString(3,log.getThing());
            stat.setTimestamp(4,Timestamp.valueOf(log.getLog_date()));
            stat.setInt(5,log.getStatus());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
        return false;
    }

    @Override
    public boolean delete(system_log log) {
        String sql="DELETE FROM system_log WHERE id=?";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,log.getId());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
        }
        return false;
    }
}
