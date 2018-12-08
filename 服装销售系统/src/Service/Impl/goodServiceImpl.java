package Service.Impl;

import DAO.Impl.goodDaoImpl;
import DAO.UserDao;
import DAO.goodDao;
import Model.Good;
import Model.Good_Sold;
import Model.Good_Stock;
import Service.goodService;
import Util.UUIDUtils;
import Util.systemInfo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */
public class goodServiceImpl implements goodService {
    private goodDao dao=new goodDaoImpl();
    @Override
    public boolean addGood(String name, String picture, double price, LocalDate date, int num) {
        Good g=new Good();
        g.setGood_id(UUIDUtils.getUUID64());
        g.setGood_name(name);
        g.setPrice(price);
        if(picture!=null&&picture!="") {
            g.setPicture(picture);
        }else {
            g.setPicture(systemInfo.Good);
        }
        g.setProduceDate(date);
        dao.addGood(g);
        Good_Stock gs=dao.selectGoodNum(g.getGood_id());
        gs.setGood_id(g.getGood_id());
        gs.setNum(gs.getNum()+num);
        return dao.addGoodNum(gs);
    }

    @Override
    public boolean addSoldAndDate(String sold_id, String good_id, int num,LocalDate date) {
        Good_Sold sold=new Good_Sold();
        sold.setGoodSold_id(UUIDUtils.getId());
        sold.setSold_id(sold_id);
        sold.setGood_id(good_id);
        sold.setSold_date(date);
        sold.setNum(num);
        if (date!=null){
            sold.setSold_date(date);
            return dao.addGoodSoldAndDate(sold);
        }
        return dao.addGoodSold(sold);
    }

    @Override
    public boolean addGoodNum(String good_id,int num) {
        Good_Stock gs=new Good_Stock();
        gs.setGood_id(good_id);
        gs.setStock_id(UUIDUtils.getId());
        gs.setNum(num);
        return dao.addGoodNum(gs);
    }

    @Override
    public boolean exisitInStock(String good_id) {
        return dao.selectGoodNumByGood(good_id)!=0;
    }

    @Override
    public boolean addGoodSold(String sold_id, String good_id, int num, LocalDate sold_date) {
        Good_Sold sold=new Good_Sold();
        sold.setGoodSold_id(UUIDUtils.getId());
        sold.setSold_id(sold_id);
        sold.setGood_id(good_id);
        sold.setNum(num);
        sold.setSold_date(sold_date);
        if(sold_date!=null)
            return dao.addGoodSoldAndDate(sold);
        return dao.addGoodSold(sold);
    }

    @Override
    public boolean delete(String id) {
        Good g=new Good();
        g.setGood_id(id);
        return dao.deleteGood(g);
    }

    @Override
    public boolean deleteSoldById(String id) {
        Good_Sold sold=new Good_Sold();
        sold.setGoodSold_id(id);
        return dao.deleteSoldById(sold);
    }

    @Override
    public boolean deleteStock(String good_id) {
        Good_Stock gs=new Good_Stock();
        gs.setStock_id(good_id);
        return dao.deleteStock(gs);
    }

    @Override
    public Good selectById(String id) {
        Good g=new Good();
        g.setGood_id(id);
        Good res=dao.select(g);
        return res;
    }

    @Override
    public double selectPrice(String id) {
        Good gg=new Good();
        gg.setGood_id(id);
        Good g=dao.select(gg);
        return g.getPrice();
    }

    @Override
    public Good selectByName(String name) {
        return dao.selectByName(name);
    }

    @Override
    public Good_Sold selectSold(String id) {
        return dao.selectSoldById(id);
    }

    @Override
    public List<Good_Sold> selectAllSold() {
        return dao.selectAllSold();
    }

    @Override
    public List<Good_Sold> selectSoldBySoldAndDay(LocalDate date, String sold_id) {
        return dao.selectSoldBySoldAndDay(date,sold_id);
    }

    @Override
    public List<Good_Sold> selectSoldBySoldAndMon(LocalDate date, String sold_id) {
        return dao.selectSoldBySoldAndMon(date,sold_id);
    }

    @Override
    public List<Good_Sold> selectSoldByDay(LocalDate date) {
        return dao.selectSoldByDay(date);
    }

    @Override
    public List<Good_Sold> selectSoldByMon(LocalDate date) {
        return dao.selectSoldByMon(date);
    }

    @Override
    public List<Good_Sold> selectSoldBySoldId(String sold_id) {
        return dao.selectSoldBySoldId(sold_id);
    }

    @Override
    public Good_Stock selectNum(String stock_id) {
        return dao.selectGoodNum(stock_id);
    }

    @Override
    public Good_Stock selectGoodNumByGoodId(String good_id) {
        return dao.selectGoodNumByGoodId(good_id);
    }

    @Override
    public int selectGood(Object[][] gs) {
        List<Good> goods=dao.selectAllGood();
        //"商品ID","商品名","价格","生产日期","图片"
        for (int i=0;i<goods.size();i++){
            gs[i][0]=goods.get(i).getGood_id();
            gs[i][1]=goods.get(i).getGood_name();
            gs[i][2]=String.valueOf(goods.get(i).getPrice());
            gs[i][3]=goods.get(i).getProduceDate().toString();
            gs[i][4]=goods.get(i).getPicture();
        }
        return goods.size();
    }

    @Override
    public int selectGoodAndNum(Object[][] gs) {
        List<Good> ggs=dao.selectAllGood();
        List<Good_Stock> nums2=dao.selectAllStock();
        if(ggs.size()>gs.length){
            return ggs.size();
        }
        for (int i=0;i<ggs.size();i++){
            int num=0;
            for (Good_Stock gst:nums2){
                if(gst.getGood_id().equals(ggs.get(i).getGood_id())){
                    num=gst.getNum();
                    break;
                }
            }
            gs[i][0]=ggs.get(i).getGood_id();
            gs[i][1]=ggs.get(i).getGood_name();
            gs[i][2]=String.valueOf(ggs.get(i).getPrice());
            gs[i][3]=ggs.get(i).getProduceDate().toString();
            gs[i][4]=ggs.get(i).getPicture();
            gs[i][5]=String.valueOf(num);
        }
        return ggs.size();
    }

    @Override
    public boolean nodifyGoodNum(String id,int num) {
        Good_Stock gs=new Good_Stock();
        gs.setGood_id(id);
        gs.setNum(num);
        return dao.modifyGoodNum(gs);
    }

    @Override
    public boolean modify(String id,String name,String picture,LocalDate date,double price) {
        Good g=new Good();
        g.setGood_id(id);
        g.setGood_name(name);
        g.setPicture(picture);
        g.setProduceDate(date);
        g.setPrice(price);
        return dao.modifyGood(g);
    }

    @Override
    public boolean modifyGoodAndNum(String id, String name, String picture, LocalDate date, double price, int num) {
        if(!modify(id,name,picture,date,price)||!nodifyGoodNum(id,num)){
            return false;
        }
        return true;
    }

    @Override
    public boolean modifySold(String goodSold_id,String sold_id, String good_id, int num, LocalDate date) {
        Good_Sold sold=new Good_Sold();
        sold.setGoodSold_id(goodSold_id);
        sold.setSold_id(sold_id);
        sold.setGood_id(good_id);
        sold.setNum(num);
        sold.setSold_date(date);
        return dao.modifyBySoldId(sold);
    }
}
