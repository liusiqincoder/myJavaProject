package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author:刘思勤
 * @Description:
 *     销售类
 * @parameter
 *      goodSold_id   订单编号
 *      sold_id 销售员ID
 *      good_id  商品编号
 *      num      购买的商品数量
 *      sold_date 售卖时间
 * Created by Administrator on 2018/11/20.
 */
public class Good_Sold {
    private String goodSold_id="",sold_id="",good_id="";
    private int num=0;
    private LocalDate sold_date;

    public String getGoodSold_id() {
        return goodSold_id;
    }

    public void setGoodSold_id(String goodSold_id) {
        this.goodSold_id = goodSold_id;
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

    public LocalDate getSold_date() {
        return sold_date;
    }

    public void setSold_date(LocalDate sold_date) {
        this.sold_date = sold_date;
    }

    public String getSold_id() {
        return sold_id;
    }

    public void setSold_id(String sold_id) {
        this.sold_id = sold_id;
    }
}
