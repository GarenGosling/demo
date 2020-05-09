package ogd.controllers.controller;

import lombok.extern.slf4j.Slf4j;
import ogd.controllers.response.DataResult;
import ogd.controllers.util.DataResultUtil;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 功能描述 : 心跳检测接口
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 上午10:54
 */
@Slf4j
@RestController
public class HbController {

    @RequestMapping(value = "/hb", method = RequestMethod.GET)
    public DataResult hb() {
        return DataResultUtil.ofSuccess(null);
    }
}
