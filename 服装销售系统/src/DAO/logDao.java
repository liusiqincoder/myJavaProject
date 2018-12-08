package DAO;

import Model.User;
import Model.system_log;

/**
 * 这个类暂时没用
 * Created by Administrator on 2018/11/26.
 */
public interface logDao {
    public system_log select(User user);
    public boolean add(system_log log);
    public boolean modify(system_log log);
    public boolean delete(system_log log);
}
