package edu.neusoft.demo.jar;

import javax.websocket.*;
import java.io.IOException;

@ClientEndpoint

public class Myclient {


    @OnOpen

    public void open(Session session){

        System.out.println("Client WebSocket is opening...");

        System.out.println("Connected to endpoint:"+ session.getBasicRemote());

        try {


            session.getBasicRemote().sendText("Hello");


        } catch (IOException ex) {


        }

    }


    @OnMessage

    public void onMessage(String message){

        System.out.println("Server send message: " + message);

    }


    @OnClose

    public void onClose(){

        System.out.println("Websocket closed");

    }


}