package edu.neusoft.demo.controller;

import edu.neusoft.demo.common.util.Result;
import edu.neusoft.demo.entity.RegisterNumber;
import edu.neusoft.demo.entity.User;
import edu.neusoft.demo.service.UserService;
import edu.neusoft.demo.token.TokenSign;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@RestController进行标记这是一个Controller
@RestController
//控制器处理所有“/user”的URL请求
@RequestMapping("/user")
public class UserController {

    //Service接口的实现类
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public Object test(){
        return "user is ok!!!";
    }



    //注册新用户信息
    @RequestMapping(value = "/setUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Result> editUser(@RequestBody User user){
        Result res = new Result(200, "ok",null);

        User result =   userService.getUser(user);
        try{
            // 把结果数据放进封
            if ( result == null ){
                if (userService.setUser(user) == 1 ){
                    res.setResultMsg("注册成功");
                    res.setMessage("ok");
                }
            } else {
                res.setResultMsg("注册失败,用户已存在");
                res.setMessage("no");
            }


        }
        catch ( IncompatibleClassChangeError error ){
            res.setMessage("请求失败,请检查网络");
            res.setStatus(500);
        }
        return ResponseEntity.ok(res);
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Result> getUser(@RequestBody User userInfo){
        Result res = new Result(200, "ok",null);

        try{
            User result =   userService.getUser(userInfo);

            if (result == null){
                res.setResultMsg("登录失败,请先注册");
                res.setMessage("no");
            } else {
                res.setResultMsg("登录成功");
                res.putData("result",result);
                res.putData("token",TokenSign.sign(result.getUserName(),result.getUserId()+""));
                res.setMessage("ok");
            }


        }
        catch ( IncompatibleClassChangeError error ){
            res.setMessage("no");
            res.setResultMsg("请求失败,请检查网络");
            res.setStatus(500);
        }
        return ResponseEntity.ok(res);
    }



}
