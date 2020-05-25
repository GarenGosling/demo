package ogd.berkeleyDB.easyDPL.controller;

import ogd.berkeleyDB.easyDPL.dplPlus.util.PkUtils;
import ogd.berkeleyDB.easyDPL.entity.Server;
import ogd.berkeleyDB.easyDPL.service.IServerService;
import ogd.berkeleyDB.response.DataResult;
import ogd.berkeleyDB.response.ResultCodeEnum;
import ogd.berkeleyDB.response.ResultEnum;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/server")
public class ServerController {

    @Resource
    IServerService serverService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public DataResult save(@RequestBody Server server) {
        server.setId(PkUtils.uuid());    // 设置主键
        Server result = serverService.save(server.getId(), server);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public DataResult get(@RequestParam String id) {
        Server server = serverService.get(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, server);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResult update(@RequestBody Server server) {
        Server result = serverService.update(server.getId(), server);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public DataResult delete(@RequestParam String id) {
        serverService.delete(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, null);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public DataResult list() {
        List<Server> result = serverService.list();
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

}
