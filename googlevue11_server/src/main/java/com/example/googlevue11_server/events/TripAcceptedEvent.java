package com.example.googlevue11_server.events;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/broadcast/trip-accepted")
public class TripAcceptedEvent {

    @OnOpen
    public void onOpen(Session session) {
        // 세션이 열릴 때 수행할 작업
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        // 메시지를 수신할 때 수행할 작업
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        // 세션이 닫힐 때 수행할 작업
    }

    @OnError
    public void onError(Session session, Throwable throwable) {

    }
}