package ogd.berkeleyDB.easyDPL.controller;

import ogd.berkeleyDB.easyDPL.entity.Engine;
import ogd.berkeleyDB.easyDPL.service.IEngineService;
import ogd.berkeleyDB.response.DataResult;
import ogd.berkeleyDB.response.ResultCodeEnum;
import ogd.berkeleyDB.response.ResultEnum;
import org.garen.plus.dplPlus.Page;
import org.garen.plus.dplPlus.PkUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/engine")
public class EngineController {

    @Resource
    IEngineService engineService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public DataResult save(@RequestBody Engine engine) {
        engine.setId(PkUtils.uuid());    // 设置主键
        Engine result = engineService.save(engine.getId(), engine);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public DataResult get(@RequestParam String id) {
        Engine result = engineService.get(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResult update(@RequestBody Engine engine) {
        Engine result = engineService.update(engine.getId(), engine);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public DataResult delete(@RequestParam String id) {
        engineService.delete(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, null);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public DataResult listAll() {
        List<Engine> result = engineService.listAll();
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/listByAiAppId", method = RequestMethod.GET)
    public DataResult listByAiAppId(@RequestParam String aiAppId) {
        List<Engine> result = engineService.listBySk("aiAppId", String.class, aiAppId);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/getByParams", method = RequestMethod.GET)
    public DataResult getByParams(@RequestParam(required = false, value = "name") String name,
                                  @RequestParam(required = false, value = "type") Integer type) {
        List<Engine> result = engineService.getByParams(name, type);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/pageAll", method = RequestMethod.GET)
    public DataResult pageAll(@RequestParam(required = false, value = "current") Integer current,
                           @RequestParam(required = false, value = "size") Integer size) {
        Page<Engine> result = engineService.pageAll(current, size, engineService.listAll());
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

}
