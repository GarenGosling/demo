package ogdd.sessionDistributed.noRepeatSubmit;

import ogdd.sessionDistributed.session.SessionInfo;
import org.springframework.web.bind.annotation.*;

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
    public Map<String,Object> noRepeat(HttpServletRequest request, @RequestParam String testArg){
        return SessionInfo.getInfo(request);
    }

    @NoRepeatSubmitAnnotation
    @PostMapping("/noRepeat2")
    public Map<String,Object> noRepeat2(HttpServletRequest request, @RequestParam String testArg, @RequestBody ReqVo testVo){
        return SessionInfo.getInfo(request);
    }

    @NoRepeatSubmitAnnotation
    @PostMapping("/noRepeat3")
    public Map<String,Object> noRepeat3(HttpServletRequest request, @RequestHeader String token){
        return SessionInfo.getInfo(request);
    }

    @GetMapping("/repeat")
    public Map<String,Object> repeat(HttpServletRequest request){
        return SessionInfo.getInfo(request);
    }

}