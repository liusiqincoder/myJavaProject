package Model;

/**
 * @Author:刘思勤
 * @Description:
 *     库存类
 * @parameter
 *      stock_id  库存编号
 *      good_id  商品编号
 *      num      商品库存
 * Created by Administrator on 2018/11/20.
 */
public class Good_Stock {
    private String stock_id="",good_id="";
    private int num=0;

    public String getStock_id() {
        return stock_id;
    }

    public void setStock_id(String stock_id) {
        this.stock_id = stock_id;
    }

    public String getGood_id() {
        return good_id;
    }

    public void setGood_id(String good_id) {
        this.good_id = good_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
