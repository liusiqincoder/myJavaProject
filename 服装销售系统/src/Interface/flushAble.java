package Interface;

import java.util.ArrayList;

/**
 *@Description  实现此接口能使用 myFindbox查找表格信息
 * Created by Administrator on 2018/12/5.
 */
public interface flushAble {
    /* @Description  刷新表格面板
        * @Parameter  match   如果对应数据字段含有里面的字符串则为匹配的记录
        * @return
        */
    public void flushContent(java.util.List<String> match);

    /* @Description  刷新表格信息
        * @Parameter
        * @return
        */
    public void flushData();

    /* @Description  获取字段值列表
        * @Parameter
        * @return   返回表的某一列的List
        */
    ArrayList<String> getItems();
}
