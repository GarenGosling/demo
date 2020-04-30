package ogd.zookeeper.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ogd.zookeeper.component.zookeeperCounter.IDistributedCounterGroup;
import ogd.zookeeper.component.zookeeperCounter.entity.CounterInfo;
import ogd.zookeeper.response.CodeEnum;
import ogd.zookeeper.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(description = "分布式计数器组")
@RestController
@RequestMapping(value = "/zookeeper/distributed/counter/group")
public class DistributedCounterGroupController {

    @Autowired
    private IDistributedCounterGroup group;

    @ApiOperation(value="创建计数器")
    @RequestMapping(value = "/createCounter", method = RequestMethod.GET)
    public Response createCounter(@RequestParam String counterName,
                                  @RequestParam String counterDesc,
                                  @RequestParam Long initialize) {
        boolean result = group.createCounter(counterName, counterDesc, initialize);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="删除计数器")
    @RequestMapping(value = "/deleteCounter", method = RequestMethod.GET)
    public Response deleteCounter(@RequestParam String counterName) {
        boolean result = group.deleteCounter(counterName);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="修改计数器（只能改描述）")
    @RequestMapping(value = "/updateCounter", method = RequestMethod.GET)
    public Response updateCounter(@RequestParam String counterName,
                                  @RequestParam String counterDesc) {
        boolean result = group.updateCounter(counterName, counterDesc);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="计数器列表")
    @RequestMapping(value = "/getCounterList", method = RequestMethod.GET)
    public Response getCounterList() {
        List<CounterInfo> result = group.getCounterList();
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="初始化计数器")
    @RequestMapping(value = "/initialize", method = RequestMethod.GET)
    public Response initialize(@RequestParam String counterName,
                               @RequestParam Long initialize) {
        boolean result = group.initialize(counterName, initialize);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

}
