package com.example.googlevue11_server.events;

import com.example.googlevue11_server.models.Trip;
import com.example.googlevue11_server.models.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.List;

@ServerEndpoint("/broadcast/trip-ended")
public class TripEndedEvent {

    public TripEndedEvent(Trip trip, User user) {
    }

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
        // 에러가 발생했을 때 수행할 작업
    }

    private void broadcastToChannel(Session session, JsonObject data) {
        try {
            session.getBasicRemote().sendText(data.toString());
        } catch (Exception e) {
            // 예외 처리
        }
    }

    private JsonObject createBroadcastData(Trip trip, User user) {
        JsonObject data = new JsonObject();
        data.addProperty("trip_id", trip.getId());
        data.addProperty("user_id", user.getId());
        // 추가 데이터 필드 설정
        return data;
    }

    private void broadcastToPassengerChannel(JsonObject data, int userId) {
        String channelName = "passenger_" + userId;
        for (Session session : getChannelSessions(channelName)) {
            broadcastToChannel(session, data);
        }
    }

    private List<Session> getChannelSessions(String channelName) {
        // 채널명을 기반으로 세션 목록을 가져오는 로직 구현
        return null;
    }
}

