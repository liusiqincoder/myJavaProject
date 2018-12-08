package DAO;

import Model.Good;
import Model.Good_Sold;
import Model.Good_Stock;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description
 *      有关商品的DAO接口，操作Good表，Good_Sold表，Good_Stock表
 * @Parameter
 * Created by Administrator on 2018/11/26.
 */
public interface goodDao {

    /* @Description  添加商品
              sql="insert into Good(id,price,procedureDate,name,picture) values(?,?,?,?,?)"
    * @Parameter    g  商品
 *  */
    public boolean addGood(Good g);

    /* @Description  商品售卖记录表
              sql="insert into Good_Sold(goodSold_id,sold_id,good_id,num) values(?,?,?,?)"
    * @Parameter     sold  商品售卖对象
 *  */
    public boolean addGoodSold(Good_Sold sold);

    /* @Description    向Good_sold表添加记录（包括sold_date）
              sql="insert into Good_Sold(goodSold_id,sold_id,good_id,num,sold_date) values(?,?,?,?,?)"
    * @Parameter       sold商品售卖对象（sold的date不为null）
 *  */
    public boolean addGoodSoldAndDate(Good_Sold sold);

    /* @Description  商品库存表，增加已存在的商品库存
                     insert into Good_Stock(good_id,num,stock_id) values(?,?,?)
    * @Parameter     gs
    */
    public boolean addGoodNum(Good_Stock gs);

    /* @Description  通过id查询商品
                     sql="SELECT id,name,price,to_char(procedureDate,'YYYY-MM-DD'),picture FROM Good WHERE id=?"
    * @Parameter     g 只有id有用
    */
    public Good select(Good g);

    /* @Description  查询所有商品
                     sql="SELECT id,name,price,to_char(procedureDate,'YYYY-MM-DD'),picture FROM Good"
        * @Parameter
        */
    public List<Good> selectAllGood();

    /* @Description  通过商品名查询商品所有信息
                     sql="SELECT id,name,price,to_char(procedureDate,'YYYY-MM-DD'),picture FROM Good WHERE name =?"
        * @Parameter  name  要查询的商品名
        * @return    Good 商品信息
        */
    public Good selectByName(String name);

    /* @Description  通过good_id查询在Good_Sold上的记录
                     sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold"
        * @Parameter
        * @return    List<Good_Sold>   所有商品销售记录
        */
    public List<Good_Sold> selectAllSold();

    /* @Description  查找与date同一天的所有销售记录
sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_date BETWEEN ? AND ?";        * @Parameter
        * @return    List<Good_Sold>   商品销售记录
        */
    public List<Good_Sold> selectSoldByDay(LocalDate date);

    /* @Description  查找与date同一月的所有销售记录
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_date BETWEEN ? AND ?";
        * @Parameter   date  日期
        * @return    List<Good_Sold>   商品销售记录
        */
    public List<Good_Sold> selectSoldByMon(LocalDate date);

    /* @Description  查询销售员的某一天的销售记录
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_id=? AND (sold_date BETWEEN ? AND ?)";
        * @Parameter   date  日期
        *              sold_id   销售员ID
        * @return    List<Good_Sold>   商品销售记录
        */
    public List<Good_Sold> selectSoldBySoldAndDay(LocalDate date,String sold_id);

    public int selectGoodNumByGood(String good_id);

    /* @Description  查询销售员的某一月的销售记录
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_id=? AND (sold_date BETWEEN ? AND ?)";
        * @Parameter   date  日期
        *              sold_id   销售员ID
        * @return    List<Good_Sold>   商品销售记录
        */
    public List<Good_Sold> selectSoldBySoldAndMon(LocalDate date,String sold_id);

    /* @Description  通过销售员一部分连续ID段查询销售员的所有销售记录
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD'),goodSold_id FROM Good_Sold WHERE sold_id LIKE ?";
        * @Parameter   sold_id   销售员ID
        * @return    List<Good_Sold>   商品销售记录
        */
    public List<Good_Sold> selectSoldBySoldId(String sold_id);

    /* @Description  通过商品ID查询销售记录
        String sql="SELECT sold_id,good_id,num,to_char(sold_date,'YYYY-MM-DD') FROM Good_Sold WHERE goodSold_id=?";
        * @Parameter   goodId   商品ID
        * @return    List<Good_Sold>   商品销售记录
        */
    public Good_Sold selectSoldById(String goodId);

    /* @Description  通过库存编号stock_id查询库存记录
        String sql="SELECT num,good_id FROM Good_Stock WHERE stock_id=?";
        * @Parameter   stock_id   库存编号
        * @return    库存信息
        */
    public Good_Stock selectGoodNum(String stock_id);

    /* @Description  通过商品ID查询库存记录
        String sql="SELECT num,stock_id FROM Good_Stock WHERE good_id=?";
        * @Parameter   good_id   商品ID
        * @return    库存信息
        */
    public Good_Stock selectGoodNumByGoodId(String good_id);

    /* @Description  查询所有库存信息
        String sql="SELECT good_id,num,stock_id FROM Good_Stock";
        * @Parameter
        * @return    所有库存信息
        */
    public List<Good_Stock> selectAllStock();

    /* @Description  通过商品id删除商品
        String sql="DELETE FROM Good WHERE id=?";
        * @Parameter   g  商品
        * @return    是否能删除
        */
    public boolean deleteGood(Good g);

    /* @Description  通过售卖编号sold_id删除Good_Sold表上的记录
        String sql="DELETE FROM Good_Sold WHERE goodSold_id=?";
        * @Parameter   sold  商品售卖类
        * @return    是否能删除
        */
    public boolean deleteSoldById(Good_Sold sold);

    /* @Description  通过售卖编号sold_id删除Good_Sold表上的记录
        String sql="DELETE FROM Good_Stock WHERE stock_id=?";
        * @Parameter   gs  商品库存类
        * @return    是否能删除
        */
    public boolean deleteStock(Good_Stock gs);

    /* @Description  通过商品ID修改商品信息
        String sql="UPDATE Good SET NAME =?,price=?,picture=? WHERE id=?";
        * @Parameter   g  商品类
        * @return    是否能修改
        */
    public boolean modifyGood(Good g);

    /* @Description  通过goodSold_id修改Good_Sold表
        String sql="UPDATE Good_Sold SET good_id =?,num=?,sold_date=? WHERE goodSold_id=?";
        * @Parameter   sold  商品售卖类
        * @return    是否能修改
        */
    public boolean modifyBySoldId(Good_Sold sold);

    /* @Description  通过stock_id修改Good_Stock表
        String sql="UPDATE Good_Stock SET num=? WHERE good_id=?";
        * @Parameter   gs  商品库存类
        * @return    是否能修改
        */
    public boolean modifyGoodNum(Good_Stock gs);
}
