package DAO;

import Model.User;

import java.util.List;

/**
 * Created by Administrator on 2018/11/23.
 */
public interface UserDao {
    //对于Manager，ShopKeeper，Solder

    /* @Description  查看用户名count，${table}表的用户的密码是否为password
        String sql="select password from "+table+" where count=?";
        * @Parameter   count   用户名
        *              password  用户密码
        *              table   用户所在的表名
        * @return    是否存在该用户
        */
    public boolean check(String count, String password,String table);

    /* @Description  查找用户名count，${table}表的用户的信息，并放倒user里返回
        String sql="select* from "+table+" where count='"+count+"'";
        * @Parameter   count   用户名
        *              user  用户类
        *              table   用户所在的表名
        * @return    能否查找到
        */
    public void selectByCount(String count,String table,User user);

    /* @Description  通过ID查找用户，并放倒user里返回
        String sql="select* from "+table+" where id='"+user.getId()+"'";
        * @Parameter   user  用户类
        * @return    能否查找到
        */
    public void selectUserById(User user);

    /* @Description  通过ID查找用户名
        String sql="SELECT count FROM users WHERE id=?";
        * @Parameter   id  用户ID
        * @return    用户名
        */
    public String selectCountById(String id);

    /* @Description  插入用户记录到${table}表里
        String sql="insert into "+table+"(id,password,count,name,picture) values(?,?,?,?,?)";
        * @Parameter   user  用户类
        *              table  user对应的表
        * @return    能否插入成功
        */
    public boolean insert(User user,String table);

    /* @Description  通过count更新用户密码
        String sql="UPDATE "+table+" SET password=? WHERE count=?";
        * @Parameter   user  用户类
        *              count 用户名
        *              table  user对应的表
        * @return    能否插入成功
        */
    public boolean updatePassWordByCount(String count, User user,String table);

    /* @Description  通过count查找用户ID
        String sql="SELECT id FROM "+table+" WHERE count=?";
        * @Parameter   user  用户类
        *              table  user对应的表
        * @return    用户ID
        */
    public String selectID(String table,User user);

    /* @Description  通过id修改用户信息
        String sql="UPDATE "+table+" SET count=?,password=?,name=?,picture=? WHERE id=?";
        * @Parameter   user  用户类
        *              table  user对应的表
        * @return    能否修改成功
        */
    public boolean modifyUser(String table,User user);

    /* @Description  更新user_defy
        String sql="UPDATE user_defy SET defy_id=? WHERE user_id=?";
        * @Parameter   id  用户id
        *              defyId  用户拒绝defyId的来信
        * @return    能否修改成功
        */
    public boolean updateDefyId(String id,String defyId);

    /* @Description  通过user_id查找该用户的所有拒绝来信的ID
        String sql="select defy_id from user_defy where user_id=?";
        * @Parameter   userDefyId   用户ID
        * @return    用户拒绝来信的用户ID
        */
    public List<String> selectDefy(String userDefyId);

   /* @Description  通过user_id查找该用户的所有拒绝来信的ID
            String sql="DELETE FROM user_defy where user_id=? AND defy_id=?";
        * @Parameter   id   用户ID
        *              defyId  拒绝来信的用户ID
        * @return    能否删除成功
        */
    public boolean deleteDefy(String id,String defyId);

    /* @Description  通过user_id修改该用户拒绝来信的ID
        String sql="UPDATE user_defy SET defy_id=? WHERE user_id=?";
        * @Parameter   id   用户ID
        *              defyId  拒绝来信的用户ID
        * @return    能否修改成功
        */
    public boolean modifydefy(String id,String defyId);

    /* @Description  向user_defy表插入记录
        String sql="insert into user_defy(userDefy_id,user_id,defy_id) values(?,?,?)";
        * @Parameter   id   用户ID
        *              defyId  拒绝来信的用户ID
        *              userDefyId   编号
        * @return    能否插入成功
        */
    public boolean insert(String userDefyId,String id,String defy_id);

    /* @Description  向users表插入记录
        String sql="insert into users(id,job,count) values(?,?,?)";
        * @Parameter   ser  用户类
        * @return    能否插入成功
        */
    public boolean insertUsers(User user);

    /* @Description  向users表插入记录
        String sql="insert into users(id,job,count) values(?,?,?)";
        * @Parameter   ser  用户类
        * @return    能否插入成功
        */
    public boolean modifyUsers(User user);

    /* @Description  通过id查找用户信息
        String sql="select count,job from users where id=?";
        * @Parameter   id  用户ID
        * @return    能否查找到
        */
    public User selectUser(String id);

    /* @Description  通过ID更新用户信息
        String sql="UPDATE users SET count=?,job=? WHERE id=?";
        * @Parameter   user  用户类
        * @return    能否修改成功
        */
    public boolean updateUsers(User user);

    /* @Description  通过ID删除用户信息
        String sql="DELETE FROM users where id=?";
        * @Parameter   id  用户ID
        * @return    能否删除成功
        */
    public boolean deleteUsers(String id);

    /* @Description  查找所有用户信息
        String sql="select id,count,job from users";
        * @Parameter
        * @return    能否查找到
        */
    List<User> selectAllUsers();
}
