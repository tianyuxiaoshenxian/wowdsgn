package edu.neusoft.demo.controller;

import edu.neusoft.demo.common.util.Result;
import edu.neusoft.demo.entity.RegisterNumber;
import edu.neusoft.demo.service.RegisterNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController进行标记这是一个Controller
@RestController
//控制器处理所有“/user”的URL请求
@RequestMapping("/registerNumber")
public class RegisterNumberController {
    //Service接口的实现类
    @Autowired
    private RegisterNumberService registerNumberService;
//
    @RequestMapping("/test")
    public Object test(){
        return "user is ok!!!";
    }
    @RequestMapping(value = "/setRegisterNum",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Result> getUser(@RequestBody RegisterNumber registerNumber){
        Result res = new Result(200, "ok",null);


        try{
            RegisterNumber result  =   registerNumberService.setRegisterNum( registerNumber);

            if (result.getRgNumber() == 0){
                res.setResultMsg("预约失败,请先注册");
                res.setMessage("no");
            } else {
                res.setResultMsg("预约成功");
                res.putData("result",result);
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
    @RequestMapping(value = "/getRegisterNum",
            method = RequestMethod.POST)
    public ResponseEntity<Result> getRegisterNum(
            @RequestParam(value="bad", required=false, defaultValue="false") boolean bad
    ){
        // 结果封装类对象
        Result res = new Result(200, "ok",null);

        if(bad) {
            res.setStatus(400);
            res.setMessage("Bad request");

            // ResponseEntity是响应实体泛型，通过它可以设置http响应的状态值，此处返回400
            return new ResponseEntity<Result>(res, HttpStatus.BAD_REQUEST);
        }
        try{
            int result = registerNumberService.getRegisterNum();
            String currentTime = registerNumberService.getCurrentTime();
            // 把结果数据放进封装类
            res.putData("RegNum", result);
            res.putData("currentTime", currentTime);
        }
        catch ( IncompatibleClassChangeError error ){
            res.setMessage("请求失败,请检查网络");
            res.setStatus(500);
        }

        // ResponseEntity是响应实体泛型，通过它可以设置http响应的状态值，此处返回200
        return ResponseEntity.ok(res);

    }


}
