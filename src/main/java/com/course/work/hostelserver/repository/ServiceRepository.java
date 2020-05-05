package com.course.work.hostelserver.repository;

import java.util.List;
import java.util.Map;

public interface ServiceRepository {
    List<Map<String, Object>> findAllWithPriceMore(Double price);

    List<Map<String, Object>> findAllWithServicesAmount(Integer amount);

    List<Map<String, Object>> findServicesByGuest(Integer id);

    List<Map<String, Object>> findServicesSumByGuest(Integer id);
}
