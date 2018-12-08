package Interface;

import java.awt.*;

/**Description 实现此接口的方法可以页面前后跳转
 * Created by Administrator on 2018/11/30.
 */
public interface moveAble {

    /* @Description  本页面的上一个页面
        * @Parameter
        * @return   本Panel是否还有上一个页面
        */
    public boolean pre();

    /* @Description  本页面的下一个页面
        * @Parameter
        * @return   本Panel是否还有下一个页面
        */
    public boolean next();

    /* @Description  页面列表里添加组件
        * @Parameter  c   组件
        * @return   能否添加成功
        */
    public boolean addNode(Component c);
}
