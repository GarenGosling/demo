package ogd.berkeleyDB.easyDPL.controller;

import ogd.berkeleyDB.easyDPL.entity.Engine;
import ogd.berkeleyDB.easyDPL.service.IEngineService;
import ogd.berkeleyDB.response.DataResult;
import ogd.berkeleyDB.response.ResultCodeEnum;
import ogd.berkeleyDB.response.ResultEnum;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping(value = "/engine")
public class EngineController {

    @Resource
    IEngineService engineService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public DataResult save(@RequestBody Engine engine) {
        Engine result = engineService.save(engine);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public DataResult get(@RequestParam String id) {
        Engine result = engineService.get(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResult update(@RequestBody Engine engine) {
        Engine result = engineService.update(engine);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public DataResult delete(@RequestParam String id) {
        engineService.delete(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, null);
    }

}
