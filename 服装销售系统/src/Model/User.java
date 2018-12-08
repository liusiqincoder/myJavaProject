package Model;

/**
 * @Author:刘思勤
 * @Description:
 *     用户类
 * @parameter
 *     id     用户ID
 *     count   账户
 *     name    用户名
 *     picture  用户头像
 *     password  密码
 *     job       用户职位
 *
 * Created by Administrator on 2018/11/20.
 */
public class User {

    //表示用户的标志位
    public final static int SHOP_KEEPER=1,
                            MANAGER=2,
                            SOLDER=3;

    private String id="",count="",name="",
            picture="",password="",job="";

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
