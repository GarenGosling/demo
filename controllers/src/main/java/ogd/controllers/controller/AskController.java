package ogd.controllers.controller;

import lombok.extern.slf4j.Slf4j;
import ogd.controllers.response.DataResult;
import ogd.controllers.util.DataResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinpin
 */
@Slf4j
@RestController
@RequestMapping("/ask")
public class AskController {

    @PostMapping("answer")
    public DataResult answer(@RequestParam(value = "question") String question) {
        log.info("question: {}", question);
        return DataResultUtil.ofSuccess("This is an answer for the question");
    }
}
