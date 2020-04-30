package ogd.zookeeper.course;

import lombok.extern.slf4j.Slf4j;
import ogd.zookeeper.response.BusinessException;
import org.junit.platform.commons.util.StringUtils;

/**
 * <p>
 * 功能描述 : zookeeper课程中的公共配置和通用方法
 * </p>
 *
 * @author : Garen Gosling 2020/3/28 下午4:46
 */
@Slf4j
public class Config {

    /**
     * zookeeper服务器地址
     */
    public static final String CONNECTION_STRING = "127.0.0.1";

    /**
     * 基础节点
     * 课程中使用的节点都是这个节点的子节点
     */
    public static final String BASE_NODE_PATH = "/course1";

    /**
     * 节点分隔符
     */
    public static final String SPLIT = "/";


    /**
     * <p>
     * 功能描述 : 通过节点名称获取节点全路径
     * </p>
     *
     * @author : Garen Gosling   2020/3/28 下午4:48
     *
     * @param nodeName 节点名称
     * @Return java.lang.String
     **/
    public static String getNodePath(String nodeName) {
        if(StringUtils.isBlank(nodeName)){
            throw new BusinessException("node name is null!");
        }
        return BASE_NODE_PATH + SPLIT + nodeName;
    }

}
