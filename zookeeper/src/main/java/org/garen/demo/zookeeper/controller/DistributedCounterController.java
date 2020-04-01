package org.garen.demo.zookeeper.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.garen.demo.zookeeper.component.IDistributedCounter;
import org.garen.demo.zookeeper.component.entity.CounterResult;
import org.garen.demo.zookeeper.response.CodeEnum;
import org.garen.demo.zookeeper.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "分布式计数器")
@RestController
@RequestMapping(value = "/zookeeper/distributed/counter")
public class DistributedCounterController {

    @Autowired
    private IDistributedCounter counter;

    @ApiOperation(value="计数值 + 1")
    @RequestMapping(value = "/increment", method = RequestMethod.GET)
    public Response increment(@RequestParam String counterName) {
        CounterResult result = counter.increment(counterName);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="计数值 - 1")
    @RequestMapping(value = "/decrement", method = RequestMethod.GET)
    public Response decrement(@RequestParam String counterName) {
        CounterResult result = counter.decrement(counterName);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="计数值 + delta")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Response add(@RequestParam String counterName, @RequestParam Long delta) {
        CounterResult result = counter.add(counterName, delta);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="计数值 - delta")
    @RequestMapping(value = "/subtract", method = RequestMethod.GET)
    public Response subtract(@RequestParam String counterName, @RequestParam Long delta) {
        CounterResult result = counter.subtract(counterName, delta);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="查询当前计数值")
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Response get(@RequestParam String counterName) {
        CounterResult result = counter.get(counterName);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="查询当前计数值")
    @RequestMapping(value = "/getValue", method = RequestMethod.GET)
    public Response getValue(@RequestParam String counterName) {
        Long value = counter.getValue(counterName);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), value);
    }

    @ApiOperation(value="尝试设置计数值")
    @RequestMapping(value = "/trySet", method = RequestMethod.GET)
    public Response trySet(@RequestParam String counterName, @RequestParam Long newValue) {
        CounterResult result = counter.trySet(counterName, newValue);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), result);
    }

    @ApiOperation(value="强迫设置计数值")
    @RequestMapping(value = "/forceSet", method = RequestMethod.GET)
    public Response forceSet(@RequestParam String counterName, @RequestParam Long newValue) {
        counter.forceSet(counterName, newValue);
        return new Response(CodeEnum.OK.code(), CodeEnum.OK.msg(), null);
    }

}
