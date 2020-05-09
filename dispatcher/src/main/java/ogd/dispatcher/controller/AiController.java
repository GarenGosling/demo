package ogd.dispatcher.controller;

import lombok.extern.slf4j.Slf4j;
import ogd.dispatcher.core.Dispatcher;
import ogd.dispatcher.model.Answer;
import ogd.dispatcher.response.DataResult;
import ogd.dispatcher.response.DataResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 功能描述 : 调度接口
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 上午10:54
 */
@Slf4j
@RestController
@RequestMapping("/ai")
public class AiController {

    @Autowired
    Dispatcher dispatcher;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public DataResult post(@RequestParam Integer aiAppId, @RequestParam String question) {
        Answer answer = dispatcher.execute(aiAppId, question);
        return DataResultUtil.ofSuccess(answer);
    }

}
