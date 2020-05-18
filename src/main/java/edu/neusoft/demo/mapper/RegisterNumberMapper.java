package edu.neusoft.demo.mapper;

import edu.neusoft.demo.entity.RegisterNumber;
import edu.neusoft.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
//添加接口
@Mapper
//数据库访问接口类
public interface RegisterNumberMapper {


    public List<RegisterNumber>  getRegisterNum(String startDate, String endDate);

    public  RegisterNumber getUserRegInfo(String userName,String startDate, String endDate);

    public  int setRegisterNum(RegisterNumber registerNumber);

    public  int getMaxRgNum( String startDate, String endDate);
}
