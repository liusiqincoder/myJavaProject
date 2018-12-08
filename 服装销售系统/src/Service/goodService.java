package Service;

import Model.Good;
import Model.Good_Sold;
import Model.Good_Stock;

import java.time.LocalDate;
import java.util.List;

/**
 * 商品控制层
 * Created by Administrator on 2018/11/26.
 */
public interface goodService {

    /* @Description  添加商品信息和库存数量
                     如果商品图片不存在则设置为默认商品图片
        * @return   能否添加成功
        */
    public boolean addGood(String name, String picture, double price, LocalDate date, int num);

    /* @Description  添加商品售卖信息，如果date为null则设置当前时间
        * @return   如果商品数量不够则返回false
        */
    public boolean addSoldAndDate(String sold_id, String good_id, int num,LocalDate date);

    /* @Description  添加商品库存信息
        * @return   能否添加成功
        */
    public boolean addGoodNum(String good_id,int num);

    /*
    * 查询Good_Stock表里是否有该商品
    * */
    public boolean exisitInStock(String good_id);

    /* @Description  多余的类
        * @return
        */
    public boolean addGoodSold(String sold_id,String good_id,int num,LocalDate sold_date);

    /* @Description  通过ID删除指定商品
        * @return   能否删除成功
        */
    public boolean delete(String id);

    /* @Description  通过售卖编号删除销售记录
        * @return   能否删除成功
        */
    public boolean deleteSoldById(String goodSold_id);

    /* @Description  通过库存编号删除库存记录
    * @return   能否删除成功
    */
    public boolean deleteStock(String stock_id);

    /* @Description  通过商品ID删除商品
    * @return   能否查询到
    */
    public Good selectById(String id);

    /* @Description  通过商品ID查找商品价格
    * @return   商品价格
    */
    public double selectPrice(String id);

    /* @Description  通过商品名查找商品
    * @return   商品类
    */
    public Good selectByName(String name);

    /* @Description  通过售卖编号查找销售记录
    * @return   销售记录
    */
    public Good_Sold selectSold(String goodSold_id);

    /* @Description  查找所有销售记录
    * @return   good_sold表里的记录
    */
    public List<Good_Sold> selectAllSold();

    /* @Description  查找sold_id的销售员在date这一天的所有销售记录
    * @return   销售记录
    */
    public List<Good_Sold> selectSoldBySoldAndDay(LocalDate date,String sold_id);

    /* @Description  查找sold_id的销售员在这一月的所有销售记录
    * @return   销售记录
    */
    public List<Good_Sold> selectSoldBySoldAndMon(LocalDate date,String sold_id);

    /* @Description  查找在date这一天的所有销售记录
    * @return   销售记录
    */
    public List<Good_Sold> selectSoldByDay(LocalDate date);

    /* @Description  查找在date这一月的所有销售记录
    * @return   销售记录
    */
    public List<Good_Sold> selectSoldByMon(LocalDate date);

    /* @Description  查找销售员ID的所有销售记录
    * @return   销售记录
    */
    public List<Good_Sold> selectSoldBySoldId(String sold_id);

    /* @Description  通过库存编号查找库存记录
    * @return   库存记录
    */
    public Good_Stock selectNum(String stock_id);

    /* @Description  通过商品编号查找库存记录
    * @return   库存记录
    */
    public Good_Stock selectGoodNumByGoodId(String good_id);

    /* @Description  通过库存编号查找库存记录
     * @Parameter   gs  用以返回商品信息
     *              gs[i][0]   good_id
     *              gs[i][1]   name
     *              gs[i][2]   price
     *              gs[i][3]   produceDate
     *              gs[i][4]   picture
    * @return
    */
    public int selectGood(Object[][] gs);

    /* @Description  通过所有商品
    * @Parameter 同上
    * @return   查找到的记录数
    */
    public int selectGoodAndNum(Object[][] gs);

    /* @Description  通过id修改商品
    * @return   能否修改成功
    */
    public boolean modify(String id,String name,String picture,LocalDate date,double price);

    /* @Description  修改商品数量
    * @return   能否修改成功
    */
    public boolean nodifyGoodNum(String good_id,int num);

    /* @Description  通过id修改商品信息和数量
    * @return   能否修改成功
    */
    public boolean modifyGoodAndNum(String id,String name,String picture,LocalDate date,double price,int num);

    /* @Description  通过售卖编号修改售卖信息
    * @return   能否修改成功
    */
    public boolean modifySold(String goodSold_id,String sold_id,String good_id,int num,LocalDate date);
}
