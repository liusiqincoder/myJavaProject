package test;

import DAO.Impl.UserDaoImpl;
import DAO.Impl.goodDaoImpl;
import DAO.UserDao;
import DAO.goodDao;
import Model.*;
import Service.Impl.UserServiceOmpl;
import Service.Impl.goodServiceImpl;
import Service.Impl.letterServiceImpl;
import Service.UserService;
import Service.goodService;
import Service.letterService;
import Util.ojdbcUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by Administrator on 2018/11/23.
 */
public class test {

    Good g=new Good();
    Good_Sold sold=new Good_Sold();
    Good_Stock gs=new Good_Stock();
    letter l=new letter();

    /*@Before
    public void before() throws Exception {

        Connection conn=ojdbcUtil.getConnection();
        Statement stat=conn.createStatement();

//        g.setGood_id("0001");
//        g.setPrice(1);
//        g.setPicture("picture");
//        g.setGood_name("name");
//        g.setProduceDate(LocalDateTime.now());

//        stat.execute("INSERT INTO Good(id,name) VALUES ('goodId','name')");
//        stat.execute("INSERT INTO Solder(id,name,password,count) VALUES ('soldId','1','1','1')");
//        sold.setGood_id("goodId");
//        sold.setSold_id("soldId");
//        sold.setSold_date(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
//        sold.setNum(1);

//        stat.execute("INSERT INTO Good(id,name) VALUES ('goodId','name')");
//        gs.setGood_id("goodId");
//        gs.setNum(1);
        l.setMessage("hello,l am letter");
        l.setTo_id("002");
        l.setFrom_id("001");
    }
    //test ok
    @Test
    public void test1(){
        Manager s=new Manager();
        s.setId("002");
        s.setPicture("asda");
        s.setName("bbb");
        s.setPassword("ok");
        s.setCount("adda");
        UserDao dao=new UserDaoImpl();
        assert dao.insert(s,"Manager")==true;
        assert dao.check("adda","ok","Manager")==true;
        s.setPassword("1");
        assert dao.updatePassWordByCount("adda",s,"Manager")==true;
        assert dao.check("adda","1","Manager")==true;
        Manager s2=new Manager();
        dao.selectByCount("adda","Manager",s2);
        assert s2.getName().equals("bbb");
        assert s2.getCount().equals("adda");
        assert s2.getPassword().equals("1");
        assert s2.getId().equals("002");
        assert s2.getPicture().equals("asda");
    }

    @Test
    public void test2(){
        goodDao dao=new goodDaoImpl();
        dao.addGood(g);
        Good g2=dao.select(g);
        assert g2.getPicture().equals("picture");
        assert g2.getGood_id().equals("0001");
        assert g2.getGood_name().equals("name");
        assert g2.getPrice()-1<0.0000001;
        System.out.println(g2.getProduceDate());
        g.setPrice(2);
        dao.modifyGood(g);
        g2=dao.select(g);
        assert g2.getPicture().equals("picture");
        assert g2.getGood_id().equals("0001");
        assert g2.getGood_name().equals("name");
        assert g2.getPrice()-2<0.0000001;
        System.out.println(g2.getProduceDate());
        dao.deleteGood(g);
        g2=dao.select(g);
        assert g2==null||g2.getGood_id()==null;
    }

    @Test
    public void test3(){
//        goodDao dao=new goodDaoImpl();
//        dao.addGoodSold(sold);
//        Good_Sold sold1=dao.selectBySoldId(sold.getSold_id()),sold2=dao.selectByGoodId(sold.getGood_id());
//        assert sold1.getGood_id().equals(sold.getGood_id())&&sold2.getGood_id().equals(sold.getGood_id());
//        assert sold1.getSold_id().equals(sold.getSold_id())&&sold2.getSold_id().equals(sold.getSold_id());
//        assert sold1.getSold_date().equals(sold.getSold_date())&&sold2.getSold_date().equals(sold.getSold_date());
//        assert sold1.getNum()==sold.getNum()&&sold2.getNum()==sold.getNum();
//        sold.setNum(2);
//        dao.modifyBySoldId(sold);
//        sold1=dao.selectBySoldId(sold.getSold_id());
////        System.out.println(sold1.getNum());
//        assert sold1.getNum()==2;
//        sold1=dao.selectByGoodId(sold.getGood_id());
//        assert sold1.getNum()==2;
//        dao.deleteByGoodId(sold);
//        sold2=dao.selectByGoodId(sold1.getGood_id());
//        assert sold2==null||sold2.getGood_id()==null;
//        dao.addGoodSold(sold1);
//        dao.deleteBySoldId(sold1);
//        sold1=dao.selectBySoldId(sold1.getSold_id());
//        assert sold1==null||sold1.getGood_id()==null;
    }

    @Test
    public void test4(){
        goodDao dao=new goodDaoImpl();
        dao.addGoodNum(gs);
        Good_Stock gs1=dao.selectGoodNum(gs.getGood_id());
        assert gs1.getGood_id().equals(gs.getGood_id());
        assert gs1.getNum()==gs.getNum();
        gs.setNum(2);
        dao.modifyGoodNum(gs);
        gs1=dao.selectGoodNum(gs.getGood_id());
        System.out.println(gs1.getNum());
        assert gs1.getNum()==2;
    }

    @Test
    public void test5(){
        letterService service1=new letterServiceImpl();
        UserService service2=new UserServiceOmpl();
        service1.sendLetter("001","002","hello,l am letter");
        service1.sendLetter("001","002","hello,l am letter");
        service1.sendLetter("001","002","hello,l am letter");
        service1.sendLetter("001","002","hello,l am letter");
        List<letter> ls=service1.getLetter("002");
//        assert ls.size()==4;
        for (int i=0;i<ls.size();i++){
//            System.out.println(ls.get(i).getFrom_id()+":"+ls.get(i).getMessage());
            assert ls.get(i).getFrom_id().equals("001");
            assert ls.get(i).getMessage().equals("hello,l am letter");
        }
        service1.defyUser("001","002");
        service1.defyUser("001","002");
        service1.defyUser("001","002");
        service1.defyUser("001","002");
        List<String> defys=service1.getDefyByToId("002");
//        assert defys.size()==4;
        for (int i=0;i<defys.size();i++){
            assert defys.get(i).equals("001");
        }
    }

    @Test
    public void test6(){
        goodService service=new goodServiceImpl();
        service.addGood("name","picture",1.1,LocalDate.now(),100);
        Good g=service.selectByName("name");
        System.out.println(g.getGood_id());
        assert g.getGood_name().equals("name");
        assert g.getPrice()-1.1<0.001;
        assert g.getPicture().equals("picture");
        Good_Stock gs=service.selectNum(g.getGood_id());
        assert gs.getNum()==100;
//        service.addSold("001",g.getGood_id(),1);
        Good_Sold sold=service.selectSold(g.getGood_id());
//        assert sold.getGood_id().equals(g.getGood_id());
//        System.out.println(sold.getGood_id());
        assert sold.getNum()==1;
        assert sold.getSold_id().equals("001");
//        System.out.println(sold.getSold_date());
    }
*/
//    @Test
    public static void main(String[] args){
        goodDao dao=new goodDaoImpl();
        LocalDate now=LocalDate.now();
        List<Good_Sold> solds=dao.selectSoldByMon(now);
        for (Good_Sold sold:solds){
            System.out.println(sold.getGoodSold_id());
        }
    }

    @After
    public void delete() throws Exception {
        Connection conn= ojdbcUtil.getConnection();
        Statement stat=conn.createStatement();
//        stat.execute("DELETE FROM Solder");
//        stat.execute("DELETE FROM ShopKeeper");
//        stat.execute("DELETE FROM Manager");
//
//        stat.execute("delete from Good");
//
//        stat.execute("DELETE FROM Good_Sold");
//
//        stat.execute("DELETE FROM Good_Stock");
//        stat.execute("DELETE FROM letter");
//        stat.execute("DELETE FROM users");

        conn.commit();
        ojdbcUtil.closse(conn);
    }


}
