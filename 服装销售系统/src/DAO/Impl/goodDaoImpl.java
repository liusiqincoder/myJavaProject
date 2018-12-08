package DAO.Impl;

import DAO.goodDao;
import Model.Good;
import Model.Good_Sold;
import Model.Good_Stock;
import Util.ojdbcUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */
public class goodDaoImpl implements goodDao {
    @Override
    public boolean addGood(Good g) {
        String sql="insert into Good(id,price,procedureDate,name,picture) values(?,?,?,?,?)";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,g.getGood_id());
            stat.setDouble(2,g.getPrice());
            stat.setDate(3, Date.valueOf(g.getProduceDate()));
            stat.setString(4,g.getGood_name());
            stat.setString(5,g.getPicture());
            if(stat.executeUpdate()==0){
                System.out.println(0);
                return true;
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
        return false;
    }

    @Override
    public boolean addGoodSold(Good_Sold sold) {
        String sql="insert into Good_Sold(goodSold_id,sold_id,good_id,num) values(?,?,?,?)";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,sold.getGoodSold_id());
            stat.setString(2,sold.getSold_id());
            stat.setString(3,sold.getGood_id());
            stat.setInt(4, sold.getNum());
            stat.executeUpdate();
            conn.commit();
            return true;
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
        return false;
    }

    @Override
    public boolean addGoodSoldAndDate(Good_Sold sold) {
        String sql="insert into Good_Sold(goodSold_id,sold_id,good_id,num,sold_date) values(?,?,?,?,?)";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,sold.getGoodSold_id());
            stat.setString(2,sold.getSold_id());
            stat.setString(3,sold.getGood_id());
            stat.setInt(4, sold.getNum());
            stat.setDate(5,Date.valueOf(sold.getSold_date()));
            stat.executeUpdate();
            conn.commit();
            return true;
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
        return false;
    }

    @Override
    public boolean addGoodNum(Good_Stock gs) {
        String sql="insert into Good_Stock(good_id,num,stock_id) values(?,?,?)";
        Connection conn= ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,gs.getGood_id());
            stat.setInt(2, gs.getNum());
            stat.setString(3,gs.getStock_id());
            if(stat.executeUpdate()==0){
                return false;
            }
            conn.commit();
            return true;
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
        return false;
    }

    @Override
    public Good select(Good g) {
        String sql="SELECT id,name,price,to_char(procedureDate,'YYYY-MM-DD'),picture FROM Good WHERE id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        Good res=new Good();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,g.getGood_id());
            rs=stat.executeQuery();
            if(rs.next()){
                res.setGood_id(rs.getString(1));
                res.setGood_name(rs.getString(2));
                res.setPrice(rs.getDouble(3));
                res.setProduceDate(LocalDate.parse(rs.getString(4)));
                res.setPicture(rs.getString(5));
                return res;
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
    public List<Good> selectAllGood() {
        List<Good> res=new ArrayList<>();
        String sql="SELECT id,name,price,to_char(procedureDate,'YYYY-MM-DD'),picture FROM Good";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            rs=stat.executeQuery();
            while(rs.next()){
                Good g=new Good();
                g.setGood_id(rs.getString(1));
                g.setGood_name(rs.getString(2));
                g.setPrice(rs.getDouble(3));
                g.setProduceDate(LocalDate.parse(rs.getString(4)));
                g.setPicture(rs.getString(5));
                res.add(g);
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
    public Good selectByName(String name) {
        String sql="SELECT id,name,price,to_char(procedureDate,'YYYY-MM-DD'),picture FROM Good WHERE name =?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        Good res=new Good();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,name);
            rs=stat.executeQuery();
            if(rs.next()){
                res.setGood_id(rs.getString(1));
                res.setGood_name(rs.getString(2));
                res.setPrice(rs.getDouble(3));
                res.setProduceDate(LocalDate.parse(rs.getString(4)));
                res.setPicture(rs.getString(5));
                return res;
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
    public List<Good_Sold> selectAllSold() {
        List<Good_Sold> res=new ArrayList<>();
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            rs=stat.executeQuery();
            while(rs.next()){
                Good_Sold gs=new Good_Sold();
                gs.setSold_id(rs.getString(1));
                gs.setGood_id(rs.getString(2));
                gs.setNum(rs.getInt(3));
                gs.setSold_date(LocalDate.parse(rs.getString(4)));
                gs.setGoodSold_id(rs.getString(5));
                res.add(gs);
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
    public List<Good_Sold> selectSoldByDay(LocalDate date) {
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_date BETWEEN ? AND ?";
        List<Good_Sold> res=new ArrayList<>();
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setDate(1,Date.valueOf(date));
            stat.setDate(2,Date.valueOf(date.plusDays(1)));
            rs=stat.executeQuery();
            while(rs.next()){
                Good_Sold gs=new Good_Sold();
                gs.setSold_id(rs.getString(1));
                gs.setGood_id(rs.getString(2));
                gs.setNum(rs.getInt(3));
                gs.setSold_date(LocalDate.parse(rs.getString(4)));
                gs.setGoodSold_id(rs.getString(5));
                res.add(gs);
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
    public List<Good_Sold> selectSoldByMon(LocalDate date) {
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_date BETWEEN ? AND ?";
        List<Good_Sold> res=new ArrayList<>();
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setDate(1,Date.valueOf(date.withDayOfMonth(1)));
            stat.setDate(2,Date.valueOf(date.withDayOfMonth(date.lengthOfMonth())));
            rs=stat.executeQuery();
            while(rs.next()){
                Good_Sold gs=new Good_Sold();
                gs.setSold_id(rs.getString(1));
                gs.setGood_id(rs.getString(2));
                gs.setNum(rs.getInt(3));
                gs.setSold_date(LocalDate.parse(rs.getString(4)));
                gs.setGoodSold_id(rs.getString(5));
                res.add(gs);
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
    public List<Good_Sold> selectSoldBySoldAndDay(LocalDate date,String sold_id) {
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_id=? AND (sold_date BETWEEN ? AND ?)";
        List<Good_Sold> res=new ArrayList<>();
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,sold_id);
            stat.setDate(2,Date.valueOf(date));
            stat.setDate(3,Date.valueOf(date.plusDays(1)));
            rs=stat.executeQuery();
            while(rs.next()){
                Good_Sold gs=new Good_Sold();
                gs.setSold_id(rs.getString(1));
                gs.setGood_id(rs.getString(2));
                gs.setNum(rs.getInt(3));
                gs.setSold_date(LocalDate.parse(rs.getString(4)));
                gs.setGoodSold_id(rs.getString(5));
                res.add(gs);
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
    public int selectGoodNumByGood(String good_id) {
        String sql="SELECT num FROM Good_Stock WHERE good_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,good_id);
            rs=stat.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ojdbcUtil.closse(conn);
            ojdbcUtil.closse(rs);
        }
        return 0;
    }

    @Override
    public List<Good_Sold> selectSoldBySoldAndMon(LocalDate date,String sold_id) {
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_id=? AND (sold_date BETWEEN ? AND ?)";
        List<Good_Sold> res=new ArrayList<>();
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,sold_id);
            stat.setDate(2,Date.valueOf(date.withDayOfMonth(1)));
            stat.setDate(3,Date.valueOf(date.withDayOfMonth(date.lengthOfMonth())));
            rs=stat.executeQuery();
            while(rs.next()){
                Good_Sold gs=new Good_Sold();
                gs.setSold_id(rs.getString(1));
                gs.setGood_id(rs.getString(2));
                gs.setNum(rs.getInt(3));
                gs.setSold_date(LocalDate.parse(rs.getString(4)));
                gs.setGoodSold_id(rs.getString(5));
                res.add(gs);
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
    public List<Good_Sold> selectSoldBySoldId(String sold_id) {
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_id LIKE ?";
        List<Good_Sold> res=new ArrayList<>();
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,"%"+sold_id+"%");
            rs=stat.executeQuery();
            while(rs.next()){
                Good_Sold gs=new Good_Sold();
                gs.setSold_id(rs.getString(1));
                gs.setGood_id(rs.getString(2));
                gs.setNum(rs.getInt(3));
                gs.setSold_date(LocalDate.parse(rs.getString(4)));
                gs.setGoodSold_id(rs.getString(5));
                res.add(gs);
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
    public Good_Sold selectSoldById(String GoodSoldId) {
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD') FROM Good_Sold WHERE goodSold_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        Good_Sold res=new Good_Sold();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,GoodSoldId);
            rs=stat.executeQuery();
            if(rs.next()){
                res.setGoodSold_id(GoodSoldId);
                res.setSold_id(rs.getString(1));
                res.setGood_id(rs.getString(2));
                res.setNum(rs.getInt(3));
                res.setSold_date(LocalDate.parse(rs.getString(4)));
                return res;
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
    public Good_Stock selectGoodNum(String id) {
        String sql="SELECT num,good_id FROM Good_Stock WHERE stock_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        Good_Stock res=new Good_Stock();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,id);
            rs=stat.executeQuery();
            if(rs.next()){
                res.setNum(rs.getInt(1));
                res.setGood_id(rs.getString(2));
                res.setStock_id(id);
                return res;
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
    public Good_Stock selectGoodNumByGoodId(String good_id) {
        String sql="SELECT num,stock_id FROM Good_Stock WHERE good_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;
        Good_Stock res=new Good_Stock();

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,good_id);
            rs=stat.executeQuery();
            if(rs.next()){
                res.setNum(rs.getInt(1));
                res.setStock_id(rs.getString(2));
                res.setGood_id(good_id);
                return res;
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
    public List<Good_Stock> selectAllStock() {
        List<Good_Stock> res=new ArrayList<>();
        String sql="SELECT good_id,num,stock_id FROM Good_Stock";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;
        ResultSet rs=null;

        try {
            stat=conn.prepareStatement(sql);
            rs=stat.executeQuery();
            while(rs.next()){
                Good_Stock gs=new Good_Stock();
                gs.setGood_id(rs.getString(1));
                gs.setNum(rs.getInt(2));
                gs.setStock_id(rs.getString(3));
               res.add(gs);
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
    public boolean deleteGood(Good g) {
        String sql="DELETE FROM Good WHERE id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,g.getGood_id());
            stat.execute();
            conn.commit();
            return true;
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
    public boolean deleteSoldById(Good_Sold sold) {
        String sql="DELETE FROM Good_Sold WHERE goodSold_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,sold.getGoodSold_id());
            stat.execute();
            conn.commit();
            return true;
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
/*

    @Override
    public boolean deleteByGoodId(Good_Sold sold) {
        String sql="DELETE FROM Good_Sold WHERE goodSold_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,sold.getGoodSold_id());
            stat.execute();
            conn.commit();
            return true;
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
*/

    @Override
    public boolean deleteStock(Good_Stock gs) {
        String sql="DELETE FROM Good_Stock WHERE stock_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,gs.getGood_id());
            stat.execute();
            conn.commit();
            return true;
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
    public boolean modifyGood(Good g) {
        String sql="UPDATE Good SET NAME =?,price=?,picture=? WHERE id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,g.getGood_name());
            stat.setDouble(2,g.getPrice());
            stat.setString(3,g.getPicture());
            stat.setString(4,g.getGood_id());
            stat.executeUpdate();
            conn.commit();
            return true;
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
        return false;
    }

    @Override
    public boolean modifyBySoldId(Good_Sold sold) {
        String sql="UPDATE Good_Sold SET good_id =?,num=?,sold_date=? WHERE goodSold_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setString(1,sold.getGood_id());
            stat.setInt(2,sold.getNum());

            //   !!!!sql里的date转为Java的LoalDateTime
            stat.setDate(3,Date.valueOf(sold.getSold_date()));
            stat.setString(4,sold.getGoodSold_id());
            stat.executeUpdate();
            conn.commit();
            return true;
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
        return false;
    }

    @Override
    public boolean modifyGoodNum(Good_Stock gs) {
        String sql="UPDATE Good_Stock SET num=? WHERE good_id=?";
        Connection conn=ojdbcUtil.getConnection();
        PreparedStatement stat=null;

        try {
            stat=conn.prepareStatement(sql);
            stat.setInt(1,gs.getNum());
            stat.setString(2,gs.getGood_id());
            stat.executeUpdate();
            conn.commit();
            return true;
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
        return false;
    }
}
