package ogd.concurrency.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "测试接口")
@RestController
@RequestMapping(value = "/")
public class HelloController {

    @ApiOperation(value="测试")
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String hello() {
        return "restTemplate";
    }

}
