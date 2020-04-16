package org.garen.demo.highConcurrency.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloController {

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String hello() {
        return "test";
    }

}
