package Service;

import Model.User;
import Model.letter;

import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */
public interface letterService {

    /* @Description  发送信件
    * @return   能否发送成功
    */
    public boolean sendLetter(String from_id,String to_id,String message);

    /* @Description  拒绝defyId的来信
    * @return
    */
    public void defyUser(String id,String defyId);

    /* @Description  查找发送给to_id的所有用户ID
    * @return   发送给to_id的所有用户ID
    */
    public List<String> getDefyByToId(String to_id);

    /* @Description  查找发送给to_id,同时没有给to_id拒绝的所有信件
    * @return   信件List
    */
    public List<letter> getLetter(String id);

    /* @Description  查找所有发送给to_id的信件
    * @return   信件List
    */
    public List<letter> selectByToId(String to_id);

    /* @Description  通过ID查找用户名
    * @return   用户名
    */
    public String selectCountById(String id);

    /* @Description  查找from_id发出所有的信件
    * @return   信件List
    */
    public List<letter> selectByFromId(String fromId);

    /* @Description  通过信件编号删除信件
    * @return   能否删除成功
    */
    public boolean deleteLetter(String letter_id);
}
