package edu.neusoft.demo.service;


import edu.neusoft.demo.entity.RegisterNumber;

import java.util.List;

//Service层业务接口类编写
public interface RegisterNumberService {

    //添加新User
//    public int getRegisterNum();

//    List<RegisterNumber> getRegisterNum(RegisterNumber registerNumber);
    public  String getCURRENTTIME();
    public  List<RegisterNumber> getRegisterNum();

    public List<RegisterNumber> setRegisterNum( RegisterNumber registerNumber);

}
