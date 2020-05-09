package ogd.controllers.controller;

import lombok.extern.slf4j.Slf4j;
import ogd.controllers.response.DataResult;
import ogd.controllers.util.DataResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * <p>
 * 功能描述 : 问答接口
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 上午10:54
 */
@Slf4j
@RestController
@RequestMapping("/ask")
public class AskController {

    @RequestMapping(value = "answer", method = RequestMethod.POST)
    public DataResult answer(@RequestParam(value = "question") String question, HttpServletRequest request) {
        request.getRemoteHost();
        request.getRemotePort();
        log.info("question: {}, port: {}", question, request.getServerPort());
        String sb = null;
        if(new Random().nextBoolean()){
            sb = "This is an answer for the question, port: " + request.getServerPort();
        }
        return DataResultUtil.ofSuccess(sb);
    }
}
