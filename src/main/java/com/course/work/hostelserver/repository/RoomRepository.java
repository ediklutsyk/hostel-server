package com.course.work.hostelserver.repository;

import com.course.work.hostelserver.entity.FilterRooms;

import java.util.List;
import java.util.Map;

public interface RoomRepository {

    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findPrivate();

    List<Map<String, Object>> findPrivateForGuestNum(Integer amount);

    List<Map<String, Object>> findDorms();

    List<Map<String, Object>> findDormsForGuestNum(Integer amount);

    List<Map<String, Object>> filterRooms(FilterRooms filter);

    List<Map<String, Object>> getAllBedsSizes();

    List<Map<String, Object>> getMinAndMaxPrice();

    List<Map<String, Object>> findById(Integer id);

    List<Map<String, Object>> findAllCleaningById(Integer id);

    List<Map<String, Object>> findAllGuestCurrentlyLivingInTheRoom(Integer id);
}
