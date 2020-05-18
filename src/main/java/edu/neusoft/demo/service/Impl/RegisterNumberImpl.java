package edu.neusoft.demo.service.Impl;


import edu.neusoft.demo.entity.RegisterNumber;
import edu.neusoft.demo.mapper.RegisterNumberMapper;
import edu.neusoft.demo.service.RegisterNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public  class RegisterNumberImpl  implements RegisterNumberService {
    //Service接口的实现类

    public static int onlineRgNumber = 1;
    @Autowired
    RegisterNumberMapper registerNumberMapper;
    public int getRegisterNum(){

//        Date currentTimeFoStart = initDateByDay();

        int result = registerNumberMapper.getMaxRgNum(
                startDateByDay(),endDataByDay());

        return result;
    }

    @Transactional
    public RegisterNumber setRegisterNum(RegisterNumber registerNumber){


        List<RegisterNumber> registerNumberList = registerNumberMapper.getRegisterNum(startDateByDay(),endDataByDay());
        int maxRgNum = registerNumberMapper.getMaxRgNum(startDateByDay(),endDataByDay());
        System.out.println("this is put date----------------------" +  maxRgNum);
        System.out.println("this is put date----------------------" +  startDateByDay()+ "---------" + endDataByDay());

            if (maxRgNum!=0){
                onlineRgNumber = maxRgNum;
                pushRG_NUMBER();
            } else {
                onlineRgNumber = 1;
            }
            registerNumber.setRgNumber(onlineRgNumber);
            System.out.println("打印的是registerNumber值----------------------" +  registerNumber);

            System.out.println("打印的是renumber值----------------------" +  registerNumber.getRgNumber());



        int status =  registerNumberMapper.setRegisterNum(registerNumber);
        RegisterNumber result = null;
        if(status!=0){

            String username = registerNumber.getUserName();
            result=    registerNumberMapper.getUserRegInfo(username,startDateByDay(),endDataByDay());
            System.out.println("打印的是result值----------------------"+ result);
        }
//        List<RegisterNumber> result =  registerNumberMapper.setRegisterNum(registerNumber);



//        int rgNumber = RegisterNumber.getOnlineRgNumber();



        return result;
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

    public  synchronized int pushRG_NUMBER() {
       return onlineRgNumber++;
    }


    public  synchronized int popRG_NUMBER() {
       return onlineRgNumber--;
    }


    /**
     * 获得当天零时零分零秒
     * @return
     */
    public static String startDateByDay(){

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();

        /**
         * 创建格式化时间日期;
         *构造入参String类型就是我们想要转换成的时间形式
         */
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("当前的日期是------>"+format.format(date));

        return  format.format(date);
    }

    /**
     * 获得当天零时零分零秒
     * @return
     */
    public static String endDataByDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        Date date =  calendar.getTime();

        /**
         * 创建格式化时间日期;
         *构造入参String类型就是我们想要转换成的时间形式
         */
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前的日期是------>"+format.format(date));

        return  format.format(date);
    }




}
