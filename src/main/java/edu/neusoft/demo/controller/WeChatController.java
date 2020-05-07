package edu.neusoft.demo.controller;

import edu.neusoft.demo.common.HttpUtil;
import edu.neusoft.demo.entity.WeChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author songxipeng
 * @since 2020-1-2
 */
@RestController
@RequestMapping("/wechat")
public class WeChatController {

    private Logger logger = LoggerFactory.getLogger(WeChatController.class);

    /**
     * @return
     */
    @RequestMapping(value = "/getopenid",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getOpenId(@RequestBody WeChat wechat){
        System.out.println("===========getOpenId START=======");

        String url = "https://api.weixin.qq.com/sns/jscode2session";

        String requestUrl = url + "?appid=" + wechat.getAppId() + "&secret=" + wechat.getSecret()
                + "&js_code=" + wechat.getJsCode() + "&grant_type=" + wechat.getGrantType();

        String data = HttpUtil.get(requestUrl);
        System.out.println("=========== "+data+ "=======");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json = null;
        try {
            json = mapper.readValue(data, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("===========getOpenId END=======");

        return json;
    }

    @RequestMapping("/test3")
    public Object test(){
        return "ok!!!";
    }

}

