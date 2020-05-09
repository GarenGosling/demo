package ogd.dispatcher.controller;

import lombok.extern.slf4j.Slf4j;
import ogd.dispatcher.config.DefaultConfig;
import ogd.dispatcher.core.Dispatcher;
import ogd.dispatcher.response.DataResult;
import ogd.dispatcher.response.DataResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 功能描述 : 调度接口
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 上午10:54
 */
@Slf4j
@RestController
@RequestMapping("/default")
public class DefaultController {

    @Autowired
    Dispatcher dispatcher;

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public DataResult config() {
        return DataResultUtil.ofSuccess(new DefaultConfig().initAiAppList());
    }

}
