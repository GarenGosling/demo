package ogd.controllers.controller;

import lombok.extern.slf4j.Slf4j;
import ogd.controllers.response.DataResult;
import ogd.controllers.util.DataResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * @author jinpin
 */
@Slf4j
@RestController
@RequestMapping("/ask")
public class AskController {

    @PostMapping("answer")
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
