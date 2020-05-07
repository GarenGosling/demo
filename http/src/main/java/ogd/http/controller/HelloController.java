package ogd.http.controller;

import lombok.extern.slf4j.Slf4j;
import ogd.http.entity.Hello;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 功能描述 : 描述
 * </p>
 *
 * @author : Garen Gosling 2020/5/5 上午10:54
 */
@Slf4j
@RestController
@RequestMapping("/httpHello")
public class HelloController {

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Hello get() {
        log.info("execute method : get");
        return new Hello(1, "hello http");
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Hello post() {
        log.info("execute method : post");
        return new Hello(1, "hello http");
    }

    @RequestMapping(value = "/getWithParams", method = RequestMethod.GET)
    public Hello getWithParams(@RequestParam Integer id, @RequestParam String message) {
        log.info("execute method : getWithParams");
        return new Hello(id, message);
    }

    @RequestMapping(value = "/postWithParams", method = RequestMethod.POST)
    public Hello postWithParams(@RequestParam Integer id, @RequestParam String message) {
        log.info("execute method : postWithParams");
        return new Hello(id, message);
    }

    @RequestMapping(value = "/postWithEntityParam", method = RequestMethod.POST)
    public Hello postWithEntityParam(@RequestBody Hello hello) {
        log.info("execute method : postWithEntityParam");
        return hello;
    }

    @RequestMapping(value = "/postWithParamsAndEntityParam", method = RequestMethod.POST)
    public Map<String, Object> postWithParamsAndEntityParam(@RequestParam String name, @RequestBody Hello hello) {
        log.info("execute method : postWithParamsAndEntityParam");
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("hello", hello);
        return map;
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String file(@RequestParam MultipartFile file, String name) {
        return file.getOriginalFilename() + "--" + name;
    }

}
