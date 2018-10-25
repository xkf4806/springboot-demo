package com.example.demo.controller;

import com.example.demo.model.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xinj.x
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private ConcurrentHashMap<String, User> userMap = new ConcurrentHashMap<>();
    @PostConstruct
    private void init() {
        userMap.put("zhangsan", new User("zhangsan", "123"));
        userMap.put("lisi", new User("lisi", "456"));
        userMap.put("wangwu", new User("wangwu", "789"));
    }

    @ApiOperation(value = "获取所有用户", notes = "")
    @GetMapping(value = "/users")
    public List<User> listAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    @ApiOperation(value = "新增用户")
    @ApiImplicitParam(name = "user", required = true, dataTypeClass = User.class)
    @PostMapping(value = "")
    public String addUser(@RequestBody User user) {
        log.info("新增用户成功！user={}", user);
        return user.getUserName();
    }

    @ApiOperation(value = "根据用户名删除用户")
    @ApiImplicitParam(name = "username", required = true, dataTypeClass = String.class, dataType = "username")
    @DeleteMapping(value = "/{username}")
    public User deleteUser(@PathVariable("username") String username) {
        log.info("删除用户username={}", username);
        User remove = userMap.remove(username);
        if(remove != null) {
            log.info("删除成功，用户信息：{}", remove);
        }
        return remove;
    }
}
