package com.course.work.hostelserver.repository;

import com.course.work.hostelserver.entity.Guest;

import java.util.List;
import java.util.Map;

public interface GuestRepository {
    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findLikeSurname(String surname);

    List<Map<String, Object>> findActive();

    List<Map<String, Object>> getGuestsWithPrices();

    List<Map<String, Object>> findById(Integer id);

    List<Map<String, Object>> checkIfPhoneExist(String phone);

    void createGuest(Guest guest);
}
