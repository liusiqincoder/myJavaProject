package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author:刘思勤
 * @Description:
 *     商品类
 * @parameter
 *      good_id   商品编号
 *      good_name 商品名称
 *      price     商品价格
 *      produceDate 生产日期
 * Created by Administrator on 2018/11/20.
 */
public class Good {
    private String good_id="",good_name="",picture="";
    private double price=0;
    private LocalDate produceDate;

    public LocalDate getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(LocalDate produceDate) {
        this.produceDate = produceDate;
    }

    public String getGood_id() {
        return good_id;
    }

    public void setGood_id(String good_id) {
        this.good_id = good_id;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
