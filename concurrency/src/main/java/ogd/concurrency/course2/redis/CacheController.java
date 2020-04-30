package ogd.concurrency.course2.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 功能描述 : redis测试接口
 * </p>
 *
 * @author : Garen Gosling 2020/4/16 下午3:25
 */
@Controller
@RequestMapping("/redis")
public class CacheController {

    @Autowired
    private RedisClient redisClient;

    @RequestMapping("/set")
    @ResponseBody
    public String set(@RequestParam("k") String k,
                      @RequestParam("v") String v) throws Exception{
        redisClient.set(k, v);
        return "SUCCESS";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam("k") String k) throws Exception{
        return redisClient.get(k);
    }

}
