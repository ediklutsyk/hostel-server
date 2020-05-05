package com.course.work.hostelserver.repository;

import java.util.List;
import java.util.Map;

public interface RoomTypeRepository {

    List<Map<String, Object>> getAmountOfRoomsByEachType();
}
