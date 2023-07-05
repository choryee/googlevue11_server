package com.example.googlevue11_server.events;

import org.springframework.stereotype.Component;
import org.w3c.dom.events.Event;

@Component
public class EventDispatcher {

    public void dispatch(Event event) {
        // 이벤트 유형에 따라 처리 작업을 수행하는 로직을 작성합니다.
        // 예시로는 이벤트를 콘솔에 출력하는 것으로 대체하였습니다.
        System.out.println("Event dispatched: " + event.getClass().getSimpleName());
        System.out.println("Event data: " + event.toString());

        // 이벤트 유형에 따라 추가적인 작업을 수행할 수 있습니다.
        if (event instanceof TripLocationUpdated) {
            handleTripLocationUpdatedEvent((TripLocationUpdated) event);
        }
        // 다른 이벤트 유형에 대한 처리 로직을 추가할 수 있습니다.
    }

    public void handleTripLocationUpdatedEvent(TripLocationUpdated event) {
        // TripLocationUpdated 이벤트 처리 작업을 수행합니다.
        Integer tripId = event.getTripId();
        double latitude = event.getLatitude();
        double longitude = event.getLongitude();

        // 처리 작업을 수행하는 로직을 작성합니다.
        // 예시로는 콘솔에 출력하는 것으로 대체하였습니다.
        System.out.println("Handling TripLocationUpdated event");
        System.out.println("Trip ID: " + tripId);
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);

        // 이벤트 처리 후 추가적인 작업을 수행할 수 있습니다.
    }
}
