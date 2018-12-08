package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Description  信件类
 * @parameter
 *     letter_id  信件编号
 *     from_id   发信者ID
 *     to_id     收信者ID
 *     message    信件内容
 *     send_date  发送时间
 * Created by Administrator on 2018/11/26.
 */
public class letter {
    private String letter_id="",from_id="",to_id="",message="";
    private LocalDate send_date;

    public String getLetter_id() {
        return letter_id;
    }

    public void setLetter_id(String letter_id) {
        this.letter_id = letter_id;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getSend_date() {
        return send_date;
    }

    public void setSend_date(LocalDate send_date) {
        this.send_date = send_date;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }
}
