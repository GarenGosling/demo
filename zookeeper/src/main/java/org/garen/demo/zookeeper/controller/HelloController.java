package org.garen.demo.zookeeper.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.garen.demo.zookeeper.response.CodeEnum;
import org.garen.demo.zookeeper.response.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "测试接口")
@RestController
@RequestMapping(value = "/zookeeper")
public class HelloController {

    @ApiOperation(value="测试")
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public Response hello() {
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), null);
    }

}
