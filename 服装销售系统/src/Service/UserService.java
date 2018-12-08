package Service;

import Model.User;

/**
 * Created by Administrator on 2018/11/23.
 */
public interface UserService {

    /* @Description  检查用户账户和密码是否匹配
    * @return   用户账户和密码是否匹配
    */
    public boolean exisit(User user);

    /* @Description  通过账户设置用户信息，如果是管理员或者店长则在布告栏上发布欢迎信息
    * @return
    */
    public void setUserInfo(User user);

    /* @Description  检查是否存在账户
    * @return  是否存在账户
    */
    public boolean exisitCount(User user);

    /* @Description  跳转到相应用户界面
    * @return
    */
    public void toUserPanel(User user);

    /* @Description  通过count查找用户ID
    * @return 用户ID
    */
    public String selevtID(User user);

    /* @Description  通过ID查找用户，并放倒user里返回
    * @return
    */
    public void selectUserById(User user);

    /* @Description  通过id修改用户信息，并把相应字段同步到users表里
    * @return 是否能修改成功
    */
    public boolean modifyUser(User user);

    /* @Description  向user_defy表插入记录
    * @return
    */
    public void defyUser(String id,String defyId);

    /* @Description  删除user_defy表记录
    * @return
    */
    public void cancelDefy(String id,String defyId);

    /* @Description  向相应的用户表插入记录，并向users表插入记录
    * @return 能否插入成功
    */
    public boolean insert(User user);

    /* @Description  查找users里的所有记录
    * @return users里的记录数
    */
    public int selectAllUsers(Object[][] gs);

    /* @Description  向users表插入记录
    * @return 能否插入成功
    */
    public boolean addUsers(String job,String count);

    /* @Description  通过id修改users表里的记录
    * @return 能否修改成功
    */
    public boolean modifyUsersById(String id,String job,String count);

    /* @Description  通过id删除users表里的记录
    * @return 能否删除成功
    */
    public boolean deleteUsersById(String id);

}
