package ogd.berkeleyDB.easyDPL.controller;

import ogd.berkeleyDB.easyDPL.entity.AiApp;
import ogd.berkeleyDB.easyDPL.service.IAiAppService;
import ogd.berkeleyDB.response.DataResult;
import ogd.berkeleyDB.response.ResultCodeEnum;
import ogd.berkeleyDB.response.ResultEnum;
import org.garen.plus.dplPlus.Page;
import org.garen.plus.dplPlus.PkUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/aiApp")
public class AiAppController {

    @Resource
    IAiAppService aiAppService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public DataResult save(@RequestBody AiApp aiApp) {
        aiApp.setId(PkUtils.uuid());    // 设置主键
        AiApp result = aiAppService.save(aiApp.getId(), aiApp);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public DataResult get(@RequestParam String id) {
        AiApp result = aiAppService.get(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResult update(@RequestBody AiApp aiApp) {
        AiApp result = aiAppService.update(aiApp.getId(), aiApp);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public DataResult delete(@RequestParam String id) {
        aiAppService.delete(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, null);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public DataResult listAll() {
        List<AiApp> result = aiAppService.listAll();
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/listByName", method = RequestMethod.GET)
    public DataResult listByName(@RequestParam String name) {
        List<AiApp> result = aiAppService.listBySk("name", String.class, name);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/pageAll", method = RequestMethod.GET)
    public DataResult pageAll(@RequestParam(required = false, value = "current") Integer current,
                           @RequestParam(required = false, value = "size") Integer size) {
        Page<AiApp> result = aiAppService.pageAll(current, size, aiAppService.listAll());
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }


}
