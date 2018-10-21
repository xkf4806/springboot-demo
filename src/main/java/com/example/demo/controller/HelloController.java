package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xj.x@hnair.com
 * @date 2018-05-02 11:37
 **/
@RestController
public class HelloController {

    @RequestMapping(value = "/index")
    public String index() {
        return "hello springboot";
    }

}
