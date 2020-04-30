package ogdd.sessionDistributed.noRepeatSubmit;

import ogdd.sessionDistributed.session.SessionInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 功能描述 : 测试禁止重复提交
 * </p>
 *
 * @author : Garen Gosling 2020/4/29 下午4:56
 */
@RestController
@RequestMapping("/submit")
public class NoRepeatSubmitController {

    @NoRepeatSubmitAnnotation
    @GetMapping("/noRepeat")
    public Map<String,Object> noRepeat(HttpServletRequest request){
       return SessionInfo.getInfo(request);
    }

    @GetMapping("/repeat")
    public Map<String,Object> repeat(HttpServletRequest request){
        return SessionInfo.getInfo(request);
    }

}