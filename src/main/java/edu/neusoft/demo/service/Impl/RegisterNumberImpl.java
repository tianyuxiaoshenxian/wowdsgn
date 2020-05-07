package edu.neusoft.demo.service.Impl;

import edu.neusoft.demo.entity.RegisterNumber;
import edu.neusoft.demo.mapper.RegisterNumberMapper;
import edu.neusoft.demo.service.RegisterNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public  class RegisterNumberImpl  implements RegisterNumberService {
    //Service接口的实现类
    @Autowired
    RegisterNumberMapper registerNumberMapper;
    public List<RegisterNumber> getRegisterNum(){

        List<RegisterNumber> registerNumber = registerNumberMapper.getRegisterNum();

        return registerNumber;
    }
    @Transactional
    public List<RegisterNumber> setRegisterNum(RegisterNumber registerNumber){

        List<RegisterNumber> registerNumberList = registerNumberMapper.setRegisterNum(registerNumber);

        return registerNumberList;
    }

    public  String getCURRENTTIME(){

        Date date=new Date();
        System.out.println("当前的日期是------>"+date);

        /**
         * 创建格式化时间日期;
         *构造入参String类型就是我们想要转换成的时间形式
         */
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return  format.format(date);
    }
}
