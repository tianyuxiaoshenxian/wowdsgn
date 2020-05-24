package edu.neusoft.demo.service;


import edu.neusoft.demo.entity.RegisterNumber;

//Service层业务接口类编写
public interface RegisterNumberService {

    //添加新User
//    public int getRegisterNum();

//    List<RegisterNumber> getRegisterNum(RegisterNumber registerNumber);
    public  String getCurrentTime();

    public int getRegisterNum();

    public RegisterNumber setRegisterNum(RegisterNumber registerNumber);

}
