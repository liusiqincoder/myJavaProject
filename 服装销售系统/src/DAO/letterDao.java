package DAO;

import Model.letter;

import java.util.List;

/**
 * Created by Administrator on 2018/11/26.
 */
public interface letterDao {

    /* @Description  通过信件编号letter_id查找信件
        String sql="SELECT letter_id,from_id,to_id,message,to_char(send_date,'YYYY-MM-DD') FROM letter WHERE letter_id=?";
        * @Parameter   letter_id   信件编号
        * @return    信件记录
        */
    public letter selectById(String letter_id);

    /* @Description  通过发信者的ID查找信件
        String sql="SELECT letter_id,to_id,message,to_char(send_date,'YYYY-MM-DD') FROM letter WHERE from_id=?";
        * @Parameter   from_id   发信者的ID
        * @return    from_id用户的所有发信记录
        */
    public List<letter> selectByFromId(String from_id);

    /* @Description  通过收信者的ID查找信件
        String sql="SELECT letter_id,from_id,message,to_char(send_date,'YYYY-MM-DD') FROM letter WHERE to_id=?";
        * @Parameter   to_id   收信者的ID
        * @return    to_id用户收到的所有信件
        */
    public List<letter> selectByToId(String to_id);

    /* @Description  插入信件记录
        String sql="INSERT INTO letter(letter_id,from_id,to_id,message,send_date) VALUES (?,?,?,?,?)";
        * @Parameter   l   信件记录
        * @return    能否插入记录
        */
    public boolean insert(letter l);

    /* @Description  通过信件编号letter_id删除信件记录
        String sql="DELETE FROM letter WHERE letter_id=?";
        * @Parameter   letter_id   信件编号
        * @return    能否删除记录
        */
    public boolean deleteById(String letter_id);

    /* @Description  删除所有关于fron_id的来信
        String sql="DELETE FROM letter WHERE from_id=?";
        * @Parameter   fromId   发信者ID
        * @return    能否删除成功
        */
    public boolean deleteByFromId(String fromId);

    /* @Description  删除所有发给to_id的信件
        String sql="DELETE FROM letter WHERE to_id=?";
        * @Parameter   toId   收信者ID
        * @return    能否删除成功
        */
    public boolean deleteByToId(String toId);

    /* @Description  更新letter_id的信件
        String sql="UPDATE letter SET from_id=?,to_id=?,message=?,send_date=? WHERE letter_id=?";
        * @Parameter   l   信件类
        * @return    能否更新成功
        */
    public boolean update(letter l);
}
