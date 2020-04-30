package ogdd.sessionSingleton.session;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 功能描述 : 会话信息
 * </p>
 *
 * @author : Garen Gosling 2020/4/30 上午10:09
 */
public class SessionInfo {

    /**
     * <p>
     * 功能描述 : 获取session信息
     * </p>
     *
     * @author : Garen Gosling   2020/4/30 上午10:11
     *
     * @param request 请求
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static Map<String,Object> getInfo(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();
        map.put("sessionId",request.getSession().getId());
        map.put("port",request.getLocalPort());
        return map;
    }

}
