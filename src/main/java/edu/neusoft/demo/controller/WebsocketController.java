package edu.neusoft.demo.controller;

import edu.neusoft.demo.service.RegisterNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zhaoxi
 * @date 2018/12/4 16:28
 * TODO:后台管理系统的推送
 */
@ServerEndpoint(value = "/websocket/manager")

@Component
public class WebsocketController {
    /**
     * log4j日志
     */

    private static final Logger log = LoggerFactory.getLogger(WebsocketController.class);

    /**
     * 静态容器，记录每个客户端连接时，产生对应的ManagerWebSocket对象。
     * concurrent包的线程安全Set。
     */
    private static CopyOnWriteArraySet<WebsocketController> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 静态变量，记录当前在线连接数。
     * 如果需要精确的统计，需要把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    /**
     * 成员变量；每个客户端连接时，产生连接会话。
     * 需要通过它来给对应的客户端发送数据
     * session作为ManagerWebSocket对象的一个成员属性存储起来
     */
    private Session session;


    /**
     * TODO: 连接建立成功调用的方法
     *
     * @return
     * @throws
     * @author zhaoxi
     * @time 2018/12/4 16:32
     * @params
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
    }

    /**
     * TODO: 连接关闭调用的方法
     *
     * @return
     * @throws
     * @author zhaoxi
     * @time 2018/12/4 16:36
     * @params
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * TODO: 收到客户端消息后调用的方法
     *
     * @return
     * @throws
     * @author zhaoxi
     * @time 2018/12/4 16:36
     * @params
     */

    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("来自客户端的消息:" + message);
        /**
         * 回复此客户端
         */

        try {
            this.sendInfo(message);
            this.sendMessage(session, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * TODO: 发生错误时调用
     *
     * @return
     * @throws
     * @author zhaoxi
     * @time 2018/12/4 16:40
     * @params
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.info("发生错误");
        error.printStackTrace();
    }


    /**
     * TODO: 给客户端发消息
     *
     * @return
     * @throws
     * @author zhaoxi
     * @time 2018/12/4 16:39
     * @params
     */
    public void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public void sendMessage(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    /**
     * TODO: 群发自定义消息
     *
     * @return
     * @throws
     * @author zhaoxi
     * @time 2018/12/4 16:44
     * @params
     */
    public static void sendInfo(String message) throws IOException {
        for (WebsocketController item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketController.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketController.onlineCount--;
    }
}