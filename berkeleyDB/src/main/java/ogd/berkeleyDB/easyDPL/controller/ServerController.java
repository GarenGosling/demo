package ogd.berkeleyDB.easyDPL.controller;

import ogd.berkeleyDB.easyDPL.entity.Server;
import ogd.berkeleyDB.easyDPL.service.IServerDao;
import ogd.berkeleyDB.response.DataResult;
import ogd.berkeleyDB.response.ResultCodeEnum;
import ogd.berkeleyDB.response.ResultEnum;
import org.garen.plus.dplPlus.Page;
import org.garen.plus.dplPlus.PkUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping(value = "/server")
public class ServerController {

    @Resource
    IServerDao serverDao;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public DataResult save(@RequestBody Server server) {
        server.setId(PkUtils.uuid());    // 设置主键
        Server result = serverDao.save(server.getId(), server);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public DataResult get(@RequestParam String id) {
        Server server = serverDao.get(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, server);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResult update(@RequestBody Server server) {
        Server result = serverDao.update(server.getId(), server);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public DataResult delete(@RequestParam String id) {
        serverDao.delete(id);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, null);
    }

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public DataResult listAll() {
        List<Server> result = serverDao.listAll();
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/listByEngineId", method = RequestMethod.GET)
    public DataResult listByEngineId(@RequestParam String engineId) {
        List<Server> result = serverDao.listBySk("engineId", String.class, engineId);
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

    @RequestMapping(value = "/pageAll", method = RequestMethod.GET)
    public DataResult pageAll(@RequestParam(required = false, value = "dataIndex") Integer dataIndex,
                           @RequestParam(required = false, value = "pageSize") Integer pageSize) {
        Page<Server> result = serverDao.pageAll(dataIndex, pageSize, serverDao.listAll());
        return new DataResult<>(true, ResultEnum.RESULT_SUCCESS, ResultCodeEnum.RESULT_SUCCESS, result);
    }

}
