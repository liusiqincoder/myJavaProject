package Model;

import java.time.LocalDateTime;

/**status  用户身份  1管理员   2 店长    3 销售员
 *
 * 暂时没用
 * Created by Administrator on 2018/11/26.
 */
public class system_log {
    private String id,userId,job,thing;
    private LocalDateTime log_date;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDateTime getLog_date() {
        return log_date;
    }

    public void setLog_date(LocalDateTime log_date) {
        this.log_date = log_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
