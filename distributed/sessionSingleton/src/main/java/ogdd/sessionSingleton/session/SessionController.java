package ogdd.sessionSingleton.session;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 功能描述 : 测试分布式session
 * </p>
 *
 * @author : Garen Gosling 2020/4/28 下午5:24
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/singleton")
    public Map<String,Object> sessionInfo(HttpServletRequest request){
        return SessionInfo.getInfo(request);
    }

}